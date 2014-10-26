package controller;

import model.Phones;
import model.Subscriber;
import model.Subscribers;

public class SubscriberController {

	public void createSubscriber(Subscriber s) {
		Subscribers.getInstance().addSubscriber(s);
	}

	public void updateSubscriber() {

	}

	public void deleteSubscriber(Subscriber s) {

	}
}
