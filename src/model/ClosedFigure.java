package model;

import java.awt.Color;

import view.BoundBox;

public abstract class ClosedFigure extends GeomFigure {
	private Color fillColor;

	public ClosedFigure(BoundBox boundBox, Color color, int thicknes, Color fillColor) {
		super(boundBox, color, thicknes);
		this.fillColor = fillColor;
	}

	public Color getFillColor() {
		return fillColor;
	}
}
