package controller.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import model.Phone;
import model.Subscriber;
import remote.OperationRequest;
import remote.OperationResponse;
import controller.Controller;

public class ClientController implements Controller {
	private final Socket server;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ClientController(Socket server) throws IOException {
		this.server = server;
		in = new ObjectInputStream(this.server.getInputStream());
		out = new ObjectOutputStream(this.server.getOutputStream());
	}

	public Subscriber getSubscriber(Long id) {
		OperationResponse response = sendMessage(new OperationRequest(
				"getSubscriber", id));
		return (Subscriber) response.getObject();
	}

	public void addSubscriber(Subscriber Subscriber) /* throws CreationException */{
		OperationResponse response = sendMessage(new OperationRequest(
				"addSubscriber", Subscriber));
		/*
		 * if (response.getException() != null && response.getException()
		 * instanceof CreationException) throw (CreationException)
		 * response.getException();
		 */
	}

	public void replaceSubscriber(Subscriber newSubscriber) {
		sendMessage(new OperationRequest("replaceSubscriber", newSubscriber));
	}

	public void deleteSubscriber(Long SubscriberId) {
		sendMessage(new OperationRequest("deleteSubscriber", SubscriberId));
	}

	public void addPhone(Phone location) /* throws CreationException */{
		OperationResponse response = sendMessage(new OperationRequest(
				"addPhone", location));
		/*
		 * if (response.getException() != null && response.getException()
		 * instanceof CreationException) throw (CreationException)
		 * response.getException();
		 */
	}

	public void replacePhone(Phone location) {
		sendMessage(new OperationRequest("replacePhone", location));
	}

	public Phone getLocation(Long id) {
		OperationResponse response = sendMessage(new OperationRequest(
				"getLocation", id));
		return (Phone) response.getObject();
	}

	public void deletePhone(Long PhoneId) {
		sendMessage(new OperationRequest("deletePhone", PhoneId));
	}

	public List<Phone> getLocationsBySubscriber(Long SubscriberId) {
		OperationResponse response = sendMessage(new OperationRequest(
				"getLocationsBySubscriber", SubscriberId));
		return (List<Phone>) response.getObject();
	}

	public List<Subscriber> getSubscribers() {
		OperationResponse response = sendMessage(new OperationRequest(
				"getSubscribers"));
		return (List<Subscriber>) response.getObject();
	}

	private synchronized OperationResponse sendMessage(OperationRequest request) {
		try {
			out.writeObject(request);
			out.flush();
			out.reset();

			OperationResponse res = (OperationResponse) in.readObject();
			return res;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void replacePhone(Phone phone, Subscriber oldSubscriber) {
		OperationResponse response = sendMessage(new OperationRequest(
				"replacePhone", phone, oldSubscriber));

	}

	@Override
	public Phone getPhone(Long id) {
		OperationResponse response = sendMessage(new OperationRequest(
				"getPhone", id));
		return (Phone) response.getObject();
	}

	@Override
	public List<Phone> getPhonesBySubscriber(Long subscriberId) {
		OperationResponse response = sendMessage(new OperationRequest(
				"getPhonesBySubscriber", subscriberId));
		return (List<Phone>) response.getObject();
	}

	@Override
	public List<Phone> getPhones() {
		OperationResponse response = sendMessage(new OperationRequest(
				"getPhones"));
		return (List<Phone>) response.getObject();
	}

	public boolean tryLockSubscriber(Long id) {
		OperationResponse response = sendMessage(new OperationRequest(
				"tryLockSubscriber", id));
		return (Boolean) response.getObject();
	}

	public void unlockSubscriber(Long id) {
		sendMessage(new OperationRequest("unlockSubscriber", id));
	}

	public boolean tryLockPhone(Long id) {
		OperationResponse response = sendMessage(new OperationRequest(
				"tryLockPhone", id));
		return (Boolean) response.getObject();
	}

	public void unlockPhone(Long id) {
		sendMessage(new OperationRequest("unlockPhone", id));
	}

}
