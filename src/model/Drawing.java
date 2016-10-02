package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import view.BoundBox;

public class Drawing {
	private List<Figure> figures;
	private List<Figure> selectedFigures;

	public Drawing() {
		figures = new LinkedList<Figure>();
		selectedFigures = new ArrayList<>();
	}

	public void addFigure(final Figure figure) {
		figures.add(figure);
	}

	private void removeFigure(final Figure figure) {
		figures.remove(figure);
	}

	public Iterator<Figure> getIterator() {
		return figures.iterator();
	}

	public void deselectAll() {
		for (Figure figure : figures) {
			figure.setSelected(false);
		}

		selectedFigures.clear();
	}

	public void select(Point p1, Point p2) {
		ListIterator<Figure> iterator = figures.listIterator(figures.size());

		if (BoundBox.isEmptyBoundBox(p1, p2)) {
			while (iterator.hasPrevious()) {
				Figure figure = iterator.previous();
				BoundBox boxFigure = figure.getNormalizedBoundBox();

				if (boxFigure.contains(p1)) {
					figure.setSelected(true);
					selectedFigures.add(figure);
					break;
				} else {
					figure.setSelected(false);
					selectedFigures.remove(figure);
				}
			}
		} else {
			BoundBox box = new BoundBox(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
			box.normalize();
			select(box);
		}
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
	}

	public void select(Figure figure) {
		figure.setSelected(true);
		selectedFigures.add(figure);
	}

	public void deleteSelected() {
		Iterator<Figure> iSelected = selectedFigures.iterator();

		while (iSelected.hasNext()) {
			removeFigure(iSelected.next());
			iSelected.remove();
		}
	}
}
