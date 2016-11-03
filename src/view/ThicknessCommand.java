package view;

import mediator.App;

public class ThicknessCommand implements Command {

	@Override
	public void execute() {
		App.getInstance().showThicknessChooser();
	}

	@Override
	public String getIconPath() {
		return "resources/bwicons/stroke2.png";
	}

	@Override
	public String getName() {
		return "Set thickness";
	}
	
	@Override
	public int getShortcutKey() {
		return -1;
	}
}
