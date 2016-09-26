package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import mediator.App;
import model.Figure;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;

	public Canvas() {
		setBackground(Color.WHITE);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Figure> iterator = App.getInstance().getIterator();
		while (iterator.hasNext()) {
			iterator.next().paint(g);
		}
	}
}
