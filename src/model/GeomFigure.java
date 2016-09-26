package model;

import java.awt.Color;

import view.BoundBox;

public abstract class GeomFigure extends Figure {
	public GeomFigure(BoundBox boundBox, Color color) {
		super(boundBox, color);
	}
}
