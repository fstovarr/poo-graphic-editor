package model;

import java.awt.Color;

import view.BoundBox;

public abstract class ClosedFigure extends GeomFigure {
	public ClosedFigure(BoundBox boundBox, Color color) {
		super(boundBox, color);
	}
}
