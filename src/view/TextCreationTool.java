package view;

import java.awt.Point;

import model.Text;

public class TextCreationTool extends CreationTool {

	public TextCreationTool() {
		super("resources/bwicons/font2.png", "Text Creation");
	}

	@Override
	protected void createInitialFigure(Point ptPressed) {
		setFigure(new Text(new BoundBox(ptPressed.x, ptPressed.y, 0, 0), "Hola mundo", null));
	}
}
