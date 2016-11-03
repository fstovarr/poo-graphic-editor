package view;

import java.awt.Color;
import java.awt.Point;

import model.Line;

public class LineCreationTool extends CreationTool {
	public LineCreationTool() {
		super("resources/bwicons/line1.png", "Line Creation");
	}

	@Override
	protected void createInitialFigure(Point ptPressed) {
		setFigure(new Line(new BoundBox(ptPressed.x, ptPressed.y, 0, 0), Color.BLACK, 1));
	}
}
