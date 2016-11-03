package view;

import java.awt.event.KeyEvent;

public class PasteCommand implements Command {
	private static final String name = "Paste";
	private static final String iconPath = null;

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getIconPath() {
		return iconPath;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getShortcutKey() {
		return KeyEvent.VK_V;
	}
}
