package view;

import java.awt.Point;

import model.Figure;
import model.Line;

public class LineCreationTool extends CreationTool {
	public LineCreationTool(String iconPath) {
		super(iconPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Figure createFigure(Point ptPressed, Point ptReleased) {
		Line line = new Line(
				new BoundBox(ptPressed.x, ptPressed.y, ptReleased.x - ptPressed.x, ptReleased.y - ptPressed.y), null,
				1);
		line.setSelected(true);
		return line;
	}
}
