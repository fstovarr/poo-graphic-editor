package view;

public interface DrawingListener {

	public static enum DrawingEvent {
		MODIFIED, SELECTED, DESELECTED, SAVED;
	}

	void update(DrawingEvent event);
}
