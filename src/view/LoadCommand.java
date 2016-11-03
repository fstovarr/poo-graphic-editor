package view;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import mediator.App;

public class LoadCommand implements Command {
	private static final String name = "Load";
	private static final String iconPath = null;
	private Component parent;

	public LoadCommand(final Component parent) {
		this.parent = parent;
	}

	@Override
	public void execute() {
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
		int result = chooser.showOpenDialog(parent);

		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();

			if (!file.getName().endsWith(".eg")) {
				JOptionPane.showConfirmDialog(parent, "The file " + file.getPath() + " is not supported.", "Alert",
						JOptionPane.OK_OPTION);
				execute();
				return;
			}

			App.getInstance().load(file);
		}
	}

	@Override
	public String getIconPath() {
		return iconPath;
	}

	@Override
	public int getShortcutKey() {
		return KeyEvent.VK_O;
	}

	@Override
	public String getName() {
		return name;
	}
}
