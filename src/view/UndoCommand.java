package view;

public class UndoCommand implements Command {
	private static final String name = "Undo";
	private static final String iconPath = null;

	@Override
	public void execute() {
		// TODO Auto-generated method stub

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
