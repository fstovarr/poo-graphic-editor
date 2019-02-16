package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import mediator.App;

public class MenuBarHelper extends JMenuBar implements DrawingListener {
	private static final long serialVersionUID = 1;
	private ArrayList<JComponent> fileElements = new ArrayList<>();
	private ArrayList<JComponent> editElements = new ArrayList<>();
	private ArrayList<JComponent> helpElements = new ArrayList<>();

	public MenuBarHelper() {
		createFileMenu();
		createEditionMenu();
		createHelpMenu();
	}

	public void init() {
		App.getInstance().addDrawingListener(this);
	}

	private void createHelpMenu() {
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');

		helpElements.add(new MenuItem(new Command() {
			@Override
			public int getShortcutKey() {
				return -1;
			}

			@Override
			public String getName() {
				return "About";
			}

			@Override
			public String getIconPath() {
				return null;
			}

			@Override
			public void execute() {
			}
		}));

		fillMenu(helpElements, helpMenu);

		add(helpMenu);
	}

	private void createEditionMenu() {
		JMenu editionMenu = new JMenu("Edit");
		editionMenu.setMnemonic('E');

		editElements.add(new MenuItem(new UndoCommand()));
		editElements.add(new MenuItem(new RedoCommand()));
		editElements.add(new JPopupMenu.Separator());
		editElements.add(new MenuItem(new CutCommand()));
		editElements.add(new MenuItem(new CopyCommand()));
		editElements.add(new MenuItem(new PasteCommand()));
		editElements.add(new JPopupMenu.Separator());
		editElements.add(new MenuItem(new DeleteCommand()));
		editElements.add(new MenuItem(new SelectAllCommand()));

		fillMenu(editElements, editionMenu);

		add(editionMenu);
	}

	private void fillMenu(List<JComponent> elements, JMenu menu) {
		for (JComponent component : elements) {
			menu.add(component);

			if (component instanceof JMenuItem) {
				MenuItem item = ((MenuItem) component);

				Action a = new AbstractAction(item.getCommand().getName()) {
					private static final long serialVersionUID = 1;

					@Override
					public void actionPerformed(ActionEvent e) {
						item.apply();
					}
				};

				if (item.getCommand().getShortcutKey() != -1)
					a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(item.getCommand().getShortcutKey(),
							Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

				item.setAction(a);
			}
		}
	}

	private void createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');

		fileElements.add(new MenuItem(new NewFileCommand()));
		fileElements.add(new MenuItem(new OpenCommand(getParent())));
		fileElements.add(new MenuItem(new SaveCommand(getParent())));
		fileElements.add(new MenuItem(new SaveAsCommand(getParent())));
		fileElements.add(new JPopupMenu.Separator());
		fileElements.add(new MenuItem(new ExitCommand()));

		fillMenu(fileElements, fileMenu);

		add(fileMenu);
	}

	private class MenuItem extends JMenuItem {
		private static final long serialVersionUID = 1;
		private Command command;

		public MenuItem(Command command) {
			this.command = command;
			setText(command.getName());
		}

		public void apply() {
			if (!(command instanceof Tool)) {
				command.execute();
				setSelected(false);
			} else {
				Tool tool = (Tool) command;
				setSelected(true);
				App.getInstance().setActiveTool(tool);
			}
		}

		public Command getCommand() {
			return command;
		}

		@Override
		public boolean equals(Object obj) {
			return obj instanceof MenuItem
					&& ((MenuItem) obj == this || this.command == ((MenuItem) obj).getCommand());
		}
	}

	@Override
	public void update(DrawingEvent event) {
		switch (event) {
		default:
			break;
		case SAVED:
		case NEW:
			fileElements.get(2).setEnabled(false);
			break;
		case MODIFIED:
			if (!fileElements.get(2).isEnabled())
				fileElements.get(2).setEnabled(true);
			break;
		}
	}
}
