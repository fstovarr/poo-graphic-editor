package mediator;

import java.awt.Point;
import java.util.Iterator;

import model.Drawing;
import model.Figure;
import view.InteractiveTool;
import view.View;

public class App {
	private static App app;
	private Drawing model;
	private View view;
	private static final String TITLE_APP = "Graphic Editor";

	// Singleton Design Pattern
	private App() {
		super();
		model = new Drawing();
		view = new View(TITLE_APP);
	}

	public Iterator<Figure> getIterator() {
		return model.getIterator();
	}

	private void run() {
		view.setBounds(10, 10, 800, 640);
		view.setVisible(true);
	}

	public static void main(String[] args) {
		App app = App.getInstance();
		app.run();
	}

	public static App getInstance() {
		if (app == null) {
			app = new App();
		}
		return app;
	}

	public void selectAll() {
		model.selectAll();
		repaintCanvas();
	}

	public void addFigure(final Figure figure) {
		model.addFigure(figure);
		repaintCanvas();
	}

	public void deleteSelectedFigures() {
		model.deleteSelected();
		repaintCanvas();
	}

	public void repaintCanvas() {
		view.repaintCanvas();
	}

	public void selectFigure(Figure figure) {
		model.deselectAll();
		model.select(figure);
	}
	
	public InteractiveTool getActiveTool() {
		return view.getActiveTool();
	}

	public void setActiveTool(InteractiveTool tool) {
		view.setActiveTool(tool);
	}

	public void selectFigure(Point p1, Point p2) {
		model.deselectAll();
		model.select(p1, p2);
		repaintCanvas();
	}

	public void newFile() {
		model.clear();
		repaintCanvas();
	}

	public void exit() {
		System.exit(0);
	}
}
