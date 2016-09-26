package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;

import model.Shape;

public class ControlPoint implements Shape {
	private static final Color color = Color.BLUE;
	public static final int SIZE = 7;
	private final BoundBox bbox;
	private final Cardinal cardinal;
	private final Point position;

	public static enum Cardinal {
		NW, N, NE, E, SE, S, SW, W;
	}

	public ControlPoint(final BoundBox bbox, final Cardinal cardinal) {
		this.bbox = bbox;
		this.cardinal = cardinal;
		position = new Point(bbox.x - SIZE / 2, bbox.y - SIZE / 2);
		setPosition(cardinal);
	}

	public Cursor getCursor() {
		Cursor cursor = null;
		return cursor;
	}

	private void setPosition(Cardinal cardinal) {
		switch (cardinal) {
		case NW:
			break;
			
		case N:
			position.x += bbox.width / 2;
			break;

		case NE:
			position.x += bbox.width;
			break;

		case W:
			position.y += bbox.height / 2;
			break;

		case E:
			position.x += bbox.width;
			position.y += bbox.height / 2;
			break;

		case SW:
			position.y += bbox.height;
			break;

		case S:
			position.y += bbox.height;
			position.x += bbox.width / 2;
			break;

		case SE:
			position.y += bbox.height;
			position.x += bbox.width;
			break;

		default:
			break;
		}
	}

	public Cardinal getCardinal() {
		return cardinal;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(position.x, position.y, SIZE, SIZE);
	}
}
