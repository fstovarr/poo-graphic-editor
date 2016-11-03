package view;

public interface DrawingListener {

	public static enum DrawingEvent {
		MODIFIED, SELECTED, DESELECTED, SAVED, LOADED, UNDO_REDO, NEW;
	}

	void update(DrawingEvent event);
}
