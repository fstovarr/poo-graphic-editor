package view;

import java.awt.Point;
import java.awt.event.MouseEvent;

import mediator.App;
import model.Figure;

public abstract class CreationTool extends Tool {

	protected abstract Figure createFigure(Point ptPressed, Point ptReleased);

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		Point ptPressed = getPtPressed();
		Point ptReleased = getPtReleased();

		// TEMPLATE
		// 1. Non empty bounding box
		if (ptPressed.equals(ptReleased) == false) {
			// 2. Create figure
			Figure figure = createFigure(ptPressed, ptReleased);
			// 3. Check figure
			if (figure != null) {
				// 4. Add figure to model
				App.getInstance().addFigure(figure);
			}
		}
	}
}
