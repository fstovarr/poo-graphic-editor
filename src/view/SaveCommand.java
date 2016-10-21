package view;

import mediator.App;

public class SaveCommand implements Command {
	private static final String name = "Save";
	private static final String iconPath = null;

	@Override
	public void execute() {
		App.getInstance().save();
	}

	@Override
	public String getIconPath() {
		// TODO Auto-generated method stub
		return iconPath;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
