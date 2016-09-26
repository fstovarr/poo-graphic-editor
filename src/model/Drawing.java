package model;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import view.BoundBox;

public class Drawing {
	private List<Figure> figures;

	public Drawing() {
		figures = new LinkedList<Figure>();
		testFigures();
	}

	public void testFigures() {
		Line line = new Line(new BoundBox(300, 310, 100, 100), Color.BLUE);
		line.setSelected(true);
		addFigure(line);

		Ellipse elli = new Ellipse(new BoundBox(30, 30, 100, 100), Color.BLACK);
		elli.setSelected(true);
		addFigure(elli);

		Rectangle rect = new Rectangle(new BoundBox(400, 30, 100, 100), Color.RED);
		rect.setSelected(true);
		addFigure(rect);

		Text text = new Text(new BoundBox(200, 200, 1000, 30), "Hola mundo", Color.GREEN);
		text.setSelected(true);
		addFigure(text);

	}

	public void addFigure(final Figure figure) {
		figures.add(figure);
	}

	public Iterator<Figure> getIterator() {
		return figures.iterator();
	}

}
