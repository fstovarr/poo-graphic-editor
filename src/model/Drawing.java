package model;

import java.awt.Color;
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
	private String pathName = "New graphic";

	public Drawing() {
		figures = new LinkedList<>();
		selectedFigures = new LinkedList<>();
		listeners = new LinkedList<>();
	}

	public void addFigure(final Figure figure) {
		figures.add(figure);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void addListener(DrawingListener listener) {
		listeners.add(listener);
	}

	public void changeFillColor(Color color) {
		for (Figure figure : selectedFigures) {
			if (figure instanceof ClosedFigure) {
				((ClosedFigure) figure).setFillColor(color);
			}
		}
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void changeStrokeColor(Color color) {
		for (Figure figure : selectedFigures) {
			if (figure instanceof ClosedFigure) {
				((ClosedFigure) figure).setColor(color);
			}
		}
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void changeThickness(int thickness) {
		for (Figure figure : selectedFigures) {
			if (figure instanceof GeometricFigure) {
				((GeometricFigure) figure).setThickness(thickness);
			}
		}
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void clear() {
		figures.clear();
		selectedFigures.clear();
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void deleteFigure(Figure figure) {
		figures.remove(figure);
		selectedFigures.remove(figures);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void deleteSelected() {
		Iterator<Figure> iSelected = selectedFigures.iterator();

		while (iSelected.hasNext()) {
			removeFigure(iSelected.next());
			iSelected.remove();
		}

		notifyListeners(DrawingEvent.DESELECTED);
		notifyListeners(DrawingEvent.MODIFIED);
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

	public String getPathName() {
		return pathName;
	}

	private void notifyListeners(DrawingEvent event) {
		for (DrawingListener listener : listeners) {
			listener.update(event);
		}
	}

	private void removeFigure(final Figure figure) {
		figures.remove(figure);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	private void select(BoundBox box) {
		ListIterator<Figure> iterator = figures.listIterator(figures.size());

		while (iterator.hasPrevious()) {
			Figure figure = iterator.previous();
			BoundBox boxFigure = figure.getNormalizedBoundBox();
			if (box.contains(boxFigure)) {
				select(figure);
			}
		}
	}

	public void select(Figure figure) {
		figure.setSelected(true);
		selectedFigures.add(figure);
		if (!selectedFigures.isEmpty()) {
			notifyListeners(DrawingEvent.SELECTED);
		}
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

		if (!selectedFigures.isEmpty()) {
			notifyListeners(DrawingEvent.SELECTED);
		}
	}

	public void save() {
		notifyListeners(DrawingEvent.SAVED);
	}
}
