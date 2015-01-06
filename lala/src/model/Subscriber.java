package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="subscriber")
public class Subscriber implements Serializable {

	private static final long serialVersionUID = 6180258311032093626L;
	private Long id;
	private String passport;
	private String fio;
	private String address;
	private String birthday;
	private List<Long> phoneIdList;

	public Subscriber() {
		phoneIdList = new ArrayList<>();
	}

	public Subscriber(Long id) {
		this.id = id;
		phoneIdList = new ArrayList<>();
	}

	public Subscriber(Long id, String passport, String fio, String address,
			String birthday) {
		this.id = id;
		this.passport = passport;
		this.fio = fio;
		this.address = address;
		this.birthday = birthday;
		phoneIdList = new ArrayList<>();
	}
	//@XmlAttribute
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	//@XmlAttribute
	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}
	//@XmlAttribute
	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}
	//@XmlAttribute
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	//@XmlAttribute
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	//@XmlAttribute
	public List<Long> getPhoneList() {
		return phoneIdList;
	}

	public String getPhoneListAsString() {
		String result = "";
		for (Long p : phoneIdList) {
			result += p.toString() + ",";
		}
		if (!result.isEmpty())
			return result.substring(0, result.length() - 1);
		else
			return "";
	}

	public void setPhoneList(List<Long> phoneIdList) {
		this.phoneIdList = phoneIdList;
	}

	public void removePhoneById(Long phoneId) {
		if (phoneIdList != null) {
			for (Long p : phoneIdList) {
				if (p == phoneId) {
					phoneIdList.remove(p);
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
