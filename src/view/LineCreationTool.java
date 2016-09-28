package view;

import java.awt.Color;
import java.awt.Point;

import model.Figure;
import model.Line;

public class LineCreationTool extends CreationTool {
	@Override
	protected Figure createFigure(Point ptPressed, Point ptReleased) {
		Line line = new Line(
				new BoundBox(ptPressed.x, ptPressed.y, ptReleased.x - ptPressed.x, ptReleased.y - ptPressed.y),
				Color.BLACK, 1);
		line.setSelected(true);
		return line;
	}
}
