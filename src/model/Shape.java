package model;

import java.awt.Graphics2D;
import java.io.Serializable;

public interface Shape extends Serializable {
	void paint(Graphics2D g);
}
