package view;

import mediator.App;

public class EliminationTool extends ActionTool {
	public EliminationTool() {
		super("resources/bwicons/eraser1.png", "Eliminación");
	}

	@Override
	public void applyTool() {
		App.getInstance().deleteSelectedFigures();
	}
}
