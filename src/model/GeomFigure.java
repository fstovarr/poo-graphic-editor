package model;

import java.awt.Color;

import view.BoundBox;

public abstract class GeomFigure extends Figure {
	private int thickness;

	public GeomFigure(BoundBox boundBox, Color color, int thickness) {
		super(boundBox, color);
		this.thickness = thickness;
	}

	public int getThickness() {
		return thickness;
	}
}
