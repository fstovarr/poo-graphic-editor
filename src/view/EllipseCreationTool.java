package view;

import java.awt.Point;

import model.Ellipse;
import model.Figure;

public class EllipseCreationTool extends CreationTool {
	public EllipseCreationTool() {
		super("resources/bwicons/ellipse1.png", "Elipse");
	}

	@Override
	protected Figure createFigure(Point ptPressed, Point ptReleased) {
		return new Ellipse(
				new BoundBox(ptPressed.x, ptPressed.y, ptReleased.x - ptPressed.x, ptReleased.y - ptPressed.y), null, 1,
				null);
	}
}
