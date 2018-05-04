package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import view.BoundBox;
import view.Cardinal;

public class Line extends GeometricFigure {
	public Line(BoundBox boundBox, Color color, int thickness) {
		super(boundBox, color, thickness);
	}

	@Override
	public void resize(Point point, Cardinal cardinal) {
		BoundBox boundBox = getBoundBox();

		switch (cardinal) {
		case N:
			boundBox.height += point.y;
			break;
		case S:
			boundBox.y += point.y;
			boundBox.height -= point.y;
			break;
		case E:
			boundBox.x += point.x;
			boundBox.width -= point.x;
			break;
		case W:
			boundBox.width += point.x;
			break;
		case SE:
			boundBox.y += point.y;
			boundBox.x += point.x;
			boundBox.height -= point.y;
			boundBox.width -= point.x;
			break;
		case SW:
			boundBox.width += point.x;
			boundBox.height -= point.y;
			boundBox.y += point.y;
			break;
		case NE:
			boundBox.height += point.y;
			boundBox.width += point.x;
			break;
		case NW:
			boundBox.height += point.y;
			boundBox.width += point.x;
			break;
		default:
			break;
		}

		// if (needsNormalization()) {
		// boundBox.normalize();
		// }
	}

	@Override
	protected boolean needsNormalization() {
		return false;
	}

	@Override
	public void doPaint(Graphics2D g) {
		BoundBox bbox = getBoundBox();
		g.drawLine(bbox.x, bbox.y, bbox.x + bbox.width, bbox.y + bbox.height);
	}

	@Override
	public BoundBox getNormalizedBoundBox() {
		BoundBox box = super.getNormalizedBoundBox();
		box.normalize();
		return box;
	}
}