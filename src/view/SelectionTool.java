package view;

import java.awt.Point;

import mediator.App;

public class SelectionTool extends Tool {

	public SelectionTool() {
		super("resources/bwicons/selection1.png", "Selection Creation");
	}

	@Override
	protected void processMouse() {
		Point ptPressed = getPtPressed();
		Point ptReleased = getPtReleased();
		App.getInstance().deselectAll();
		App.getInstance().selectFigure(ptPressed, ptReleased);
	}
}
