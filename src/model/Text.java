package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import view.BoundBox;
import view.ControlPoint;

public class Text extends Figure {
	private String text;
	private Font font;

	public Text(final BoundBox boundBox, String text, Color color) {
		super(boundBox, color);
		this.text = text;
		font = new Font("TimesRoman", Font.PLAIN, boundBox.height);
	}

	@Override
	protected void doPaint(Graphics g) {
		BoundBox box = getBoundBox();
		g.setFont(font);
		g.setColor(getColor());

		FontMetrics fm = g.getFontMetrics(font);
		box.width = fm.stringWidth(text);
		box.height = fm.getHeight();

		int dim = ControlPoint.SIZE / 2;
		g.clipRect(box.x - dim, box.y - dim, box.width + dim * 2, box.height + dim * 2);
		g.drawString(text, box.x, box.y + fm.getAscent());
	}
}
