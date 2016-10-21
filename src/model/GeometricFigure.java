package model;

import java.awt.Color;

import view.BoundBox;

public abstract class GeometricFigure extends Figure {
	private int thickness;

	public GeometricFigure(BoundBox boundBox, Color color, int thickness) {
		super(boundBox, color);
		this.thickness = thickness;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
}
