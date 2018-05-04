package view;

import java.awt.Point;

import model.Figure;
import model.Rhombus;

public class RomboCreationTool extends CreationTool {

	public RomboCreationTool() {
		super(null, "Rhombus");
	}

	@Override
	protected Figure createFigure(Point ptPressed, Point ptReleased) {
		return new Rhombus(new BoundBox(ptPressed.x, ptPressed.y, ptReleased.x - ptPressed.x, ptReleased.y - ptPressed.y), null, 1, null);
	}
}
