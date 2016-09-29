package view;

import java.awt.Point;

import model.Ellipse;
import model.Figure;

public class EllipseCreationTool extends CreationTool {

	public EllipseCreationTool(String iconPath) {
		super(iconPath);
	}

	@Override
	protected Figure createFigure(Point ptPressed, Point ptReleased) {
		Ellipse ellipse = new Ellipse(
				new BoundBox(ptPressed.x, ptPressed.y, ptReleased.x - ptPressed.x, ptReleased.y - ptPressed.y), null,
				1, null);
		ellipse.setSelected(true);
		return ellipse;
	}
}
