package view;

import java.awt.event.MouseEvent;

import mediator.App;

public class MoveTool extends SelectionTool {
	public MoveTool() {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		App.getInstance().selectFigure(getPtPressed(), getPtPressed());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		App.getInstance().moveSelectedFigures(getPtPressed(), e.getPoint());
	}

	@Override
	protected void processMouse() {
		App.getInstance().moveSelectedFigures(getPtPressed(), null);
	}
}