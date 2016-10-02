package view;

import java.awt.Point;

import model.Figure;

public class TextCreationTool extends CreationTool {

	public TextCreationTool() {
		super("resources/bwicons/font2.png", "Texto");
	}

	@Override
	protected Figure createFigure(Point ptPressed, Point ptReleased) {
		return null;
	}
}
