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

		int thickness = getThickness();
		g.setColor(getFillColor());
		g.fillOval(bbox.x + thickness / 2, bbox.y + thickness / 2, bbox.width - thickness, bbox.height - thickness);
	}
}
