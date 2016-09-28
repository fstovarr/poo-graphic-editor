package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JPanel;

import mediator.App;
import model.Figure;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private Tool activeTool = new RectangleCreationTool();

	public Canvas() {
		super();
		setBackground(Color.WHITE);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				activeTool.mousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				activeTool.mouseReleased(e);
			}
		});
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
