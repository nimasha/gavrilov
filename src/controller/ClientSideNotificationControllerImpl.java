package controller;

import java.util.ArrayList;
import java.util.List;

import model.Phone;
import model.Subscriber;

public class ClientSideNotificationControllerImpl implements
		ClientSideNotificationController {
	private List<NotificationListener> listeners;

	public ClientSideNotificationControllerImpl() {
		listeners = new ArrayList<NotificationListener>();
	}

	public void subscriberChanged(Subscriber subscriber) {
		for (NotificationListener listener : listeners) {
			listener.subscriberChanged(subscriber);
		}
	}

	public void subscriberAdded(Subscriber subscriber) {
		for (NotificationListener listener : listeners) {
			listener.subscriberAdded(subscriber);
		}
	}

	public void subscriberRemoved(Long id) {
		for (NotificationListener listener : listeners) {
			listener.subscriberRemoved(id);
		}
	}

	public void phoneChanged(Phone phone) {
		for (NotificationListener listener : listeners) {
			listener.phoneChanged(phone);
		}
	}

	public void phoneAdded(Phone phone) {
		for (NotificationListener listener : listeners) {
			listener.phoneAdded(phone);
		}
	}

	public void phoneRemoved(Long id) {
		for (NotificationListener listener : listeners) {
			listener.phoneRemoved(id);
		}
	}

	public void registerListener(NotificationListener listener) {
		listeners.add(listener);
	}
}