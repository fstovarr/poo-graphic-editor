package view;

import java.awt.Point;

import mediator.App;

public class SelectionTool extends InteractiveTool {

	public SelectionTool() {
		super("resources/bwicons/selection1.png", "Seleccion");
	}

	@Override
	protected void processMouse() {
		Point ptPressed = getPtPressed();
		Point ptReleased = getPtReleased();
		App.getInstance().selectFigures(ptPressed, ptReleased);
	}
}
