package model;

import java.awt.Color;
import java.awt.Graphics;

import view.BoundBox;

public class Ellipse extends ClosedFigure {

	public Ellipse(BoundBox boundBox, Color color, int thicknes, Color fillColor) {
		super(boundBox, color, thicknes, fillColor);
	}

	@Override
	public void doPaint(Graphics g) {
		BoundBox bbox = getBoundBox();
		g.drawOval(bbox.x, bbox.y, bbox.width, bbox.height);
		g.setColor(getFillColor());
		g.fillOval(bbox.x, bbox.y, bbox.width, bbox.height);
	}
}
