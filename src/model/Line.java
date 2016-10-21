package model;

import java.awt.Color;
import java.awt.Graphics2D;

import view.BoundBox;

public class Line extends GeometricFigure {
	public Line(BoundBox boundBox, Color color, int thickness) {
		super(boundBox, color, thickness);
	}

	@Override
	protected boolean needsNormalization() {
		return false;
	}

	@Override
	public void doPaint(Graphics2D g) {
		BoundBox bbox = getBoundBox();
		g.drawLine(bbox.x, bbox.y, bbox.x + bbox.width, bbox.y + bbox.height);
	}

	@Override
	public BoundBox getNormalizedBoundBox() {
		BoundBox box = super.getNormalizedBoundBox();
		box.normalize();
		return box;
	}
}