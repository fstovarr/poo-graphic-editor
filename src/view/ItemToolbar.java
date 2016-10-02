package view;

import java.awt.Image;

import javax.swing.ImageIcon;

public interface ItemToolbar {
	static final int ICON_SIZE = 32;

	ImageIcon getIcon();

	String getName();

	static ImageIcon resizeIcon(ImageIcon icon) {
		Image newImage = icon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
		return new ImageIcon(newImage);
	}
}
