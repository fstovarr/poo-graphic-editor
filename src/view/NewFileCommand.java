package view;

import mediator.App;

public class NewFileCommand implements Command {
	private static final String name = "New";
	private static final String iconPath = null;

	@Override
	public void execute() {
		App.getInstance().newFile();
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
