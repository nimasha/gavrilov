package controller;

import model.Phone;
import model.Phones;

public class PhoneController {
	private static PhoneController instance;

	public static PhoneController getInstance() {
		if (instance == null) {
			instance = new PhoneController();
		}
		return instance;

	}
	public void createPhone(Phone p) {
		Phones.getInstance().addPhone(p);
	}

	public void updatePhone() {

	}

	public void deletePhone(Phone p) {

	}

}
