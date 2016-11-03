package model;

import java.awt.Color;
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
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.undo.UndoableEditSupport;

import view.BoundBox;
import view.DrawingListener;
import view.DrawingListener.DrawingEvent;

public class Drawing {
	private List<Figure> figures;
	private List<DrawingListener> listeners;
	private List<Figure> selectedFigures;
	private String fileName = null;
	private UndoManager undoManager;
	private UndoableEditSupport editSupport;

	public Drawing() {
		figures = new LinkedList<>();
		selectedFigures = new LinkedList<>();
		listeners = new LinkedList<>();

		undoManager = new UndoManager();
		editSupport = new UndoableEditSupport();
		editSupport.addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				UndoableEdit edit = e.getEdit();
				undoManager.addEdit(edit);
				notifyListeners(DrawingEvent.UNDO_REDO);
			}
		});
	}

	public void addFigure(final Figure figure) {
		figures.add(figure);
		select(figure);
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
		notifyListeners(DrawingEvent.NEW);
	}

	public void deleteFigure(Figure figure) {
		figures.remove(figure);
		selectedFigures.remove(figures);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public boolean hasSelectedFigures() {
		return !selectedFigures.isEmpty();
	}

	public List<Figure> getSelectedFigures() {
		return new LinkedList<>(selectedFigures);
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

	public Iterator<Figure> getFiguresIterator() {
		return figures.iterator();
	}

	public String getFileName() {
		return fileName;
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

	public void moveSelectedFigures(Point base, Point p) {
		for (Figure figure : selectedFigures) {
			figure.move(base, p);
		}
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void resizeSelectedFigure(Point point) {
		selectedFigures.get(0).resize(point);
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void undo() {
		if (undoManager.canUndo()) {
			undoManager.undo();
		}
	}

	public void redo() {
		if (undoManager.canRedo()) {
			undoManager.redo();
		}
	}

	public void addEdit(UndoableEdit edit) {
		editSupport.postEdit(edit);
	}

	public void addFigures(List<Figure> figures2) {
		for (Figure f : figures2) {
			figures.add(f);
		}
		notifyListeners(DrawingEvent.MODIFIED);
	}

	public void save(final ObjectOutputStream oos, String name) {
		try {
			oos.writeObject(figures);
			fileName = name;
			notifyListeners(DrawingEvent.SAVED);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void load(final ObjectInputStream ois) {
		try {
			figures = (List<Figure>) ois.readObject();
			notifyListeners(DrawingEvent.LOADED);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteFigures(List<Figure> figures2) {
		for (Figure figure : figures2) {
			figures.remove(figure);
		}
		notifyListeners(DrawingEvent.MODIFIED);
	}
}