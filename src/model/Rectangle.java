package model;

import java.awt.Color;
import java.awt.Graphics2D;

import view.BoundBox;

public class Rectangle extends ClosedFigure {
	public Rectangle(BoundBox boundBox, Color color, int thickness, Color fillColor) {
		super(boundBox, color, thickness, fillColor);
	}

	@Override
	public void doPaint(Graphics2D g) {
		BoundBox bbox = getBoundBox();

		g.setColor(getFillColor());
		g.fillRect(bbox.x, bbox.y, bbox.width + 1, bbox.height + 1);
		g.setColor(getColor());
		g.drawRect(bbox.x, bbox.y, bbox.width + 1, bbox.height + 1);
	}
}
