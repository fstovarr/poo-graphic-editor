package view;

import java.awt.Point;

import model.Figure;
import model.Rectangle;

public class RectangleCreationTool extends CreationTool {

	public RectangleCreationTool(String iconPath) {
		super(iconPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Figure createFigure(Point ptPressed, Point ptReleased) {
		Rectangle rectangle = new Rectangle(
				new BoundBox(ptPressed.x, ptPressed.y, ptReleased.x - ptPressed.x, ptReleased.y - ptPressed.y), null, 1,
				null);
		rectangle.setSelected(true);
		return rectangle;
	}
}
