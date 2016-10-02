package view;

import java.awt.Point;

import model.Figure;
import model.Line;

public class LineCreationTool extends CreationTool {
	public LineCreationTool() {
		super("resources/bwicons/line1.png", "Línea");
	}

	@Override
	protected Figure createFigure(Point ptPressed, Point ptReleased) {
		return new Line(new BoundBox(ptPressed.x, ptPressed.y, ptReleased.x - ptPressed.x, ptReleased.y - ptPressed.y),
				null, 1);
	}
}
