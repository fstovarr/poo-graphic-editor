package view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Tool extends MouseAdapter implements Command {
	private String name, iconPath;
	private Point ptPressed;
	private Point ptReleased;

	public Tool(String iconPath, String name) {
		this.name = name;
		this.iconPath = iconPath;
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

	@Override
	public void execute() {
		processMouse();
	}

	protected abstract void processMouse();
}
