package model;

import java.awt.Color;
import java.awt.Graphics2D;

import view.BoundBox;

public class Ellipse extends ClosedFigure {
	public Ellipse(BoundBox boundBox, Color color, int thicknes, Color fillColor) {
		super(boundBox, color, thicknes, fillColor);
	}

	@Override
	public void doPaint(Graphics2D g) {
		BoundBox bbox = getBoundBox();

		g.setColor(getFillColor());
		g.fillOval(bbox.x, bbox.y, bbox.width, bbox.height);
		g.setColor(getColor());
		g.drawOval(bbox.x, bbox.y, bbox.width, bbox.height);
	}
}
