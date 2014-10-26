/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author sasha
 */

public class Phone {
	private static final long serialVersionUID = 1L;
	private BigDecimal id;
	private double hours;
	private double balance;
	private Subscriber idSubscriber;

	public Phone() {
	}

	public Phone(BigDecimal id) {
		this.id = id;
	}

	public Phone(BigDecimal id, double hours, double balance) {
		this.id = id;
		this.hours = hours;
		this.balance = balance;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
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

	public Subscriber getIdSubscriber() {
		return idSubscriber;
	}

	public void setIdSubscriber(Subscriber idSubscriber) {
		this.idSubscriber = idSubscriber;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
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
		return "model.Phone[ id=" + id + " ]";
	}

}
