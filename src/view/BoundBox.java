package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	public void paint(Graphics g) {
		for (int i = 0; i < cPoint.length; i++) {
			cPoint[i] = new ControlPoint(this, ControlPoint.Cardinal.values()[i]);
		}

		Graphics2D graphics = (Graphics2D) g;
		graphics.setStroke(new BasicStroke(thickness));
		graphics.setColor(color);
		// graphics.drawRect(box.x, box.y, box.width, box.height);
		graphics.drawRect(x, y, width, height);

		for (ControlPoint point : cPoint) {
			point.paint(g);
		}
	}
}
