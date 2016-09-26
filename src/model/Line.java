package model;

import java.awt.Color;
import java.awt.Graphics;

import view.BoundBox;

public class Line extends GeomFigure {

	public Line(BoundBox boundBox, Color color) {
		super(boundBox, color);
	}

	@Override
	protected boolean needsNormalization() {
		return false;
	}

	@Override
	public void doPaint(Graphics g) {
		BoundBox bbox = getBoundBox();
		g.setColor(getColor());

		// draw line
		g.drawLine(bbox.x, bbox.y, bbox.x + bbox.width, bbox.y + bbox.height);
	}
}
