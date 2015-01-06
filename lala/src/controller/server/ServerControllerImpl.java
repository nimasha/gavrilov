package controller.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;

import model.DBModel;
import model.Model;
import model.ModelInterface;
import model.Phone;
import model.Subscriber;
import controller.ServerSideNotificationController;
import db.DBHelper;

public class ServerControllerImpl implements ServerController {

	private ModelInterface model = new DBModel(new DBHelper());
	private ServerSideNotificationController notificationController = new ServerSideNotificationController();

	public Subscriber getSubscriber(Long id) {
		return model.getSubscriber(id);
	}

	public void addSubscriber(Subscriber subscriber) {
		if (subscriber == null)
			return;
		model.addSubscriber(subscriber);
		notificationController.subscriberAdded(subscriber);
	}

	public void replaceSubscriber(Subscriber newSubscriber) {
		if (newSubscriber == null)
			return;
		model.changeSubscriber(newSubscriber);
		notificationController.subscriberChanged(newSubscriber);
	}

	public void deleteSubscriber(Long subscriberId) {

		List<Long> phones = model.getPhonesBySubscriber(subscriberId);
		for (Long phone : phones) {
			model.changePhone(new Phone(phone));
			//model.removePhone(id);
		}
		Subscriber subscToRemove = model.getSubscriber(subscriberId);
		model.removeSubscriber(subscriberId);
		notificationController.subscriberRemoved(subscToRemove);
	}

	public void addPhone(Phone phone) {
		/*if (phone.getSubscriber() != null)
			model.getSubscriber(phone.getSubscriber()).getPhoneList()
					.add(phone.getId());*/
		model.addPhone(phone);
		notificationController.phoneAdded(phone);
	}

	public void replacePhone(Phone phone, Long oldSubscriber) {
		/*if (oldSubscriber != null) {
			Subscriber newSub = model.getSubscriber(oldSubscriber);
			//newSub.removePhoneById(phone.getId());
			model.changeSubscriber(newSub);
		}*/
		/*if (phone.getSubscriber() != null)
			model.getPhonesBySubscriber(phone.getSubscriber()).add(
					phone.getId());*/
		model.changePhone(phone);
		notificationController.phoneChanged(phone);
	}

	public Phone getPhone(Long id) {
		return model.getPhone(id);
	}

	public void deletePhone(Long phoneId) {
		// unlockPhone(phoneId);
		Subscriber subsc = model.getSubscriber(model.getPhone(phoneId)
				.getSubscriber());
		/*if (subsc != null) {
			subsc.removePhoneById(phoneId);
		}*/
		Phone phone = model.getPhone(phoneId);
		model.removePhone(phoneId);
		notificationController.phoneRemoved(phone);
	}

	public List<Long> getPhonesBySubscriber(Long subscriberId) {
		if (subscriberId == null)
			return Collections.emptyList();
		return model.getPhonesBySubscriber(subscriberId);
	}

	public List<Phone> getPhones() {
		return model.getPhones();
	}

	public List<Subscriber> getSubscribers() {
		return model.getSubscribers();
	}

	@Override
	public void addListener(Socket socket) {
		notificationController.addListener(socket);
	}

	public boolean tryLockSubscriber(Long id) {
		return model.lockSubscriber(id);
	}

	public void unlockSubscriber(Long id) {
		model.unlockSubscriber(id);
	}

	public boolean tryLockPhone(Long id) {
		return model.lockPhone(id);
	}

	public void unlockPhone(Long id) {
		model.unlockPhone(id);
	}

}
