package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import model.Shape;

public class BoundBox extends Rectangle implements Shape {
	private static final long serialVersionUID = 1L;
	private ControlPoint[] cPoint;
	private static final Color color = Color.LIGHT_GRAY;
	private static final int thickness = 1;

	public BoundBox(BoundBox box) {
		this(box.x, box.y, box.width, box.height);
	}

	public BoundBox(int x, int y, int width, int height) {
		super(x, y, width, height);
		cPoint = new ControlPoint[ControlPoint.Cardinal.values().length];
	}

	public void normalize() {
		if (width < 0) {
			width *= -1;
			x -= width;
		}

		if (height < 0) {
			height *= -1;
			y -= height;
		}
	}

	@Override
	public void paint(Graphics2D g) {
		for (int i = 0; i < cPoint.length; i++) {
			cPoint[i] = new ControlPoint(this, ControlPoint.Cardinal.values()[i]);
		}

		Graphics2D graphics = (Graphics2D) g;
		graphics.setStroke(new BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f,
				new float[] { 10, 2 }, 0.0f));

		graphics.setColor(color);
		graphics.drawRect(x, y, width, height);

		for (ControlPoint point : cPoint) {
			point.paint(g);
		}
	}

	public static int getSizeControlPoint() {
		return ControlPoint.SIZE;
	}

	private static class ControlPoint implements Shape {
		private static final Color color = Color.BLUE;
		public static final int SIZE = 7;
		private final BoundBox bbox;
		private final Cardinal cardinal;
		private final Point position;

		public static enum Cardinal {
			NW, N, NE, E, SE, S, SW, W;
		}

		public ControlPoint(final BoundBox bbox, final Cardinal cardinal) {
			this.bbox = bbox;
			this.cardinal = cardinal;
			position = new Point(bbox.x - SIZE / 2, bbox.y - SIZE / 2);
			setPosition();
		}

		private void setPosition() {
			switch (cardinal) {
			case NW:
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

			default:
				break;
			}
		}

		@Override
		public void paint(Graphics2D g) {
			g.setColor(color);
			g.fillRect(position.x, position.y, SIZE, SIZE);
		}
	}

	public static boolean isEmptyBoundBox(Point p1, Point p2) {
		return !(Math.abs((p2.x - p1.x)) >= getSizeControlPoint() && Math.abs((p2.y - p1.y)) >= getSizeControlPoint());
	}
}
