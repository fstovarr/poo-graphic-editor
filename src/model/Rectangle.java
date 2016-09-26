package model;

import java.awt.Color;
import java.awt.Graphics;

import view.BoundBox;

public class Rectangle extends ClosedFigure {

	public Rectangle(BoundBox boundBox, Color color) {
		super(boundBox, color);
	}
	
	@Override
	public void doPaint(Graphics g) {
		BoundBox bbox = getBoundBox();
		g.setColor(getColor());
		// draw rect
		g.drawRect(bbox.x, bbox.y, bbox.width, bbox.height);
	}
}
