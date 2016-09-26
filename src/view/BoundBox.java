package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import model.Shape;

public class BoundBox extends Rectangle implements Shape {
	private static final long serialVersionUID = 1L;
	private ControlPoint[] cPoint;
	private static final Color color = Color.LIGHT_GRAY;

	public BoundBox(int x, int y, int width, int height) {
		super(x, y, width, height);
		cPoint = new ControlPoint[ControlPoint.Cardinal.values().length];
	}

	public BoundBox(BoundBox bbox) {
		this(bbox.x, bbox.y, bbox.width, bbox.height);
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

		g.setColor(color);
		// draw rect
		g.drawRect(x, y, width, height);

		for (ControlPoint point : cPoint) {
			point.paint(g);
		}
	}
}
