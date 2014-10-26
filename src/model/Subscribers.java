package model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subscribers {
	private Map<BigDecimal, Subscriber> subscriber_id = new HashMap<BigDecimal, Subscriber>();
	private static Subscribers instance;

	public static Subscribers getInstance() {
		if (instance == null) {
			instance = new Subscribers();
		}
		return instance;

	}

	public void addSubscriber(Subscriber newSubscriber) {
		subscriber_id.put(newSubscriber.getId(), newSubscriber);
	}

	public List<String> getListOfSubscribers() {
		List<String> returnList = null;
		
		
		for(Subscriber subscriber:  subscriber_id.values()){
			returnList.add(subscriber.toString());
		}
		
		return returnList;
	}
}
