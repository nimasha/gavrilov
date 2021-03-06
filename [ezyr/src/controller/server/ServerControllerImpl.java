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

import model.Model;
import model.Phone;
import model.Subscriber;
import controller.ServerSideNotificationController;

public class ServerControllerImpl implements ServerController {

	private Model model = new Model();
	private ServerSideNotificationController notificationController = new ServerSideNotificationController();

	public void saveModel(File outputFile) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Model.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(model, outputFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void setModel(File inputFile) throws FileNotFoundException {
		try {
			if (!inputFile.exists()) {
				throw new FileNotFoundException("File '" + inputFile.getPath()
						+ "' does not exists");
			}

			JAXBContext jaxbContext = JAXBContext.newInstance(Model.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			model = (Model) jaxbUnmarshaller.unmarshal(inputFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

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
		model.addSubscriber(newSubscriber);
		notificationController.subscriberChanged(newSubscriber);
	}

	public void deleteSubscriber(Long subscriberId) {

		List<Long> phones = model.getPhonesBySubscriber(subscriberId);
		for (Long phone : phones) {
			model.removePhone(phone);
		}
		Subscriber subscToRemove = model.getSubscriber(subscriberId);
		model.removeSubscriber(subscriberId);
		notificationController.subscriberRemoved(subscToRemove);
	}

	public void addPhone(Phone phone) {
		if (phone.getSubscriber() != null)
		 	model.getSubscriber(phone.getSubscriber()).getPhoneList().add(phone.getId());
		model.addPhone(phone);
		notificationController.phoneAdded(phone);
	}

	public void replacePhone(Phone phone, Long oldSubscriber) {
		if (oldSubscriber != null)
			model.getSubscriber(oldSubscriber).removePhoneById(phone.getId());
		if (phone.getSubscriber() != null)
			model.getPhonesBySubscriber(phone.getSubscriber()).add(
					phone.getId());
		model.addPhone(phone);
		notificationController.phoneChanged(phone);
	}

	public Phone getPhone(Long id) {
		return model.getPhone(id);
	}

	public void deletePhone(Long phoneId) {
		// unlockPhone(phoneId);
		Subscriber subsc = model.getSubscriber(model.getPhone(phoneId).getSubscriber());
		if (subsc != null) {
			subsc.removePhoneById(phoneId);
		}
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
