package model;

import java.io.Serializable;

public class Model implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Phones phones = Phones.getInstance();
	private static Subscribers subscribers = Subscribers.getInstance();
	private static Model instance;

	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;

	}
}
