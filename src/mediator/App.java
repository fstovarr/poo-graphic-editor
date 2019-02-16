package mediator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.undo.UndoableEdit;

import model.Drawing;
import model.Figure;
import view.Cardinal;
import view.DrawingListener;
import view.NewFileCommand;
import view.SaveCommand;
import view.Tool;
import view.View;

public class App {
	private static App app;
	private Drawing model;
	private View view;
	public static final String TITLE_APP = "Graphic Editor";
	public static final String SUG_FILE_NAME = "New graphic.eg";
	private static final Locale locale = Locale.ENGLISH;

	// Singleton Design Pattern
	private App() {
		model = new Drawing();
		view = new View(TITLE_APP);
		view.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		Locale.setDefault(locale);
		JComponent.setDefaultLocale(locale);
	}

	public Iterator<Figure> getFiguresIterator() {
		return model.getFiguresIterator();
	}

	public void save(final File file) {
		ObjectOutputStream oss = null;

		try {
			oss = new ObjectOutputStream(new FileOutputStream(file));
			model.save(oss, file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
			// TODO validar errores cuando se guarda
		} finally {
			if (oss != null)
				try {
					oss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public void load(File file) {
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			model.load(ois, file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ois != null)
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public void moveSelectedFigures(Point p) {
		model.moveSelectedFigures(p);
	}

	public String getFilePath() {
		return model.getFileName();
	}

	public void addDrawingListener(DrawingListener listener) {
		model.addListener(listener);
	}

	private void run() {
		view.setBounds(10, 10, 800, 640);
		view.setVisible(true);
		view.init();
		new NewFileCommand().execute();
	}

	public static void main(String[] args) {
		App.getInstance().run();
	}

	public static App getInstance() {
		if (app == null)
			app = new App();
		return app;
	}
	
	public void setFigureDimensions(Figure figure, Dimension dim, Point p) {
		model.setFigureDimensions(figure, dim, p);
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

	public boolean hasDocumentChanged() {
		return model.isChanged();
	}

	public boolean isSavedDocument() {
		return model.isSavedDocument();
	}

	public void newFile() {
		if (checkSavedDocument())
			model.newFile();
	}

	public boolean checkSavedDocument() {
		if (!model.isChanged())
			return true;
		int result = JOptionPane.showConfirmDialog(view, "Do you like save changes?", "Save",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (result != JOptionPane.YES_OPTION)
			return (result == JOptionPane.NO_OPTION || result != JOptionPane.CANCEL_OPTION);
		SaveCommand saveCommand = new SaveCommand(view);
		saveCommand.execute();
		return saveCommand.isSaved();
	}

	public void exit() {
		if (checkSavedDocument())
			System.exit(0);
	}

	public void showStrokeColorChooser() {
		Color color = view.showColorChooser("Choose a stroke color");
		if (color != null)
			model.changeStrokeColor(color);
	}

	public void showFillColorChooser() {
		Color color = view.showColorChooser("Choose a fill color");
		if (color != null)
			model.changeFillColor(color);
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
				if (thickness > 0)
					model.changeThickness(thickness);
			}
		});
	}

	public void resizeSelectedFigure(Point point, Figure figure, Cardinal cardinal) {
		model.resizeFigure(point, figure, cardinal);
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

	public Graphics2D getGraphics() {
		return view.getCanvasGraphics();
	}
}
