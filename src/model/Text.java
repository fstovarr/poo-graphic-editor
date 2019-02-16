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
	}

	@Override
	protected void doPaint(Graphics2D g) {
		BoundBox box = getBoundBox();
		font = new Font("TimesRoman", Font.PLAIN, box.height);
		g.setFont(font);

		FontMetrics fm = g.getFontMetrics(font);
		box.width = fm.stringWidth(text);

		int dim = BoundBox.getSizeControlPoint() / 2;
		g.setClip(box.x - dim, box.y - dim, box.width + 2 * dim, box.height + 2 * dim);
		g.drawString(text, box.x, box.y + (fm.getHeight() / 2 + fm.getDescent()));
		g.setClip(null);
	}
}
