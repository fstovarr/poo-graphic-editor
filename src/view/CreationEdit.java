package view;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import mediator.App;
import model.Figure;

public class CreationEdit extends AbstractUndoableEdit {
	private static final long serialVersionUID = 701998355968155274L;
	private Figure figure;

	public CreationEdit(Figure figure) {
		this.figure = figure;
	}

	@Override
	public void redo() throws CannotRedoException {
		super.redo();
		App.getInstance().addFigure(figure);
	}

	@Override
	public void undo() throws CannotUndoException {
		super.undo();
		App.getInstance().deleteFigure(figure);
	}
}
