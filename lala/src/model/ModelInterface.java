package model;

import java.util.List;

public interface ModelInterface {

	public abstract Subscriber getSubscriber(Long id);

	public abstract Phone getPhone(Long id);

	public abstract List<Long> getPhonesBySubscriber(Long subscriberId);

	public abstract List<Phone> getPhones();

	public abstract List<Subscriber> getSubscribers();

	public abstract void addSubscriber(Subscriber subscriber);

	public abstract void addPhone(Phone phone);

	public abstract void changeSubscriber(Subscriber newSubscriber);

	public abstract void changePhone(Phone phone);

	public abstract void removeSubscriber(Long id);

	public abstract void removePhone(Long id);

	public abstract boolean isSubscriberLocked(Long id);

	public abstract boolean lockSubscriber(Long id);

	public abstract void unlockSubscriber(Long id);

	public abstract boolean isPhoneLocked(Long id);

	public abstract boolean lockPhone(Long id);

	public abstract void unlockPhone(Long id);

}