package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.Phone;
import model.Subscriber;
import remote.OperationRequest;
import remote.OperationResponse;
import remote.RequestExecutor;

public class NotificationsListener implements Runnable {
	private final Socket socketToListen;
	private final ClientSideNotificationController notificationController;
	private final RequestExecutor requestExecutor;

	public NotificationsListener(Socket socketToListen,
			ClientSideNotificationController notificationController) {
		this.socketToListen = socketToListen;
		this.notificationController = notificationController;
		requestExecutor = new RequestExecutor(this.notificationController);
	}

	public void run() {

		try(ObjectInputStream in = new ObjectInputStream(socketToListen.getInputStream());) {
//			ObjectInputStream in = new ObjectInputStream(socketToListen.getInputStream());
			while (true) {

				JAXBContext jaxbContext = JAXBContext.newInstance(
						OperationResponse.class, OperationRequest.class,
						Phone.class, Subscriber.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();

				String xmlRequest = in.readUTF();
				OperationRequest request = (OperationRequest) jaxbUnmarshaller
						.unmarshal(new StringReader(xmlRequest));
				requestExecutor.executeRequest(request);

			}
		} catch (JAXBException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
