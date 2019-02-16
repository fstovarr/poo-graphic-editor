package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.undo.UndoableEditSupport;

import mediator.App;
import view.BoundBox;
import view.Cardinal;
import view.DrawingListener;

public class Drawing implements DrawingListener {
	private List<Figure> figures;
	private List<DrawingListener> listeners;
	private List<Figure> selectedFigures;
	private String pathFile;
	private UndoManager undoManager;
	private UndoableEditSupport editSupport;
	private boolean savedDocument;
	private boolean modified;

	public Drawing() {
		figures = new LinkedList<>();
		selectedFigures = new LinkedList<>();
		listeners = new LinkedList<>();

		addListener(this);

		undoManager = new UndoManager();
		editSupport = new UndoableEditSupport();
		editSupport.addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				undoManager.addEdit(e.getEdit());
				notifyListeners(DrawingEvent.UNDO_REDO);
			}
		});
	}

	public void addEdit(UndoableEdit edit) {
		editSupport.postEdit(edit);
	}

	public void addFigure(final Figure figure) {
		figures.add(figure);
		select(figure);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void addFigures(List<Figure> figures2) {
		for (Figure f : figures2)
			figures.add(f);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void addListener(DrawingListener listener) {
		listeners.add(listener);
	}

	public void changeFillColor(Color color) {
		for (Figure figure : selectedFigures)
			if (figure instanceof ClosedFigure)
				((ClosedFigure) figure).setFillColor(color);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void changeStrokeColor(Color color) {
		for (Figure figure : selectedFigures)
			if (figure instanceof ClosedFigure)
				((ClosedFigure) figure).setColor(color);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void changeThickness(int thickness) {
		for (Figure figure : selectedFigures)
			if (figure instanceof GeometricFigure)
				((GeometricFigure) figure).setThickness(thickness);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void newFile() {
		figures.clear();
		selectedFigures.clear();
		notifyListeners(DrawingEvent.NEW);
	}

	public void deleteFigure(Figure figure) {
		figures.remove(figure);
		selectedFigures.remove(figures);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void deleteFigures(List<Figure> figures2) {
		for (Figure figure : figures2)
			figures.remove(figure);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void deleteSelected() {
		for (Iterator<Figure> iSelected = selectedFigures.iterator(); 
				iSelected.hasNext(); iSelected.remove())
			removeFigure(iSelected.next());

		notifyListeners(DrawingEvent.DESELECTED);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void deselectAll() {
		for (Figure figure : figures)
			figure.setSelected(false);
		selectedFigures.clear();
		notifyListeners(DrawingEvent.DESELECTED);
	}

	public Iterator<Figure> getFiguresIterator() {
		return figures.iterator();
	}

	public String getFileName() {
		if (pathFile == null)
			pathFile = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/" + App.SUG_FILE_NAME;
		return pathFile;
	}

	public List<Figure> getSelectedFigures() {
		return new LinkedList<>(selectedFigures);
	}

	public boolean hasSelectedFigures() {
		return !selectedFigures.isEmpty();
	}

	public boolean isSavedDocument() {
		return savedDocument;
	}

	@SuppressWarnings("unchecked")
	public void load(final ObjectInputStream ois, String path) {
		try {
			figures = (List<Figure>) ois.readObject();
			pathFile = path;
			notifyListeners(DrawingEvent.LOADED);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void moveSelectedFigures(Point p) {
		for (Figure figure : selectedFigures)
			figure.move(p);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	private void notifyListeners(DrawingEvent event) {
		for (DrawingListener listener : listeners)
			listener.update(event);
	}

	public void redo() {
		if (undoManager.canRedo())
			undoManager.redo();
	}

	private void removeFigure(final Figure figure) {
		figures.remove(figure);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void resizeFigure(Point point, Figure figure, Cardinal cardinal) {
		figure.resize(point, cardinal);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void save(final ObjectOutputStream oos, String path) {
		try {
			oos.writeObject(figures);
			pathFile = path;
			notifyListeners(DrawingEvent.SAVED);
		} catch (IOException e) {
			e.printStackTrace();
			notifyListeners(DrawingEvent.MODIFIED);
		}
	}

	private void select(BoundBox box) {
		for (ListIterator<Figure> iterator = figures.listIterator(figures.size()); iterator.hasPrevious();) {
			Figure figure = iterator.previous();
			if (box.contains(figure.getNormalizedBoundBox()))
				select(figure);
		}
	}

	public void select(Figure figure) {
		figure.setSelected(true);
		selectedFigures.add(figure);
		if (!selectedFigures.isEmpty())
			notifyListeners(DrawingEvent.SELECTED);
	}

	public void select(Point p1, Point p2) {
		ListIterator<Figure> iterator = figures.listIterator(figures.size());
		if (!BoundBox.isEmptyBoundBox(p1, p2)) {
			BoundBox box = new BoundBox(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
			box.normalize();
			select(box);
		} else
			while (iterator.hasPrevious()) {
				Figure figure = iterator.previous();
				if (figure.getNormalizedBoundBox().contains(p1)) {
					select(figure);
					break;
				}
			}
	}

	public void selectAll() {
		for (Figure figure : figures) {
			figure.setSelected(true);
			selectedFigures.add(figure);
		}

		if (!selectedFigures.isEmpty())
			notifyListeners(DrawingEvent.SELECTED);
	}

	public void undo() {
		if (undoManager.canUndo())
			undoManager.undo();
	}

	@Override
	public void update(DrawingEvent event) {
		switch (event) {
		case SAVED:
		case LOADED:
			savedDocument = true;
			modified = false;
			break;
		case MODIFIED:
			modified = true;
			break;
		case UNDO_REDO:
			modified = undoManager.canUndo();
			break;
		case NEW:
			savedDocument = false;
			modified = false;
			break;
		default:
			break;
		}
	}

	public boolean isChanged() {
		return modified;
	}

	public void setFigureDimensions(Figure figure, Dimension dim, Point p) {
		figure.setDimensions(dim, p);
		notifyListeners(DrawingEvent.MODIFIED);
	}
}