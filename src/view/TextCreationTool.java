package view;

import java.awt.Point;

import model.Figure;
import model.Text;

public class TextCreationTool extends CreationTool {

	public TextCreationTool() {
		super("resources/bwicons/font2.png", "Text Creation");
	}

	@Override
	protected Figure createFigure(Point ptPressed, Point ptReleased) {
		return new Text(new BoundBox(ptPressed.x, ptPressed.y, ptReleased.x - ptPressed.x, ptReleased.y - ptPressed.y),
				"Hola mundo", null);
	}
}
