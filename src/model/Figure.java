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
		this.boundBox = boundBox;
		this.color = color;

		// Template Method
		if (needsNormalization()) {
			this.boundBox.normalize();
		}
		
		this.boxOriginal = new BoundBox(boundBox.x, boundBox.y, boundBox.width, boundBox.height);

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

		// boxOriginal.x = boundBox.y + thickness;
		//boundBox.y = boxOriginal.y + 100;

		System.out.println("Paint By = " + boxOriginal.y);

		if (isSelected()) {
			boundBox.paint(graphics);
		}
	}
}
