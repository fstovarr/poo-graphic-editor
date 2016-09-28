package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import view.BoundBox;

public abstract class Figure implements Shape {
	private BoundBox boundBox, boxOriginal;
	private Color color;
	private boolean selected;

	public Figure(BoundBox boundBox, Color color) {
		super();
		if (color == null) {
			color = Color.BLACK;
		}

		this.boundBox = boundBox;
		this.color = color;
		boxOriginal = new BoundBox(boundBox);

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
		return boxOriginal;
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

		boxOriginal.x = boundBox.x - thickness / 2 - 2;
		boxOriginal.y = boundBox.y - thickness / 2 - 2;
		boxOriginal.width = boundBox.width + thickness + 2;
		boxOriginal.height = boundBox.height + thickness + 2;

		if (isSelected()) {
			getNormalizedBoundBox().paint(graphics);
		}
	}
}
