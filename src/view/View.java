package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;

	public View(final String title) {
		super(title);
		Canvas canvas = new Canvas();
		add(BorderLayout.CENTER, canvas);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
