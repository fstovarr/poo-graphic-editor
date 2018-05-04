package view;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import mediator.App;
import model.Figure;

public class SelectionTool extends Tool {

	public SelectionTool() {
		super("resources/bwicons/selection1.png", "Selection Tool");
	}

	@Override
	protected void processMouse() {
		Point ptPressed = getPtPressed();
		Point ptReleased = getPtReleased();
		App.getInstance().deselectAll();
		App.getInstance().selectFigure(ptPressed, ptReleased);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		Iterator<Figure> iterator = App.getInstance().getFiguresIterator();
		Point p = e.getPoint();

		while (iterator.hasNext()) {
			Figure figure = (Figure) iterator.next();
			Cardinal cardinal = figure.getNormalizedBoundBox().getFocusedControlPoint(p);

			if (figure.isSelected() && cardinal != null) {
				App.getInstance().setActiveTool(new ResizeTool(figure, cardinal, getPtPressed()));
				return;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		List<Figure> selected = App.getInstance().getSelectedFigures();
		for (Figure f : selected) {
			if (f.getNormalizedBoundBox().contains(getPtPressed())) {
				App.getInstance().setActiveTool(new MoveTool(getPtPressed()));
				return;
			}
		}
	}

	@Override
	public int getShortcutKey() {
		return -1;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Cursor currentCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		Iterator<Figure> iterator = App.getInstance().getFiguresIterator();
		Point p = e.getPoint();

		while (iterator.hasNext()) {
			Figure figure = (Figure) iterator.next();
			Cardinal cardinal = figure.getNormalizedBoundBox().getFocusedControlPoint(p);

			if (figure.isSelected() && cardinal != null) {
				switch (cardinal) {
				case N:
					currentCursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
					break;
				case E:
					currentCursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
					break;

				case S:
					currentCursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
					break;

				case W:
					currentCursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
					break;

				case NE:
					currentCursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
					break;

				case NW:
					currentCursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
					break;

				case SE:
					currentCursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
					break;

				case SW:
					currentCursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
					break;

				default:
					break;
				}
			} else if (figure.getNormalizedBoundBox().contains(p)) {
				currentCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
			}
			App.getInstance().setCursor(currentCursor);
		}

	}
}
