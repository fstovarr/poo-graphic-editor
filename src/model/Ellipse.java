package model;

import java.awt.Color;
import java.awt.Graphics;

import view.BoundBox;

public class Ellipse extends ClosedFigure {

	public Ellipse(BoundBox boundBox, Color color) {
		super(boundBox, color);
	}

	@Override
	public void doPaint(Graphics g) {
		BoundBox bbox = getBoundBox();

		g.setColor(getColor());
		g.drawOval(bbox.x, bbox.y, bbox.width, bbox.height);
		System.out.println("Corrido2");
	}
}
