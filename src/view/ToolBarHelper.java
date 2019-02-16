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
	private static final long serialVersionUID = 1;
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

	@SuppressWarnings("unused")
	private void addAutoTools() {
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

							if (index != -1)
								((ToolButton) buttons.get(index)).setSelected(false);
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
		private static final long serialVersionUID = 1;
		private Command command;
		private ImageIcon icon;

		public ToolButton(Command command) {
			this.command = command;

			icon = getResizedIcon(command.getIconPath());

			if (icon != null)
				setIcon(icon);
			else
				setText(command.getName());
		}

		public void apply() {
			if (!(command instanceof Tool)) {
				command.execute();
				setSelected(false);
			} else {
				Tool tool = (Tool) command;
				setSelected(true);
				App.getInstance().setActiveTool(tool);
			}
		}

		private ImageIcon getResizedIcon(String iconPath) {
			ImageIcon temp = null;

			if (iconPath != null)
				try {
					URL resource = this.getClass().getClassLoader().getResource(iconPath);
					if (resource == null)
						throw new Exception("Icon don't found");
					temp = new ImageIcon(
							ImageIO.read(resource).getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
				} catch (Exception e) {
					e.printStackTrace();
				}

			return temp;
		}

		public Command getCommand() {
			return command;
		}

		@Override
		public boolean equals(Object obj) {
			return obj instanceof ToolButton && ((ToolButton) obj == this || this.command == ((ToolButton) obj).getCommand()
					|| this.command instanceof SelectionTool && ((ToolButton) obj).getCommand() instanceof SelectionTool);
		}
	}

	private void enableButtons(boolean enable) {
		for (int i = 2; i <= 5; ++i)
			buttons.get(i).setEnabled(enable);
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
		default:
			break;
		}
	}
}
