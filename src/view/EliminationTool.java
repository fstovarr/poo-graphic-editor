package view;

import mediator.App;
import model.Figure;

public class EliminationTool extends SelectionTool {
	@Override
	protected void applyTool(Figure figure, boolean flag) {
		super.applyTool(figure, flag);
		if (flag) {
			App.getInstance().removeFigure(figure);
			setIterator(App.getInstance().getIterator());
		}
	}
}
