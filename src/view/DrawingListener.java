package view;

public interface DrawingListener {

	public static enum DrawingEvent {
		ADDED, REMOVED, SELECTED, DESELECTED, MOVED, DELETED, SAVED;
	}

	void update(DrawingEvent event);
}
