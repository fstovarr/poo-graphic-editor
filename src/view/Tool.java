package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import mediator.App;

public abstract class Tool extends MouseAdapter implements Command {
	private String name, iconPath;
	private Point ptPressed;
	private Point ptReleased;
	private Cursor cursor;
	private Stroke dashStroke;

	public Tool(String iconPath, String name) {
		this.name = name;
		this.iconPath = iconPath;
		cursor = Cursor.getDefaultCursor();
		dashStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, new float[] { 10.0f, 2.0f },
				0.0f);
	}

	@Override
	public void execute() {
		processMouse();
	}

	public Cursor getCursor() {
		return cursor;
	}

	public String getIconPath() {
		return iconPath;
	}

	public String getName() {
		return name;
	}

	protected Point getPtPressed() {
		return ptPressed;
	}

	protected Point getPtReleased() {
		return ptReleased;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		ptReleased = ptPressed = e.getPoint();
	}

	/**
	 * [[SuppressWarningsSpartan]]
	 */
	private void drawFeedback(Graphics2D g) {
		int x = (ptPressed.x < ptReleased.x ? ptPressed.x : ptReleased.x);
		int y = (ptPressed.y < ptReleased.y ? ptPressed.y : ptReleased.y);
		int w = Math.abs(ptReleased.x - ptPressed.x);
		int h = Math.abs(ptReleased.y - ptPressed.y);

		g.drawRect(x, y, w, h);
	}

	public void processMouseDragged(Point pt) {
		Graphics2D g = App.getInstance().getGraphics();
		g.setColor(Color.RED);
		g.setXORMode(Color.WHITE);
		g.setStroke(dashStroke);

		drawFeedback(g);
		ptReleased = pt;
		drawFeedback(g);

		g.dispose();
	}

	protected void setPtPressed(Point p) {
		ptPressed = p;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		ptReleased = e.getPoint();
		execute();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		processMouseDragged(e.getPoint());
	}

	protected abstract void processMouse();

	protected void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}
}
