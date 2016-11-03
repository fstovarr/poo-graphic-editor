package view;

import java.awt.Point;
import java.awt.event.MouseEvent;

import mediator.App;
import model.Figure;
import view.DrawingListener.DrawingEvent;

public abstract class CreationTool extends Tool {
	private Figure figure;
	private DrawingListener listener;

	public CreationTool(String iconPath, String name) {
		super(iconPath, name);
	}

	protected abstract void createInitialFigure(Point ptPressed);

	protected Figure getFigure() {
		return figure;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);

		Figure figure = getFigure();
		figure.getBoundBox().updateSize(getPtPressed(), e.getPoint());
		figure.getBoundBox().normalize();

		listener.update(DrawingEvent.MODIFIED);
	}

	public DrawingListener getListener() {
		return listener;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		createInitialFigure(e.getPoint());
		App.getInstance().addFigure(figure);
	}

	@Override
	protected void processMouse() {
		Point ptPressed = getPtPressed();
		Point ptReleased = getPtReleased();

		// TEMPLATE
		// 1. Non empty bounding box
		if (!BoundBox.isEmptyBoundBox(ptPressed, ptReleased)) {
			// 2. Create figure
			// Figure figure = createFigure(ptPressed, ptReleased);
			// 3. Check figure
			if (figure == null) {
				App.getInstance().deleteFigure(figure);
			} else {
				App.getInstance().deselectAll();
				App.getInstance().addEdit(new CreationEdit(figure));
			}
		} else {
			App.getInstance().deleteFigure(figure);
		}
	}

	@Override
	public int getShortcutKey() {
		return -1;
	}

	protected void setFigure(Figure figure) {
		this.figure = figure;
	}

	public void setListener(DrawingListener listener) {
		this.listener = listener;
	}
}
