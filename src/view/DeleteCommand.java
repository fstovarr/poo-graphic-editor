package view;

import java.util.List;

import mediator.App;
import model.Figure;

public class DeleteCommand implements Command {
	private static final String name = "Delete";
	private static final String iconPath = "resources/bwicons/eraser1.png";

	public DeleteCommand() {
	}

	@Override
	public void execute() {
		List<Figure> figures = App.getInstance().getSelectedFigures();
		App.getInstance().addEdit(new DeleteEdit(figures));
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

	@Override
	public int getShortcutKey() {
		return -1;
	}
}
