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
		if (!App.getInstance().checkSavedDocument())
			return;
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
		chooser.setFileFilter(new FileNameExtensionFilter("Graphic files (*.eg)", "eg"));
		
		if (chooser.showOpenDialog(parent) != JFileChooser.APPROVE_OPTION)
			return;
		
		File file = chooser.getSelectedFile();
		if (file.getName().endsWith(".eg"))
			App.getInstance().load(file);
		else {
			JOptionPane.showMessageDialog(parent, "The file " + file.getPath() + " is not supported.", "Alert",
					JOptionPane.ERROR_MESSAGE);
			execute();
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
