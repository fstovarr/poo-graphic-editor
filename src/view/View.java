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
			Set<Class<? extends ItemToolbar>> classes = reflections.getSubTypesOf(ItemToolbar.class);

			Iterator<Class<? extends ItemToolbar>> iterator = classes.iterator();
			while (iterator.hasNext()) {
				Class<? extends ItemToolbar> itemToolbar = iterator.next();
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
					if (selectedTool.getTool() instanceof InteractiveTool) {
						canvas.setActiveTool((InteractiveTool) selectedTool.getTool());
					}

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
		private ItemToolbar tool;

		public ToolButton(ItemToolbar tool) {
			this.tool = tool;

			if (tool.getIcon() == null || tool.equals(null)) {
				setText(tool.getName());
			} else {
				setIcon(tool.getIcon());
			}
		}

		public ItemToolbar getTool() {
			return tool;
		}
	}
}