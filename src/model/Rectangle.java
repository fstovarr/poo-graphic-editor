package model;

import java.awt.Color;
import java.awt.Graphics;

import view.BoundBox;

public class Rectangle extends ClosedFigure {

	public Rectangle(BoundBox boundBox, Color color, int thickness, Color fillColor) {
		super(boundBox, color, thickness, fillColor);
	}

	@Override
	public void doPaint(Graphics g) {
		BoundBox bbox = getBoundBox();
		g.drawRect(bbox.x, bbox.y, bbox.width, bbox.height);

		int thickness = getThickness();
		g.setColor(getFillColor());
		g.fillRect(bbox.x + thickness / 2 + 1, bbox.y + thickness / 2 + 1, bbox.width - thickness,
				bbox.height - thickness);
	}
}
