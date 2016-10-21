package view;

import mediator.App;

public class ExitCommand implements Command {
	private static final String name = "Exit";
	private static final String iconPath = null;

	@Override
	public void execute() {
		App.getInstance().exit();
	}

	@Override
	public String getIconPath() {
		return iconPath;
	}

	@Override
	public String getName() {
		return name;
	}
}
