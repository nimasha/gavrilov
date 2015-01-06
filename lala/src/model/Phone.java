package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="phone")
public class Phone implements Serializable {

	private static final long serialVersionUID = -2955147717500663667L;
	private Long id;
	private double hours;
	private double balance;
	private Long subscriberId;

	public Phone() {
	}

	public Phone(Long id) {
		this.id = id;
	}

	public Phone(Long id, double balance, Long sId) {
		this.id = id;
		this.hours = 0;
		this.balance = balance;
		subscriberId = sId;

	}
	
	public Phone(Long id, double balance, double hours, Long sId) {
		this.id = id;
		this.hours = hours;
		this.balance = balance;
		subscriberId = sId;

	}
	//@XmlAttribute
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	//@XmlAttribute
	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}
	//@XmlAttribute
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	//@XmlAttribut
	public Long getSubscriber() {
		return subscriberId;
	}

	public void setSubscriber(Long subscriber) {
		this.subscriberId = subscriber;
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
