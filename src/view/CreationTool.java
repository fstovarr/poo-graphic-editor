package view;

import java.awt.Point;

import mediator.App;
import model.Figure;

public abstract class CreationTool extends Tool {
	public CreationTool(String iconPath, String name) {
		super(iconPath, name);
	}

	protected abstract Figure createFigure(Point ptPressed, Point ptReleased);

	@Override
	protected void processMouse() {
		Point ptPressed = getPtPressed(), ptReleased = getPtReleased();
		if (BoundBox.isEmptyBoundBox(ptPressed, ptReleased))
			return;
		Figure figure = createFigure(ptPressed, ptReleased);
		if (figure == null)
			return;
		App.getInstance().addFigure(figure);
		App.getInstance().deselectAll();
		App.getInstance().addEdit(new CreationEdit(figure));
		App.getInstance().selectFigure(figure);
	}

	@Override
	public int getShortcutKey() {
		return -1;
	}
}
