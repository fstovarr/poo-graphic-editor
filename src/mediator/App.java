package mediator;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.undo.UndoableEdit;

import model.Drawing;
import model.Figure;
import view.DrawingListener;
import view.Tool;
import view.View;

public class App {
	private static App app;
	private Drawing model;
	private View view;
	public static final String TITLE_APP = "Graphic Editor";
	public static final String SUG_FILE_NAME = "New graphic.eg";

	// Singleton Design Pattern
	private App() {
		super();
		model = new Drawing();
		view = new View(TITLE_APP);
		Locale.setDefault(Locale.US);
	}

	public Iterator<Figure> getFiguresIterator() {
		return model.getFiguresIterator();
	}

	public void save(final File file) {
		ObjectOutputStream oss = null;

		try {
			oss = new ObjectOutputStream(new FileOutputStream(file));
			model.save(oss, file.getName());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oss != null) {
				try {
					oss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void load(File file) {
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			model.load(ois);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void moveSelectedFigures(Point base, Point p) {
		model.moveSelectedFigures(base, p);
	}

	public String getFileName() {
		return model.getFileName();
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

	public void addFigures(final List<Figure> figures) {
		model.addFigures(figures);
	}

	public void deleteSelectedFigures() {
		model.deleteSelected();
	}

	public List<Figure> getSelectedFigures() {
		return model.getSelectedFigures();
	}

	public void selectFigure(Figure figure) {
		model.select(figure);
	}

	public Tool getActiveTool() {
		return view.getActiveTool();
	}

	public void undoAction() {
		model.undo();
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

	public void showStrokeColorChooser() {
		model.changeStrokeColor(view.showColorChooser("Choose a stroke color"));
	}

	public void showFillColorChooser() {
		model.changeFillColor(view.showColorChooser("Choose a fill color"));
	}

	public void deleteFigure(Figure figure) {
		model.deleteFigure(figure);
	}

	public void setCursor(Cursor cursor) {
		view.setCursor(cursor);
	}

	public boolean hasSelectedFigures() {
		return model.hasSelectedFigures();
	}

	public void showThicknessChooser() {
		view.showThicknessChooser("Set the thickness", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int thickness = view.getThicknessValue();
				if (thickness > 0) {
					model.changeThickness(thickness);
				}
			}
		});
	}

	public void resizeSelectedFigure(Point point) {
		model.resizeSelectedFigure(point);
	}

	public void addEdit(UndoableEdit edit) {
		model.addEdit(edit);
	}

	public void redoAction() {
		model.redo();
	}

	public void deleteFigures(List<Figure> figures) {
		model.deleteFigures(figures);
	}
}
