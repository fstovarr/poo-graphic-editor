package view;

import java.awt.Point;
import java.util.List;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import model.Figure;

public class MoveEdit extends AbstractUndoableEdit {
	private Point inicialPoint, finalPoint;
	private List<Figure> figures;

	public MoveEdit(final List<Figure> figures, final Point inicialPoint, final Point finalPoint) {
		this.inicialPoint = inicialPoint;
		this.figures = figures;
		this.finalPoint = finalPoint;
	}

	@Override
	public void redo() throws CannotRedoException {
		super.redo();
	}

	@Override
	public void undo() throws CannotUndoException {
		super.undo();
	}
}
