package view;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import mediator.App;

public class SaveCommand implements Command {
	private static final String name = "Save";
	private static final String iconPath = null;
	private Component parent;

	public SaveCommand(Component parent) {
		this.parent = parent;
	}

	@Override
	public void execute() {
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
		chooser.setSelectedFile(new File(App.SUG_FILE_NAME));
		int result = chooser.showSaveDialog(parent);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			
			if (!file.getName().endsWith(".eg")) {
				file = new File(file.getAbsolutePath() + ".eg");
			}

			if (file.exists()) {
				int resultDialog = JOptionPane.showConfirmDialog(parent,
						"The file " + file.getPath() + " already exists. Do you want to replace the existing file?",
						"Alert", JOptionPane.OK_CANCEL_OPTION);
				if (resultDialog != JOptionPane.OK_OPTION) {
					execute();
					return;
				}
			}

			App.getInstance().save(file);
		}
	}

	@Override
	public String getIconPath() {
		return iconPath;
	}

	@Override
	public int getShortcutKey() {
		return KeyEvent.VK_S;
	}

	@Override
	public String getName() {
		return name;
	}
}
