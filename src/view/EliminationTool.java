package view;

import mediator.App;

public class EliminationTool extends ActionTool {
	public EliminationTool() {
		super("resources/bwicons/eraser1.png", "Eliminaci�n");
	}

	@Override
	public void applyTool() {
		App.getInstance().deleteSelectedFigures();
	}
}
