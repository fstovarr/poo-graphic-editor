package view;

import java.awt.event.KeyEvent;

import mediator.App;

public class SelectAllCommand implements Command {
	private static final String name = "Select all";
	private static final String iconPath = null;

	@Override
	public void execute() {
		App.getInstance().selectAll();
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
		return KeyEvent.VK_A;
	}
}
