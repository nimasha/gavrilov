package controller;

import java.util.List;

import model.Phone;
import model.Subscriber;

public interface Controller {

	public Subscriber getSubscriber(Long id);

	public void addSubscriber(Subscriber subscriber);

	public void replaceSubscriber(Subscriber newSubscriber);

	public void deleteSubscriber(Long subscriberId);

	public void addPhone(Phone phone);

	public void replacePhone(Phone phone, Long oldSubscriber);

	public Phone getPhone(Long id);

	public void deletePhone(Long phoneId);

	public List<Long> getPhonesBySubscriber(Long subscriberId);

	public List<Phone> getPhones();

	public List<Subscriber> getSubscribers();

    boolean tryLockSubscriber(Long id);
	
	void unlockSubscriber(Long id);

	boolean tryLockPhone(Long id);
	
	void unlockPhone(Long id);
}
