package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Model implements Serializable {
	private static final long serialVersionUID = 1795979026022390158L;

	private Map<Long, Subscriber> subscribers;
	private Map<Long, Phone> phones;

	private transient Set<Long> lockedSubscribers;
	private transient Set<Long> lockedPhones;

	public Model() {
		subscribers = new HashMap<Long, Subscriber>();
		phones = new HashMap<Long, Phone>();
		lockedPhones = new HashSet<Long>();
		lockedSubscribers = new HashSet<Long>();
	}

	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		lockedPhones = new HashSet<Long>();
		lockedSubscribers = new HashSet<Long>();
	}

	public Subscriber getSubscriber(Long id) {
		return subscribers.get(id);
	}

	public Phone getPhone(Long id) {
		return phones.get(id);
	}

	public List<Phone> getPhonesBySubscriber(final Long subscriberId) {
		return subscribers.get(subscriberId).getPhoneList();
	}

	public List<Phone> getPhones() {
		return new ArrayList<Phone>(phones.values());
	}

	public List<Subscriber> getSubscribers() {
		return new ArrayList<Subscriber>(subscribers.values());
	}

	public void addSubscriber(Subscriber subscriber) {
		subscribers.put(subscriber.getId(), subscriber);
	}

	public void addPhone(Phone phone) {
		phones.put(phone.getId(), phone);
	}

	public void changeSubscriber(Subscriber newSubscriber) {
		subscribers.put(newSubscriber.getId(), newSubscriber);
	}

	public void changePhone(Phone phone) {
		phones.put(phone.getId(), phone);
	}

	public void removeSubscriber(Long id) {
		subscribers.remove(id);
	}

	public void removePhone(Long id) {
		phones.remove(id);
	}

	public boolean isSubscriberLocked(Long id) {
		return lockedSubscribers.contains(id);
	}

	public synchronized boolean lockSubscriber(Long id) {
		if (isSubscriberLocked(id))
			return false;
		lockedSubscribers.add(id);
		return true;
	}

	public void unlockSubscriber(Long id) {
		lockedSubscribers.remove(id);
	}

	public boolean isPhoneLocked(Long id) {
		return lockedPhones.contains(id);
	}

	public synchronized boolean lockPhone(Long id) {
		if (isPhoneLocked(id))
			return false;
		lockedPhones.add(id);
		return true;
	}

	public void unlockPhone(Long id) {
		lockedPhones.remove(id);
	}
}
