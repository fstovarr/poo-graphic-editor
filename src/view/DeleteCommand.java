package view;

import mediator.App;

public class DeleteCommand implements Command {
	private static final String name = "Delete";
	private static final String iconPath = "resources/bwicons/eraser1.png";

	public DeleteCommand() {
	}

	@Override
	public void execute() {
		App.getInstance().deleteSelectedFigures();
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
