package model;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import view.BoundBox;
import view.DrawingListener;
import view.DrawingListener.DrawingEvent;

public class Drawing {
	private List<Figure> figures;
	private List<DrawingListener> listeners;
	private List<Figure> selectedFigures;

	public Drawing() {
		figures = new LinkedList<>();
		selectedFigures = new LinkedList<>();
		listeners = new LinkedList<>();
	}

	public void addFigure(final Figure figure) {
		figures.add(figure);
		notifyListeners(DrawingEvent.ADDED);
	}

	private void notifyListeners(DrawingEvent event) {
		for (DrawingListener listener : listeners) {
			listener.update(event);
		}
	}

	public void addListener(DrawingListener listener) {
		listeners.add(listener);
	}

	public void clear() {
		figures.clear();
		selectedFigures.clear();
		notifyListeners(DrawingEvent.DELETED);
	}

	public void deleteSelected() {
		Iterator<Figure> iSelected = selectedFigures.iterator();

		while (iSelected.hasNext()) {
			removeFigure(iSelected.next());
			iSelected.remove();
		}

		notifyListeners(DrawingEvent.DESELECTED);
		notifyListeners(DrawingEvent.DELETED);
	}

	public void deselectAll() {
		for (Figure figure : figures) {
			figure.setSelected(false);
		}
		selectedFigures.clear();
		notifyListeners(DrawingEvent.DESELECTED);
	}

	public Iterator<Figure> getIterator() {
		return figures.iterator();
	}

	private void removeFigure(final Figure figure) {
		figures.remove(figure);
		notifyListeners(DrawingEvent.DELETED);
	}

	private void select(BoundBox box) {
		ListIterator<Figure> iterator = figures.listIterator(figures.size());

		while (iterator.hasPrevious()) {
			Figure figure = iterator.previous();
			BoundBox boxFigure = figure.getNormalizedBoundBox();
			if (box.contains(boxFigure)) {
				figure.setSelected(true);
				selectedFigures.add(figure);
			}
		}
		notifyListeners(DrawingEvent.SELECTED);
	}

	public void select(Figure figure) {
		figure.setSelected(true);
		selectedFigures.add(figure);
		notifyListeners(DrawingEvent.SELECTED);
	}

	public void select(Point p1, Point p2) {
		ListIterator<Figure> iterator = figures.listIterator(figures.size());
		boolean bool = BoundBox.isEmptyBoundBox(p1, p2);
		if (bool) {
			while (iterator.hasPrevious()) {
				Figure figure = iterator.previous();
				BoundBox boxFigure = figure.getNormalizedBoundBox();

				if (boxFigure.contains(p1)) {
					select(figure);
					break;
				}
			}
		} else {
			BoundBox box = new BoundBox(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
			box.normalize();
			select(box);
		}
	}

	public void selectAll() {
		for (Figure figure : figures) {
			figure.setSelected(true);
			selectedFigures.add(figure);
		}
		notifyListeners(DrawingEvent.SELECTED);
	}
}
