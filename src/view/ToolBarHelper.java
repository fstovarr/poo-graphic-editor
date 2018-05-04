package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

//import org.reflections.Reflections;

//import org.reflections.Reflections;

import mediator.App;

public class ToolBarHelper extends JToolBar implements DrawingListener {
	private static final long serialVersionUID = 1L;
	private static final String TITLE_TOOLBAR = "Tools";
	private final ArrayList<JComponent> buttons = new ArrayList<>();

	public ToolBarHelper() {
		super(TITLE_TOOLBAR, JToolBar.VERTICAL);
		// addAutoTools();
		addManualTools();
		setActions();
		setFloatable(false);
	}

	public void init() {
		App.getInstance().addDrawingListener(this);
	}

	@SuppressWarnings(value = { "unused" })
	private void addAutoTools() {
		try {
			/*Reflections reflections = new Reflections(this.getClass().getPackage());
			//Set<Class<? extends Tool>> classes = reflections.getSubTypesOf(Tool.class);
			//Iterator<Class<? extends Tool>> iterator = classes.iterator();

			while (iterator.hasNext()) {
				Class<? extends Tool> itemToolbar = iterator.next();
				if (!Modifier.isAbstract(itemToolbar.getModifiers())) {
					buttons.add(new ToolButton(itemToolbar.newInstance()));
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addManualTools() {
		buttons.add(new ToolButton(new SelectionTool()));
		buttons.add(new JToolBar.Separator(new Dimension(10, 10)));
		buttons.add(new ToolButton(new DeleteCommand()));
		buttons.add(new ToolButton(new ThicknessCommand()));
		buttons.add(new ToolButton(new FillColorCommand()));
		buttons.add(new ToolButton(new StrokeColorCommand()));
		buttons.add(new JToolBar.Separator(new Dimension(10, 10)));
		buttons.add(new ToolButton(new LineCreationTool()));
		buttons.add(new ToolButton(new RectangleCreationTool()));
		buttons.add(new ToolButton(new EllipseCreationTool()));
		buttons.add(new ToolButton(new TextCreationTool()));
		buttons.add(new ToolButton(new RomboCreationTool()));
	}

	private void setActions() {
		Iterator<JComponent> iterator = buttons.iterator();

		enableButtons(false);

		while (iterator.hasNext()) {
			JComponent component = (JComponent) iterator.next();

			if (component instanceof ToolButton) {
				ToolButton selectedTool = (ToolButton) component;

				selectedTool.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (selectedTool.getCommand() instanceof Tool) {
							Tool it = App.getInstance().getActiveTool();
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

	private class ToolButton extends JToggleButton {
		private static final int ICON_SIZE = 32;
		private static final long serialVersionUID = 1L;
		private Command command;
		private ImageIcon icon;

		public ToolButton(Command command) {
			this.command = command;

			icon = getResizedIcon(command.getIconPath());

			if (icon == null) {
				setText(command.getName());
			} else {
				setIcon(icon);
			}
		}

		public void apply() {
			if (command instanceof Tool) {
				Tool tool = (Tool) command;
				setSelected(true);
				App.getInstance().setActiveTool(tool);
			} else {
				command.execute();
				setSelected(false);
			}
		}

		private ImageIcon getResizedIcon(String iconPath) {
			ImageIcon temp = null;

			if (iconPath != null) {
				try {
					URL resource = this.getClass().getClassLoader().getResource(iconPath);
					if (resource != null) {
						temp = new ImageIcon(
								ImageIO.read(resource).getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
					} else {
						throw new Exception("Icon don't found");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return temp;
		}

		public Command getCommand() {
			return command;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof ToolButton)) {
				return false;
			} else if (this == (ToolButton) obj) {
				return true;
			} else if (this.command == ((ToolButton) obj).getCommand()) {
				return true;
			} else if (this.command instanceof SelectionTool
					&& ((ToolButton) obj).getCommand() instanceof SelectionTool) {
				return true;
			} else {
				return false;
			}
		}
	}

	private void enableButtons(boolean enable) {
		for (int i = 2; i <= 5; i++) {
			buttons.get(i).setEnabled(enable);
		}
	}

	@Override
	public void update(DrawingEvent event) {
		switch (event) {
		case SELECTED:
			enableButtons(true);
			break;

		case DESELECTED:
			enableButtons(false);
			break;

		case SAVED:
			break;

		default:
			break;
		}
	}
}
