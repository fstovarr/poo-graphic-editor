package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.bric.swing.ColorPicker;

import mediator.App;

public class View extends JFrame implements DrawingListener {
	private static final long serialVersionUID = 1L;
	private Canvas canvas;
	private MenuBarHelper bar;
	private ToolBarHelper toolBar;
	private ThicknessPicker tp;

	public View(final String title) {
		super(title);
		bar = new MenuBarHelper();
		toolBar = new ToolBarHelper();
		canvas = new Canvas();
		tp = new ThicknessPicker(this, "Set thickness");
		add(BorderLayout.CENTER, canvas);
		add(toolBar, BorderLayout.EAST);
		setJMenuBar(bar);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void init() {
		App.getInstance().addDrawingListener(this);
		bar.init();
		toolBar.init();
		canvas.init();

	}

	public Tool getActiveTool() {
		return canvas.getActiveTool();
	}

	public void setActiveTool(Tool tool) {
		canvas.setActiveTool(tool);
	}

	public Color showColorChooser(String title) {
		return ColorPicker.showDialog(this, title, Color.WHITE, true);
	}

	public void showThicknessChooser(String title, ActionListener listener) {
		tp.showThicknessPicker(listener);
	}

	public int getThicknessValue() {
		return tp.getValue();
	}

	@Override
	public void setCursor(Cursor cursor) {
		canvas.setCursor(cursor);
	}

	@Override
	public void update(DrawingEvent event) {
		String fileName = App.getInstance().getFileName();

		if (event == DrawingEvent.NEW) {
			fileName = App.SUG_FILE_NAME;
		}

		if (event == DrawingEvent.MODIFIED) {
			setTitle(App.TITLE_APP + " - " + fileName + " *");
		}
		if (event == DrawingEvent.SAVED) {
			setTitle(App.TITLE_APP + " - " + fileName);
		}
	}
}