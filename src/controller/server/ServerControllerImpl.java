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

import model.Model;
import model.Phone;
import model.Subscriber;
import controller.ServerSideNotificationController;

public class ServerControllerImpl implements ServerController {

	private Model model = new Model();
	private ServerSideNotificationController notificationController = new ServerSideNotificationController();

	public void saveModel(File outputFile) throws IOException {
		String fileName = outputFile.getPath();
		if (!fileName.endsWith(".cdb")) {
			outputFile = new File(fileName + ".cdb");
		}

		FileOutputStream fileOut = new FileOutputStream(outputFile);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(model);
		out.flush();
		out.close();

		fileOut.flush();
		fileOut.close();
	}

	public Model setModel(File inputFile) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			if (!inputFile.exists()) {
				throw new FileNotFoundException("File '" + inputFile.getPath()
						+ "' does not exists");
			}

			fis = new FileInputStream(inputFile);
			ois = new ObjectInputStream(fis);

			Object inputObject;
			inputObject = ois.readObject();

			ois.close();
			fis.close();

			if (inputObject.getClass() != Model.class) {
				throw new IOException("File format error");
			}

			model = (Model) inputObject;
			return model;

		} finally {
			if (fis != null)
				fis.close();
			if (ois != null)
				ois.close();
		}
	}

	public Subscriber getSubscriber(Long id) {
		return model.getSubscriber(id);
	}

	public void addSubscriber(Subscriber subscriber) {
		if (subscriber == null)
			return;
		model.addSubscriber(subscriber);
	}

	public void replaceSubscriber(Subscriber newSubscriber) {
		if (newSubscriber == null)
			return;
		model.addSubscriber(newSubscriber);
	}

	public void deleteSubscriber(Long subscriberId) {

		List<Phone> phones = model.getPhonesBySubscriber(subscriberId);
		for (Phone phone : phones) {
			model.removePhone(phone.getId());
		}
		model.removeSubscriber(subscriberId);
	}

	public void addPhone(Phone phone) {
		if (phone.getSubscriber() != null)
			phone.getSubscriber().getPhoneList().add(phone);
		model.addPhone(phone);

	}

	public void replacePhone(Phone phone, Subscriber oldSubscriber) {
		if (oldSubscriber != null)
			oldSubscriber.removePhoneById(phone.getId());
		if (phone.getSubscriber() != null)
			model.getPhonesBySubscriber(phone.getSubscriber().getId()).add(
					phone);
		model.addPhone(phone);
	}

	public Phone getPhone(Long id) {
		return model.getPhone(id);
	}

	public void deletePhone(Long phoneId) {
		// it should be removed from subscriber
		Subscriber subsc = model.getPhone(phoneId).getSubscriber();
		if (subsc != null) {
			subsc.removePhoneById(phoneId);
		}
		model.removePhone(phoneId);
	}

	public List<Phone> getPhonesBySubscriber(Long subscriberId) {
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
