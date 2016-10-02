package view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;

public abstract class InteractiveTool extends MouseAdapter implements ItemToolbar {
	private ImageIcon icon;
	private Point ptPressed;
	private Point ptReleased;
	private String name;

	public InteractiveTool(String iconPath, String name) {
		try {
			URL resource = this.getClass().getClassLoader().getResource(iconPath);
			if (resource != null) {
				// RESIZE ICON
				icon = ItemToolbar.resizeIcon(new ImageIcon(resource));
			} else {
				throw new Exception("Icon don't found :(");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.name = name;
		}
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
		processMouse();
	}

	protected abstract void processMouse();

	@Override
	public ImageIcon getIcon() {
		return icon;
	}

	@Override
	public String getName() {
		return name;
	}

	protected Point getPtPressed() {
		return ptPressed;
	}

	protected Point getPtReleased() {
		return ptReleased;
	}
}
