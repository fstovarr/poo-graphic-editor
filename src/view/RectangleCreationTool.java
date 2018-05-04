package view;

import java.awt.Point;

import model.Figure;
import model.Rectangle;

public class RectangleCreationTool extends CreationTool {

	public RectangleCreationTool() {
		super("resources/bwicons/rectangle1.png", "Rectangle Creation");
	}

	@Override
	protected Figure createFigure(Point ptPressed, Point ptReleased) {
		return new Rectangle(
				new BoundBox(ptPressed.x, ptPressed.y, ptReleased.x - ptPressed.x, ptReleased.y - ptPressed.y), null, 1,
				null);
	}
}
