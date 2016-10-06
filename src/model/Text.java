package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import view.BoundBox;

public class Text extends Figure {
	private String text;
	private Font font;

	public Text(final BoundBox boundBox, String text, Color color) {
		super(boundBox, color);
		this.text = text;
		font = new Font("TimesRoman", Font.PLAIN, boundBox.height);
	}

	@Override
	protected void doPaint(Graphics2D g) {
		BoundBox box = getBoundBox();
		g.setFont(font);
		g.setColor(getColor());

		FontMetrics fm = g.getFontMetrics(font);
		box.width = fm.stringWidth(text);
		box.height = fm.getHeight();
		
		int dim = BoundBox.getSizeControlPoint() / 2;
		g.setClip(box.x - dim, box.y - dim, box.width + dim * 2, box.height + dim * 2);
		g.drawString(text, box.x, box.y + fm.getAscent());
		g.setClip(null);
	}
}
