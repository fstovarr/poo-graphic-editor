package mediator;

import java.util.Iterator;

import model.Drawing;
import model.Figure;
import view.View;

public class App {
	private static App app;
	private Drawing model;
	private View view;

	// Singleton Design Pattern
	private App() {
		super();
		model = new Drawing();
		view = new View("Editor Gráfico");
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

	public void addFigure(Figure figure) {
		model.addFigure(figure);
		view.repaintCanvas();
	}
}
