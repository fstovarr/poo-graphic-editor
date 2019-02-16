package view;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import mediator.App;

public class SaveAsCommand implements Command {
	private static final String name = "Save as";
	private static final String iconPath = null;
	private Component parent;
	private boolean saved;

	public SaveAsCommand(Component parent) {
		this.parent = parent;
	}

	@Override
	public void execute() {
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File(App.SUG_FILE_NAME));
		int result = chooser.showSaveDialog(parent);
		if (result != JFileChooser.APPROVE_OPTION) {
			if (result == JFileChooser.CANCEL_OPTION)
				saved = false;
		} else {
			File file = chooser.getSelectedFile();
			if (!file.getName().endsWith(".eg"))
				file = new File(file.getAbsolutePath() + ".eg");
			if (file.exists() && JOptionPane.showConfirmDialog(parent,
					"The file " + file.getPath() + " already exists. Do you want to replace the existing file?",
					"Alert", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION) {
				execute();
				return;
			}
			App.getInstance().save(file);
			saved = true;
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
		return -1;
	}

	public boolean isSaved() {
		return saved;
	}
}
