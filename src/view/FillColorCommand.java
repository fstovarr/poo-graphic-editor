package view;

import mediator.App;

public class FillColorCommand implements Command {

	@Override
	public void execute() {
		App.getInstance().showFillColorChooser();
	}

	@Override
	public String getIconPath() {
		return "resources/bwicons/fillcolor1.png";
	}

	@Override
	public String getName() {
		return "Fill color";
	}

	@Override
	public int getShortcutKey() {
		return -1;
	}
}
