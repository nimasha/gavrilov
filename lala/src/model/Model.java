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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "model")
@XmlAccessorType(XmlAccessType.FIELD)
public class Model implements Serializable, ModelInterface {
	private static final long serialVersionUID = 1795979026022390158L;

	private Map<Long, Subscriber> subscribers;
	private Map<Long, Phone> phones;
	@XmlTransient
	private Set<Long> lockedSubscribers;
	@XmlTransient
	private Set<Long> lockedPhones;

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

	/* (non-Javadoc)
	 * @see model.ModelInterface#getSubscriber(java.lang.Long)
	 */
	@Override
	public Subscriber getSubscriber(Long id) {
		return subscribers.get(id);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#getPhone(java.lang.Long)
	 */
	@Override
	public Phone getPhone(Long id) {
		return phones.get(id);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#getPhonesBySubscriber(java.lang.Long)
	 */
	/*@Override
	public List<Long> getPhonesBySubscriber(final Long subscriberId) {
		return subscribers.get(subscriberId).getPhoneList();
	}*/

	/* (non-Javadoc)
	 * @see model.ModelInterface#getPhones()
	 */
	@Override
	public List<Phone> getPhones() {
		return new ArrayList<Phone>(phones.values());
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#getSubscribers()
	 */
	@Override
	public List<Subscriber> getSubscribers() {
		return new ArrayList<Subscriber>(subscribers.values());
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#addSubscriber(model.Subscriber)
	 */
	@Override
	public void addSubscriber(Subscriber subscriber) {
		subscribers.put(subscriber.getId(), subscriber);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#addPhone(model.Phone)
	 */
	@Override
	public void addPhone(Phone phone) {
		phones.put(phone.getId(), phone);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#changeSubscriber(model.Subscriber)
	 */
	@Override
	public void changeSubscriber(Subscriber newSubscriber) {
		subscribers.put(newSubscriber.getId(), newSubscriber);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#changePhone(model.Phone)
	 */
	@Override
	public void changePhone(Phone phone) {
		phones.put(phone.getId(), phone);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#removeSubscriber(java.lang.Long)
	 */
	@Override
	public void removeSubscriber(Long id) {
		subscribers.remove(id);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#removePhone(java.lang.Long)
	 */
	@Override
	public void removePhone(Long id) {
		phones.remove(id);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#isSubscriberLocked(java.lang.Long)
	 */
	@Override
	public boolean isSubscriberLocked(Long id) {
		return lockedSubscribers.contains(id);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#lockSubscriber(java.lang.Long)
	 */
	@Override
	public synchronized boolean lockSubscriber(Long id) {
		if (isSubscriberLocked(id))
			return false;
		lockedSubscribers.add(id);
		return true;
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#unlockSubscriber(java.lang.Long)
	 */
	@Override
	public void unlockSubscriber(Long id) {
		lockedSubscribers.remove(id);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#isPhoneLocked(java.lang.Long)
	 */
	@Override
	public boolean isPhoneLocked(Long id) {
		return lockedPhones.contains(id);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#lockPhone(java.lang.Long)
	 */
	@Override
	public synchronized boolean lockPhone(Long id) {
		if (isPhoneLocked(id))
			return false;
		lockedPhones.add(id);
		return true;
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#unlockPhone(java.lang.Long)
	 */
	@Override
	public void unlockPhone(Long id) {
		lockedPhones.remove(id);
	}

	@Override
	public List<Long> getPhonesBySubscriber(Long subscriberId) {
		// TODO Auto-generated method stub
		return null;
	}
}
