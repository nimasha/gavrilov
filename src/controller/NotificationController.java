package controller;

import model.Phone;
import model.Subscriber;

public interface NotificationController {
	
	void subscriberChanged(Subscriber subscriber);

	void subscriberAdded(Subscriber subscriber);

	void subscriberRemoved(Long id);

	void phoneChanged(Phone phone);

	void phoneAdded(Phone phone);

	void phoneRemoved(Long id);
}
