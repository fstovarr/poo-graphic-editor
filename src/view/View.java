package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private Canvas canvas;
	private MenuBarHelper bar;
	private ToolBarHelper toolBar;

	public View(final String title) {
		super(title);
		bar = new MenuBarHelper();
		toolBar = new ToolBarHelper();
		canvas = new Canvas();
		add(BorderLayout.CENTER, canvas);
		add(toolBar, BorderLayout.EAST);
		setJMenuBar(bar);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void init() {
		bar.init();
		toolBar.init();
		canvas.init();
	}

	public Tool getActiveTool() {
		return canvas.getActiveTool();
	}

	public void setActiveTool(Tool tool) {
		canvas.setActiveTool((Tool) tool);
	}
}