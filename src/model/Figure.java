package model;

import java.awt.Color;
import java.awt.Graphics;

import view.BoundBox;

public abstract class Figure implements Shape {
	private BoundBox boundBox;
	private Color color;
	private boolean selected;

	public Figure(BoundBox boundBox, Color color) {
		super();
		this.boundBox = boundBox;
		this.color = color;

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

	public void setBoundBox(BoundBox boundBox) {
		this.boundBox = boundBox;
	}

	// Template Method
	public final void paint(Graphics g) {
		doPaint(g);

		if (isSelected()) {
			boundBox.paint(g);
		}
	}
}
