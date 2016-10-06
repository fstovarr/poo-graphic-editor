package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import org.reflections.Reflections;

import mediator.App;

public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	private static final String TITLE_TOOLBAR = "Tools";
	private final ArrayList<JComponent> buttons = new ArrayList<>();

	public ToolBar() {
		super(TITLE_TOOLBAR, JToolBar.VERTICAL);
		// addAutoTools();
		addManualTools();
		setActions();
		setFloatable(true);
	}

	@SuppressWarnings(value = { "unused" })
	private void addAutoTools() {
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
	}

	private void addManualTools() {
		buttons.add(new ToolButton(new SelectionTool()));
		buttons.add(new ToolButton(new EliminationTool()));
		buttons.add(new JToolBar.Separator(new Dimension(10, 10)));
		buttons.add(new ToolButton(new LineCreationTool()));
		buttons.add(new ToolButton(new RectangleCreationTool()));
		buttons.add(new ToolButton(new EllipseCreationTool()));
		buttons.add(new ToolButton(new TextCreationTool()));
	}

	private void setActions() {
		Iterator<JComponent> iterator = buttons.iterator();

		while (iterator.hasNext()) {
			JComponent component = (JComponent) iterator.next();

			if (component instanceof ToolButton) {
				ToolButton selectedTool = (ToolButton) component;

				selectedTool.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (selectedTool.getTool() instanceof InteractiveTool) {
							InteractiveTool it = App.getInstance().getActiveTool();
							int index = buttons.indexOf(new ToolButton(it));

							if (index != -1) {
								((ToolButton) buttons.get(index)).setSelected(false);
							}
						}
						selectedTool.apply();
					}
				});
			}
			add(component, BorderLayout.CENTER);
		}
	}

	protected class ToolButton extends JToggleButton {
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

		public void apply() {
			if (tool instanceof InteractiveTool) {
				setSelected(true);
				App.getInstance().setActiveTool((InteractiveTool) tool);
			} else if (tool instanceof ActionTool) {
				((ActionTool) tool).applyTool();
				setSelected(false);
			}
		}

		public Tool getTool() {
			return tool;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof ToolButton)) {
				return false;
			} else if (this == (ToolButton) obj) {
				return true;
			} else if (this.tool == ((ToolButton) obj).getTool()) {
				return true;
			} else {
				return false;
			}
		}
	}

}
