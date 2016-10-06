package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private Canvas canvas;
	private JMenuBar bar;
	private JToolBar toolBar;

	public View(final String title) {
		super(title);
		canvas = new Canvas();
		bar = new MenuBar();
		toolBar = new ToolBar();

		add(BorderLayout.CENTER, canvas);
		getContentPane().add(toolBar, BorderLayout.EAST);
		setJMenuBar(bar);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void repaintCanvas() {
		canvas.repaint();
	}

	public InteractiveTool getActiveTool() {
		return canvas.getActiveTool();
	}

	public void setActiveTool(InteractiveTool tool) {
		canvas.setActiveTool((InteractiveTool) tool);
	}
}