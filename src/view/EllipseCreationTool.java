package view;

import java.awt.Point;

import model.Ellipse;

public class EllipseCreationTool extends CreationTool {
	public EllipseCreationTool() {
		super("resources/bwicons/ellipse1.png", "Elipse");
	}

	@Override
	protected void createInitialFigure(Point ptPressed) {
		setFigure(new Ellipse(new BoundBox(ptPressed.x, ptPressed.y, 0, 0), null, 1, null));
	}
}
