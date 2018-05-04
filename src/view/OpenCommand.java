package view;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import mediator.App;

public class OpenCommand implements Command {
	private static final String name = "Open";
	private static final String iconPath = null;
	private Component parent;

	public OpenCommand(final Component parent) {
		this.parent = parent;
	}

	@Override
	public void execute() {
		if (App.getInstance().checkSavedDocument()) {
			JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphic files (*.eg)", "eg");
			chooser.setFileFilter(filter);
			int result = chooser.showOpenDialog(parent);

			if (result == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();

				if (!file.getName().endsWith(".eg")) {
					JOptionPane.showMessageDialog(parent, "The file " + file.getPath() + " is not supported.", "Alert", JOptionPane.ERROR_MESSAGE);
					execute();
					return;
				}
				
				//TODO arreglar load
				App.getInstance().load(file);
			}
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
