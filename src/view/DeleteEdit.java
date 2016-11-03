package view;

import java.util.List;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import mediator.App;
import model.Figure;

public class DeleteEdit extends AbstractUndoableEdit {
	private static final long serialVersionUID = 701998355968155274L;
	private List<Figure> figures;

	public DeleteEdit(List<Figure> figures) {
		this.figures = figures;
	}

	@Override
	public void redo() throws CannotRedoException {
		super.redo();
		App.getInstance().deleteFigures(figures);
	}

	@Override
	public void undo() throws CannotUndoException {
		super.undo();
		App.getInstance().addFigures(figures);
	}
}
