package view;

import java.awt.Point;
import java.awt.event.MouseEvent;

import mediator.App;

public class ResizeTool extends Tool {

	public ResizeTool() {
		super(null, "Resize");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point point = e.getPoint();
		int x = e.getPoint().x - getPtPressed().x;
		int y = e.getPoint().y - getPtPressed().y;
		App.getInstance().resizeSelectedFigure(new Point(x, y));
	}

	@Override
	protected void processMouse() {
	}

	@Override
	public int getShortcutKey() {
		return -1;
	}
}
