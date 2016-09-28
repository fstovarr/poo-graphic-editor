package view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tool extends MouseAdapter {
	private Point ptPressed;
	private Point ptReleased;

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		ptPressed = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		ptReleased = e.getPoint();
	}

	protected Point getPtPressed() {
		return ptPressed;
	}

	protected Point getPtReleased() {
		return ptReleased;
	}
}
