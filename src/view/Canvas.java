package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JPanel;

import mediator.App;
import model.Figure;

public class Canvas extends JPanel implements DrawingListener {
	private static final long serialVersionUID = 1L;
	private Tool activeTool = new SelectionTool();

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

	public void init() {
		App.getInstance().addDrawingListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Figure> iterator = App.getInstance().getIterator();
		while (iterator.hasNext()) {
			iterator.next().paint((Graphics2D) g);
		}
	}

	protected void setActiveTool(Tool activeTool) {
		this.activeTool = activeTool;
	}

	protected Tool getActiveTool() {
		return activeTool;
	}

	@Override
	public void update(final DrawingEvent event) {
		repaint();
	}
}
