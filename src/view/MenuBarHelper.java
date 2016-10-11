package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import mediator.App;

public class MenuBarHelper extends JMenuBar implements DrawingListener {
	private static final long serialVersionUID = 1L;
	private JMenuItem cut;
	private JMenuItem copy;
	private JMenuItem paste;
	private JMenuItem delete;
	private JMenuItem redo;
	private JMenuItem undo;
	private JMenuItem save;
	private Command[] commands = new Command[9];

	public MenuBarHelper() {
		commands[0] = new CutCommand();
		commands[1] = new CopyCommand();
		commands[2] = new PasteCommand();
		commands[3] = new DeleteCommand();
		commands[4] = new RedoCommand();
		commands[5] = new UndoCommand();
		commands[6] = new SaveCommand();
		commands[7] = new SelectAllCommand();
		commands[8] = new ExitCommand();

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

		JMenuItem about = new JMenuItem("About...");
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		helpMenu.add(about);
		add(helpMenu);
	}

	private void createEditionMenu() {
		JMenu editionMenu = new JMenu("Edit");
		editionMenu.setMnemonic('E');

		redo = new JMenuItem(commands[4].getName());
		undo = new JMenuItem(commands[5].getName());
		cut = new JMenuItem(commands[0].getName());
		copy = new JMenuItem(commands[1].getName());
		paste = new JMenuItem(commands[2].getName());
		delete = new JMenuItem(commands[3].getName());

		redo.setEnabled(false);
		undo.setEnabled(false);
		cut.setEnabled(false);
		copy.setEnabled(false);
		paste.setEnabled(false);
		delete.setEnabled(false);

		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commands[5].execute();
			}
		});

		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commands[4].execute();
			}
		});

		cut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commands[0].execute();
			}
		});

		copy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commands[1].execute();
			}
		});

		paste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commands[2].execute();
			}
		});

		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commands[3].execute();
			}
		});

		JMenuItem selectAll = new JMenuItem(commands[7].getName());
		selectAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commands[7].execute();
			}
		});

		editionMenu.add(undo);
		editionMenu.add(redo);
		editionMenu.addSeparator();
		editionMenu.add(cut);
		editionMenu.add(copy);
		editionMenu.add(paste);
		editionMenu.addSeparator();
		editionMenu.add(delete);
		editionMenu.add(selectAll);

		add(editionMenu);
	}

	private void createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');

		JMenuItem file = new JMenuItem("New");
		file.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JMenuItem open = new JMenuItem("Open file...");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		save = new JMenuItem(commands[6].getName());
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commands[6].execute();
			}
		});

		JMenuItem saveAs = new JMenuItem("Save as...");
		saveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JMenuItem exit = new JMenuItem(commands[8].getName());
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commands[8].execute();
			}
		});

		fileMenu.add(file);
		fileMenu.add(open);
		fileMenu.addSeparator();
		fileMenu.add(save);
		fileMenu.add(saveAs);
		fileMenu.addSeparator();
		fileMenu.add(exit);

		add(fileMenu);
	}

	@Override
	public void update(DrawingEvent event) {
		switch (event) {
		case SELECTED:
			cut.setEnabled(true);
			copy.setEnabled(true);
			paste.setEnabled(true);
			delete.setEnabled(true);
			break;

		case DESELECTED:
			cut.setEnabled(false);
			copy.setEnabled(false);
			paste.setEnabled(false);
			delete.setEnabled(false);
			break;

		default:
			break;
		}
	}
}
