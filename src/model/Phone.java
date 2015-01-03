package model;

import java.io.Serializable;

public class Phone implements Serializable {

	private static final long serialVersionUID = -2955147717500663667L;
	private Long id;
	private double hours;
	private double balance;
	private Subscriber subscriber;

	public Phone() {
	}

	public Phone(Long id) {
		this.id = id;
	}

	public Phone(Long id, double balance, Subscriber s) {
		this.id = id;
		this.hours = 0;
		this.balance = balance;
		subscriber = s;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Phone)) {
			return false;
		}
		Phone other = (Phone) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return id.toString();
	}

}
