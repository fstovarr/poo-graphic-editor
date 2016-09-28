package view;

import java.awt.Point;

import model.Ellipse;
import model.Figure;

public class EllipseCreationTool extends CreationTool {

	@Override
	protected Figure createFigure(Point ptPressed, Point ptReleased) {
		Ellipse ellipse = new Ellipse(
				new BoundBox(ptPressed.x, ptPressed.y, ptReleased.x - ptPressed.x, ptReleased.y - ptPressed.y), null, 6,
				null);
		ellipse.setSelected(true);
		return ellipse;
	}
}
