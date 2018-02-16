package model;

import java.io.Serializable;

public abstract class GameObject implements Serializable {

	protected Point location;

	public GameObject() {
	}

	public GameObject(Point location) {
		this.location = location;
	}

	public Point getLocation() {
		return location;
	}

	protected void setLocation(Point location) {
		this.location = location;
	}

	public String toString() {
		return "location= "+location;
	}
	
}
