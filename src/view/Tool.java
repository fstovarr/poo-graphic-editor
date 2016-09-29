package view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Tool extends MouseAdapter {
	private ImageIcon icon;
	private Point ptPressed;
	private Point ptReleased;

	public Tool(String iconPath) {
		if (iconPath != "" && iconPath != null) {
			System.out.println("Entra " + iconPath);

			try {
				File file = new File(getClass().getResource("/resources/font.png").toURI());
				icon = new ImageIcon(getClass().getResource(iconPath));
				System.out.println("Imagen recuperada");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		System.out.println("Sale");
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
	}

	protected Point getPtPressed() {
		return ptPressed;
	}

	protected Point getPtReleased() {
		return ptReleased;
	}

	public ImageIcon getIcon() {
		return icon;
	}
}
