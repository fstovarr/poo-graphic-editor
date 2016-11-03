package view;

import java.awt.event.KeyEvent;

public interface Command {
	void execute();

	String getIconPath();

	String getName();

	int getShortcutKey();
}
