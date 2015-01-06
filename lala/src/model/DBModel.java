package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import db.DBHelper;

public class DBModel implements ModelInterface {
	private DBHelper dbHelper;

	private Set<Long> lockedSubscribers = new HashSet<Long>();

	private Set<Long> lockedPhones = new HashSet<Long>();
	public DBModel() {

	}

	public DBModel(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	@Override
	public Phone getPhone(Long id) {
		return dbHelper.executeSelect("select * from Phone where id=" + id,
				"getPhone");
	}

	
	public List<Long> getPhonesBySubscriber(Long subscriberId) {

		return dbHelper.executeSelect(
				"select id from phone where id_subscriber = " + subscriberId,
				"getPhonesBySubscriber");

	}

	@Override
	public List<Phone> getPhones() {
		String query = "select * from Phone";
		List<Phone> result = dbHelper.executeSelect(query, "getPhones");
		return result;

	}

	@Override
	public List<Subscriber> getSubscribers() {
		String query = "select * from Subscriber";
		List<Subscriber> result = dbHelper.executeSelect(query,
				"getSubscribers");
		//List<Subscriber> result1 = new ArrayList<>();
		//for (Subscriber subs : result) {
			//subs.setPhoneList(getPhonesBySubscriber(subs.getId()));
			//result1.add(subs);
		//}
		return result;
	}

	@Override
	public void addPhone(Phone phone) {
		String query = String
				.format("insert into Phone (id, Hours, Balance, id_subscriber) values (%d,%f,%f,%d)",
						phone.getId(), phone.getHours(), phone.getBalance(),
						phone.getSubscriber());
		dbHelper.executeStatement(query);
	}
	@Override
	public void addSubscriber(Subscriber subscriber) {
		String query = String
				.format("insert into Subscriber (id, Passport, FIO, Address, Birthday) values (%d,'%s','%s','%s','%s')",
						subscriber.getId(), subscriber.getPassport(),
						subscriber.getFio(), subscriber.getAddress(),
						subscriber.getBirthday());
		dbHelper.executeStatement(query);

	}
	@Override
	public void changeSubscriber(Subscriber newSubscriber) {
		String query = String
				.format("update Subscriber set passport='%s', fio = '%s', address = '%s', birthday='%s'  where id=%d",
						newSubscriber.getPassport(), newSubscriber.getFio(),
						newSubscriber.getAddress(),
						newSubscriber.getBirthday(), newSubscriber.getId());
		dbHelper.executeStatement(query);
	}
	@Override
	public void changePhone(Phone phone) {
		String query = String
				.format("update Phone set hours=%f, balance = %f, id_subscriber = %d  where id=%d",
						phone.getHours(), phone.getBalance(),
						phone.getSubscriber(), phone.getId());
		dbHelper.executeStatement(query);

	}

	@Override
	public void removeSubscriber(Long id) {
		dbHelper.executeStatement("delete from Subscriber where id=" + id);

	}

	@Override
	public void removePhone(Long id) {
		dbHelper.executeStatement("delete from Phone where id=" + id);

	}

	@Override
	public boolean isSubscriberLocked(Long id) {
		return lockedSubscribers.contains(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ModelInterface#lockSubscriber(java.lang.Long)
	 */
	@Override
	public synchronized boolean lockSubscriber(Long id) {
		if (isSubscriberLocked(id))
			return false;
		lockedSubscribers.add(id);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ModelInterface#unlockSubscriber(java.lang.Long)
	 */
	@Override
	public void unlockSubscriber(Long id) {
		lockedSubscribers.remove(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ModelInterface#isPhoneLocked(java.lang.Long)
	 */
	@Override
	public boolean isPhoneLocked(Long id) {
		return lockedPhones.contains(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ModelInterface#lockPhone(java.lang.Long)
	 */
	@Override
	public synchronized boolean lockPhone(Long id) {
		if (isPhoneLocked(id))
			return false;
		lockedPhones.add(id);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ModelInterface#unlockPhone(java.lang.Long)
	 */
	@Override
	public void unlockPhone(Long id) {
		lockedPhones.remove(id);
	}

	@Override
	public Subscriber getSubscriber(Long id) {
		Subscriber res = dbHelper.executeSelect(
				"select * from Subscriber where id=" + id, "getSubscriber");
		//res.setPhoneList(getPhonesBySubscriber(id));
		return res;
	}

}
