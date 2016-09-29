package view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import mediator.App;
import model.Figure;

public class SelectionTool extends Tool {
	
	public SelectionTool() {
		super("font.png");
	}

	private Iterator<Figure> iterator;

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		Point ptPressed = getPtPressed();
		Point ptReleased = getPtReleased();

		BoundBox selectedRegion = new BoundBox(ptPressed.x, ptPressed.y, ptReleased.x - ptPressed.x,
				ptReleased.y - ptPressed.y);
		selectedRegion.normalize();

		iterator = App.getInstance().getIterator();
		while (iterator.hasNext()) {
			Figure figure = iterator.next();
			BoundBox boxFigure = figure.getNormalizedBoundBox();
			applyTool(figure, selectedRegion.contains(boxFigure) || boxFigure.contains(ptPressed)
					|| boxFigure.contains(ptReleased));
		}
		App.getInstance().repaintCanvas();
	}

	protected void applyTool(final Figure figure, boolean flag) {
		figure.setSelected(flag);
	}

	protected void setIterator(Iterator<Figure> iterator) {
		this.iterator = iterator;
	}
}
