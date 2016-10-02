package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
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

	protected abstract void doPaint(Graphics g);

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
	public void paint(Graphics g) {
		int thickness = 0;

		Graphics2D graphics = (Graphics2D) g;
		graphics.setColor(color);

		if (this instanceof GeomFigure) {
			thickness = ((GeomFigure) this).getThickness();
			graphics.setStroke(new BasicStroke(thickness));
		}

		doPaint(graphics);

		normalizedBoundBox.x = boundBox.x - thickness / 2;
		normalizedBoundBox.y = boundBox.y - thickness / 2;
		normalizedBoundBox.width = boundBox.width + thickness;
		normalizedBoundBox.height = boundBox.height + thickness;

		if (isSelected()) {
			getNormalizedBoundBox().paint(graphics);
		}
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
