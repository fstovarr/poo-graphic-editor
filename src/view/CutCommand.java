package view;

public class CutCommand implements Command {
	private static final String name = "Cut";
	private static final String iconPath = "resources/bwicons/eraser1.png";

	@Override
	public void execute() {
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
