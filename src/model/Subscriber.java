/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;


/**
 * 
 * @author sasha
 */

public class Subscriber  {
	private static final long serialVersionUID = 1L;

	private BigDecimal id;
	private String passport;
	private String fio;
	private String address;
	private String birthday;
	private List<Phone> phoneList;

	public Subscriber() {
	}

	public Subscriber(BigDecimal id) {
		this.id = id;
	}

	public Subscriber(BigDecimal id, String passport, String fio,
			String address, String birthday) {
		this.id = id;
		this.passport = passport;
		this.fio = fio;
		this.address = address;
		this.birthday = birthday;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public List<Phone> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
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
		if (!(object instanceof Subscriber)) {
			return false;
		}
		Subscriber other = (Subscriber) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.Subscriber[ id=" + id + " ]";
	}

}
