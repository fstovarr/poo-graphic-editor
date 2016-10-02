package view;

import java.awt.Point;

import mediator.App;
import model.Figure;

public abstract class CreationTool extends InteractiveTool {

	public CreationTool(String iconPath, String name) {
		super(iconPath, name);
	}

	protected abstract Figure createFigure(Point ptPressed, Point ptReleased);

	@Override
	protected void processMouse() {
		Point ptPressed = getPtPressed();
		Point ptReleased = getPtReleased();

		// TEMPLATE
		// 1. Non empty bounding box
		if (!BoundBox.isEmptyBoundBox(ptPressed, ptReleased)) {
			// 2. Create figure
			Figure figure = createFigure(ptPressed, ptReleased);
			// 3. Check figure
			if (figure != null) {
				// 4. Add figure to model
				App.getInstance().addFigure(figure);
				App.getInstance().selectFigure(figure);
			}
		}
	}
}
