package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

import mediator.App;
import model.Figure;

public class ResizeTool extends Tool {
	private Figure figure;
	private Cardinal cardinal;
	private Dimension originalDim;
	private Point originalLoc;

	public ResizeTool(Figure figure, Cardinal cardinal, Point pressed) {
		super(null, "Resize");
		this.figure = figure;
		this.cardinal = cardinal;
		this.originalDim = new Dimension(figure.getBoundBox().getSize());
		this.originalLoc = new Point(figure.getBoundBox().getLocation());
		setPtPressed(pressed);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		App.getInstance().addEdit(new ResizeEdit(figure, originalDim, originalLoc));
		App.getInstance().setActiveTool(new SelectionTool());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = new Point(e.getPoint().x - getPtPressed().x, e.getPoint().y - getPtPressed().y);
		setPtPressed(e.getPoint());
		App.getInstance().resizeSelectedFigure(p, figure, cardinal);
	}

	@Override
	protected void processMouse() {
	}

	@Override
	public int getShortcutKey() {
		return -1;
	}
}
