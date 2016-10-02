package view;

import java.net.URL;

import javax.swing.ImageIcon;

public abstract class ActionTool implements ItemToolbar {
	private ImageIcon icon;
	private String name;

	public ActionTool(String iconPath, String name) {
		try {
			URL resource = this.getClass().getClassLoader().getResource(iconPath);
			if (resource != null) {
				icon = new ImageIcon(resource);
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
	public ImageIcon getIcon() {
		return icon;
	}

	@Override
	public String getName() {
		return name;
	}
}
