package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import org.reflections.Reflections;

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

		ArrayList<ToolButton> buttons = new ArrayList<>();

		try {
			Reflections reflections = new Reflections(this.getClass().getPackage());
			Set<Class<? extends Tool>> classes = reflections.getSubTypesOf(Tool.class);

			Iterator<Class<? extends Tool>> iterator = classes.iterator();
			while (iterator.hasNext()) {
				Class<? extends Tool> itemToolbar = iterator.next();
				if (!Modifier.isAbstract(itemToolbar.getModifiers())) {
					buttons.add(new ToolButton(itemToolbar.newInstance()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Iterator<ToolButton> iterator = buttons.iterator();
		while (iterator.hasNext()) {
			ToolButton selectedTool = (ToolButton) iterator.next();
			selectedTool.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Tool tool = selectedTool.getTool();
					if (tool instanceof InteractiveTool) {
						int index = buttons.indexOf(new ToolButton(canvas.getActiveTool()));

						if (index != -1) {
							((ToolButton) buttons.get(index)).setSelected(false);
						}

						canvas.setActiveTool((InteractiveTool) tool);
					} else if (tool instanceof ActionTool) {
						((ActionTool) tool).applyTool();
						selectedTool.setSelected(false);
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

		public ToolButton(Tool tool) {
			this.tool = tool;

			if (tool.getIcon() == null || tool.equals(null)) {
				setText(tool.getName());
			} else {
				setIcon(tool.getIcon());
			}
		}

		public Tool getTool() {
			return tool;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == (ToolButton) obj) {
				return true;
			} else if (this.tool == ((ToolButton) obj).getTool()) {
				return true;
			} else {
				return false;
			}
		}
	}
}