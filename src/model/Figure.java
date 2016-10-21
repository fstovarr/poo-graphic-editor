package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import view.BoundBox;

public abstract class Figure implements Shape {
	private BoundBox boundBox, normalizedBoundBox;
	private Color color;
	private boolean selected;

	public Figure(BoundBox boundBox, Color color) {
		super();
		if (color == null) {
			color = Color.BLACK;
		}

		this.boundBox = boundBox;
		this.color = color;
		normalizedBoundBox = new BoundBox(boundBox);

		// Template Method
		if (needsNormalization()) {
			this.boundBox.normalize();
		}
	}

	protected abstract void doPaint(Graphics2D g);

	protected boolean needsNormalization() {
		return true;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public BoundBox getBoundBox() {
		return boundBox;
	}

	public BoundBox getNormalizedBoundBox() {
		return normalizedBoundBox;
	}

	@Override
	public void paint(Graphics2D g) {
		int thickness = 0;

		g.setColor(color);

		if (this instanceof GeometricFigure) {
			thickness = ((GeometricFigure) this).getThickness();
			g.setStroke(new BasicStroke(thickness));
		}

		doPaint(g);

		normalizedBoundBox.x = boundBox.x - thickness / 2;
		normalizedBoundBox.y = boundBox.y - thickness / 2;
		normalizedBoundBox.width = boundBox.width + thickness;
		normalizedBoundBox.height = boundBox.height + thickness;

		if (isSelected()) {
			getNormalizedBoundBox().paint(g);
		}
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
