package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import view.BoundBox;
import view.Cardinal;

public class Rhombus extends ClosedFigure {
	private static final long serialVersionUID = 1;

	public Rhombus(BoundBox boundBox, Color color, int thickness, Color fillColor) {
		super(boundBox, color, thickness, fillColor);
	}

	@Override
	protected void doPaint(Graphics2D g) {
		BoundBox bbox = getNormalizedBoundBox();

		Point n = bbox.getPosControlPoint(Cardinal.N), s = bbox.getPosControlPoint(Cardinal.S),
				w = bbox.getPosControlPoint(Cardinal.W), e = bbox.getPosControlPoint(Cardinal.E);
		final int diff = BoundBox.getSizeControlPoint() / 2;

		int[] x = { n.x + diff, w.x + diff, s.x + diff, e.x + diff },
				y = { n.y + diff, w.y + diff, s.y + diff, e.y + diff };
		Polygon p = new Polygon(x, y, 4);

		g.setColor(getFillColor());
		g.fillPolygon(p);
		g.setColor(getColor());
		g.drawPolygon(p);
	}
}
