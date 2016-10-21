package view;

import java.awt.Point;
import java.awt.event.MouseEvent;

import model.Figure;
import model.Line;
import view.DrawingListener.DrawingEvent;

public class LineCreationTool extends CreationTool {
	public LineCreationTool() {
		super("resources/bwicons/line1.png", "Line Creation");
	}

	@Override
	protected void createInitialFigure(Point ptPressed) {
		setFigure(new Line(new BoundBox(ptPressed.x, ptPressed.y, 0, 0), null, 1));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Figure figure = getFigure();
		figure.getBoundBox().updateSize(getPtPressed(), e.getPoint());

		getListener().update(DrawingEvent.MODIFIED);
	}
}
