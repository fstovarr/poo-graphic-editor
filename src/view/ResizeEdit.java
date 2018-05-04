package view;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import mediator.App;
import model.Figure;

public class ResizeEdit extends AbstractUndoableEdit {
	private Figure figure;
	private Point location, originalP;
	private Dimension dim, originalD;

	public ResizeEdit(Figure figure, Dimension originalD, Point originalP) {
		this.figure = figure;
		location = new Point(figure.getBoundBox().x, figure.getBoundBox().y);
		dim = new Dimension(figure.getBoundBox().width, figure.getBoundBox().height);
		this.originalD = originalD;
		this.originalP = originalP;
	}

	@Override
	public void redo() throws CannotRedoException {
		super.redo();
		// App.getInstance().addFigure(figure);
		App.getInstance().setFigureDimensions(figure, dim, location);
	}

	@Override
	public void undo() throws CannotUndoException {
		super.undo();
		// App.getInstance().deleteFigure(figure);
		App.getInstance().setFigureDimensions(figure, originalD, originalP);

		// App.getInstance().addFigure(figure);
	}
}
