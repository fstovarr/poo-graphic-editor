package view;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Tool extends MouseAdapter implements Command {
	private String name, iconPath;
	private Point ptPressed;
	private Point ptReleased;
	private Cursor cursor;

	public Tool(String iconPath, String name) {
		this.name = name;
		this.iconPath = iconPath;
		cursor = Cursor.getDefaultCursor();
	}

	@Override
	public void execute() {
		processMouse();
	}

	public Cursor getCursor() {
		return cursor;
	}

	public String getIconPath() {
		return iconPath;
	}

	public String getName() {
		return name;
	}

	protected Point getPtPressed() {
		return ptPressed;
	}

	protected Point getPtReleased() {
		return ptReleased;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		ptPressed = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		ptReleased = e.getPoint();
		execute();
	}

	protected abstract void processMouse();

	protected void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}
}
