package model;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import view.BoundBox;

public class Drawing {
	private List<Figure> figures;

	public Drawing() {
		figures = new LinkedList<Figure>();
	}

	public void addFigure(final Figure figure) {
		figures.add(figure);
	}

	public void removeFigure(final Figure figure) {
		figures.remove(figure);
	}

	public Iterator<Figure> getIterator() {
		return figures.iterator();
	}

	public void select(Point p1, Point p2) {
		ListIterator<Figure> iterator = figures.listIterator(figures.size());
		while (iterator.hasPrevious()) {
			Figure figure = iterator.previous();
			BoundBox boxFigure = figure.getNormalizedBoundBox();

			if (boxFigure.contains(p1)) {
				figure.setSelected(true);
				continue;
			}

			figure.setSelected(false);
		}
	}

	public void deleteFigures(Point p1, Point p2) {
	}
}
