package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import model.Shape;

public class BoundBox extends Rectangle implements Shape {
	private static final long serialVersionUID = 1;
	private ControlPoint[] cPoint;
	private static final Color color = Color.LIGHT_GRAY;
	private static final int thickness = 1;

	public BoundBox(BoundBox box) {
		this(box.x, box.y, box.width, box.height);
		initControlPoints();
	}

	public BoundBox(int x, int y, int width, int height) {
		super(x, y, width, height);
		cPoint = new ControlPoint[Cardinal.values().length];
	}

	public void normalize() {
		if (width < 0) {
			width *= -1;
			x -= width;
		}

		if (height >= 0)
			return;
		height *= -1;
		y -= height;
	}

	public Point getPosControlPoint(Cardinal cardinal) {
		for (ControlPoint cp : cPoint)
			if (cp.cardinal == cardinal)
				return cp.getLocation();
		return null;
	}

	public void initControlPoints() {
		for (int i = 0; i < cPoint.length; ++i)
			cPoint[i] = new ControlPoint(this, Cardinal.values()[i]);
	}

	@Override
	public void paint(Graphics2D g) {
		initControlPoints();

		Graphics2D graphics = (Graphics2D) g;
		graphics.setStroke(new BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f,
				new float[] { 10.0f, 2.0f }, 0.0f));
		graphics.setColor(color);
		graphics.drawRect(x, y, width, height);

		for (ControlPoint point : cPoint)
			point.paint(g);
	}

	public void updateSize(Point base, Point point) {
		int width = point.x - base.x, height = point.y - base.y;
		this.x = base.x;
		this.y = base.y;

		setSize(width, height);
	}

	public static int getSizeControlPoint() {
		return ControlPoint.SIZE;
	}

	private static class ControlPoint extends Rectangle implements Shape {
		private static final long serialVersionUID = 1;
		private static final Color color = Color.BLUE;
		public static final int SIZE = 7;
		private final BoundBox bbox;
		private final Cardinal cardinal;

		public ControlPoint(final BoundBox bbox, final Cardinal cardinal) {
			this.bbox = bbox;
			this.cardinal = cardinal;
			setPosition(new Point(bbox.x - SIZE / 2, bbox.y - SIZE / 2));
			height = width = SIZE;
		}

		private void setPosition(Point position) {
			switch (cardinal) {
			default:
				break;
			case N:
				position.x += bbox.width / 2;
				break;
			case NE:
				position.x += bbox.width;
				break;
			case W:
				position.y += bbox.height / 2;
				break;
			case E:
				position.x += bbox.width;
				position.y += bbox.height / 2;
				break;
			case SW:
				position.y += bbox.height;
				break;
			case S:
				position.y += bbox.height;
				position.x += bbox.width / 2;
				break;
			case SE:
				position.y += bbox.height;
				position.x += bbox.width;
				break;
			}

			x = position.x;
			y = position.y;
		}

		@Override
		public void paint(Graphics2D g) {
			g.setColor(color);
			g.fillRect(x, y, width, height);
		}
	}

	public static boolean isEmptyBoundBox(Point p1, Point p2) {
		return (Math.abs(p2.x - p1.x) <= getSizeControlPoint() && Math.abs(p2.y - p1.y) <= getSizeControlPoint());
	}

	public Cardinal getFocusedControlPoint(Point p) {
		for (ControlPoint cp : cPoint)
			if (cp.contains(p))
				return cp.cardinal;
		return null;
	}

	public void moveTo(Point p) {
		x += p.x;
		y += p.y;
	}
}
