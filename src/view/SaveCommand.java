package view;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;

import mediator.App;

public class SaveCommand implements Command {
	private static final String name = "Save";
	private static final String iconPath = null;
	private SaveAsCommand command;

	public SaveCommand(Component parent) {
		command = new SaveAsCommand(parent);
	}

	@Override
	public void execute() {
		boolean saved = App.getInstance().isSavedDocument();
		boolean changed = App.getInstance().hasDocumentChanged();

		if (!saved) {
			command.execute();
		} else if (changed) {
			App.getInstance().save(new File(App.getInstance().getFilePath()));
		}
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
		return KeyEvent.VK_S;
	}

	public boolean isSaved() {
		return command.isSaved();
	}
}
