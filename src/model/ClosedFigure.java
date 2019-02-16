package model;

import java.awt.Color;

import view.BoundBox;

public abstract class ClosedFigure extends GeometricFigure {
	private Color fillColor;

	public ClosedFigure(BoundBox boundBox, Color color, int thicknes, Color fillColor) {
		super(boundBox, color, thicknes);

		if (fillColor == null)
			fillColor = new Color(255, 255, 255, 0);

		this.fillColor = fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public Color getFillColor() {
		return fillColor;
	}
}
