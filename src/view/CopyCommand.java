package view;

import java.awt.event.KeyEvent;

public class CopyCommand implements Command {
	private static final String name = "Copy";
	private static final String iconPath = "resources/bwicons/eraser1.png";

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
		return KeyEvent.VK_C;
	}
}
