package view;

import java.awt.Point;

import model.Rectangle;

public class RectangleCreationTool extends CreationTool {

	public RectangleCreationTool() {
		super("resources/bwicons/rectangle1.png", "Rectangle Creation");
	}

	@Override
	protected void createInitialFigure(Point ptPressed) {
		setFigure(new Rectangle(new BoundBox(ptPressed.x, ptPressed.y, 0, 0), null, 1, null));
	}
}
