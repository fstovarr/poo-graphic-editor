package view;

import java.awt.Point;
import java.awt.event.MouseEvent;

import mediator.App;

public class MoveTool extends SelectionTool {
	public MoveTool(Point ptPressed) {
		setPtPressed(ptPressed);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		App.getInstance().setActiveTool(new SelectionTool());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = new Point(e.getPoint().x - getPtPressed().x, e.getPoint().y - getPtPressed().y);
		setPtPressed(e.getPoint());
		App.getInstance().moveSelectedFigures(p);
	}
}