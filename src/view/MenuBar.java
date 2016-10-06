package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import mediator.App;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public MenuBar() {
		super();
		createFileMenu();
		createEditionMenu();
		createHelpMenu();
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

		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JMenuItem redo = new JMenuItem("Redo");
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JMenuItem cut = new JMenuItem("Cut");
		cut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JMenuItem copy = new JMenuItem("Copy");
		copy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JMenuItem paste = new JMenuItem("Paste");
		paste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JMenuItem delete = new JMenuItem("Delete");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().deleteSelectedFigures();
			}
		});

		JMenuItem selectAll = new JMenuItem("Select all");
		selectAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().selectAll();
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
				App.getInstance().newFile();
			}
		});

		JMenuItem open = new JMenuItem("Open file...");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JMenuItem saveAs = new JMenuItem("Save as...");
		saveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().exit();
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
}
