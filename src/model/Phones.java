package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

public class Phones  {

	private static final long serialVersionUID = 1L;
	private HashMap<BigDecimal, Phone> phone_id = new HashMap<BigDecimal, Phone>();
	private static Phones instance;

	public static Phones getInstance() {
		if (instance == null) {
			instance = new Phones();
		}
		return instance;

	}

	public void addPhone(Phone newPhone) {
		phone_id.put(newPhone.getId(), newPhone);
	}

}
