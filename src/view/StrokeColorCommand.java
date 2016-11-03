package view;

import mediator.App;

public class StrokeColorCommand implements Command {

	@Override
	public void execute() {
		App.getInstance().showStrokeColorChooser();
	}

	@Override
	public String getIconPath() {
		return "resources/bwicons/stroke1.png";
	}

	@Override
	public String getName() {
		return "Stroke color";
	}

	@Override
	public int getShortcutKey() {
		return -1;
	}
}
