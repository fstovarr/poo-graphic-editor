package mediator;

import java.awt.Point;
import java.util.Iterator;

import model.Drawing;
import model.Figure;
import view.DrawingListener;
import view.Tool;
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

	public void addDrawingListener(DrawingListener listener) {
		model.addListener(listener);
	}

	private void run() {
		view.setBounds(10, 10, 800, 640);
		view.setVisible(true);
		view.init();
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
	}

	public void addFigure(final Figure figure) {
		model.addFigure(figure);
	}

	public void deleteSelectedFigures() {
		model.deleteSelected();
	}

	public void selectFigure(Figure figure) {
		model.select(figure);
	}

	public Tool getActiveTool() {
		return view.getActiveTool();
	}

	public void setActiveTool(Tool tool) {
		view.setActiveTool(tool);
	}

	public void selectFigure(Point p1, Point p2) {
		model.select(p1, p2);
	}

	public void deselectAll() {
		model.deselectAll();
	}

	public void newFile() {
		model.clear();
	}

	public void exit() {
		System.exit(0);
	}
}
