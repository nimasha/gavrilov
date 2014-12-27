package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author sasha
 */

public class Subscriber implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6180258311032093626L;
	private Long id;
	private String passport;
	private String fio;
	private String address;
	private String birthday;
	private List<Phone> phoneList;

	public Subscriber() {
		phoneList = new ArrayList<>();
	}

	public Subscriber(Long id) {
		this.id = id;
		phoneList = new ArrayList<>();
	}

	public Subscriber(Long id, String passport, String fio, String address,
			String birthday) {
		this.id = id;
		this.passport = passport;
		this.fio = fio;
		this.address = address;
		this.birthday = birthday;
		phoneList = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPhoneListAsString() {
		String result = "";
		for (Phone p : phoneList) {
			result += p.toString() + ",";
		}
		if (!result.isEmpty())
			return result.substring(0, result.length() - 1);
		else
			return "";
	}

	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}

	public void removePhoneById(Long phoneId) {
		if (phoneList != null) {
			for (Phone p : phoneList) {
				if (p.getId() == phoneId) {
					phoneList.remove(p);
					break;
				}
			}
		}
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
		return fio;
	}

}
