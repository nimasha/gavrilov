package controller.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import model.Model;
import model.Phone;
import model.Subscriber;
import controller.Controller;

public interface ServerController extends Controller {

	public Subscriber getSubscriber(Long id);

	public void addSubscriber(Subscriber subscriber);

	public void replaceSubscriber(Subscriber newSubscriber);

	public void deleteSubscriber(Long subscriberId);

	public void addPhone(Phone phone);

	public void replacePhone(Phone phone, Long oldSubscriber);

	public Phone getPhone(Long id);

	public void deletePhone(Long phoneId);

	public List<Long> getPhonesBySubscriber(Long subscriberId);

	public List<Phone> getPhones();

	public List<Subscriber> getSubscribers();

    public void addListener(Socket socket);
}
