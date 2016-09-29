package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private Canvas canvas;
	private JMenuBar bar;
	private JToolBar toolBar;

	public View(final String title) {
		super(title);
		canvas = new Canvas();
		bar = createMenuBar();
		toolBar = createToolBar();

		add(BorderLayout.CENTER, canvas);
		getContentPane().add(toolBar, BorderLayout.EAST);
		setJMenuBar(bar);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void repaintCanvas() {
		canvas.repaint();
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("Archivo");
		JMenu editionMenu = new JMenu("Edición");
		JMenu helpMenu = new JMenu("Ayuda");

		menuBar.add(fileMenu);
		menuBar.add(editionMenu);
		menuBar.add(helpMenu);

		return menuBar;
	}

	private JToolBar createToolBar() {
		JToolBar bar = new JToolBar("Herramientas", JToolBar.VERTICAL);

		ToolButton selectionButton = new ToolButton("Selección", new SelectionTool());
		ToolButton lineButton = new ToolButton("Linea", new LineCreationTool(""));
		ToolButton rectangleButton = new ToolButton("Rectángulo", new RectangleCreationTool(""));
		ToolButton ellipseButton = new ToolButton("Elipse", new EllipseCreationTool(""));
		ToolButton textButton = new ToolButton("Texto", new TextCreationTool(""));
		ToolButton eliminationButton = new ToolButton("Borrar", new EliminationTool());

		ArrayList<ToolButton> buttons = new ArrayList<>();
		buttons.add(selectionButton);
		buttons.add(lineButton);
		buttons.add(rectangleButton);
		buttons.add(ellipseButton);
		buttons.add(textButton);
		buttons.add(eliminationButton);

		Iterator<ToolButton> iterator = buttons.iterator();
		while (iterator.hasNext()) {
			ToolButton selectedTool = (ToolButton) iterator.next();
			selectedTool.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					canvas.setActiveTool(selectedTool.getTool());

					for (ToolButton button : buttons) {
						if (button != selectedTool) {
							button.setSelected(false);
						}
					}
				}
			});
			bar.add(selectedTool, BorderLayout.CENTER);
		}

		bar.addSeparator(new Dimension(10, 10));
		bar.setFloatable(true);

		return bar;
	}

	private class ToolButton extends JToggleButton {
		private static final long serialVersionUID = 1L;
		private Tool tool;

		public ToolButton(String title, Tool tool) {
			super(title);
			this.tool = tool;
		}

		public Tool getTool() {
			return tool;
		}
	}
}
