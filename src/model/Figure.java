package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import view.BoundBox;
import view.Cardinal;

public abstract class Figure implements Shape {
	private BoundBox boundBox, normalizedBoundBox;
	private Color color;
	private boolean selected;

	public Figure(BoundBox boundBox, Color color) {
		if (color == null)
			color = Color.BLACK;

		this.boundBox = boundBox;
		this.color = color;
		normalizedBoundBox = new BoundBox(boundBox);
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
		if (!needsNormalization())
			return normalizedBoundBox;
		boundBox.normalize();
		normalizedBoundBox.normalize();
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

		if (isSelected())
			getNormalizedBoundBox().paint(g);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	public void move(Point p) {
		boundBox.moveTo(p);
	}

	public void resize(Point point, Cardinal cardinal) {
		switch (cardinal) {
		case N:
			boundBox.y += point.y;
			boundBox.height -= point.y;
			break;
		case S:
			boundBox.height += point.y;
			break;
		case E:
			boundBox.width += point.x;
			break;
		case W:
			boundBox.width -= point.x;
			boundBox.x += point.x;
			break;
		case SE:
			boundBox.width += point.x;
			boundBox.height += point.y;
			break;
		case SW:
			boundBox.x += point.x;
			boundBox.width -= point.x;
			boundBox.height += point.y;
			break;
		case NE:
			boundBox.y += point.y;
			boundBox.width += point.x;
			boundBox.height -= point.y;
			break;
		case NW:
			boundBox.x += point.x;
			boundBox.y += point.y;
			boundBox.width -= point.x;
			boundBox.height -= point.y;
			break;
		default:
			break;
		}

		if (needsNormalization())
			this.boundBox.normalize();
	}

	public void setDimensions(Dimension dim, Point p) {
		getBoundBox().setSize(dim);
		getBoundBox().setLocation(p);
		getNormalizedBoundBox().setSize(dim);
		getNormalizedBoundBox().setLocation(p);
	}

}
