package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.JaxbList;
import model.Phone;
import model.Subscriber;
import remote.OperationRequest;
import remote.OperationResponse;
import remote.RequestExecutor;
import controller.server.ServerController;

public class RequestProcessor implements Runnable {
	private final Socket client;
	private final ServerController controller;
	private final RequestExecutor requestExecutor;

	public RequestProcessor(Socket client, ServerController controller) {
		this.client = client;
		this.controller = controller;
		requestExecutor = new RequestExecutor(controller);
	}

	public void run() {
		Socket clientNotifier;
		try {
			clientNotifier = new Socket(client.getInetAddress(), 4444);
			controller.addListener(clientNotifier);

			ObjectOutputStream out = new ObjectOutputStream(
					client.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(
					client.getInputStream());

			while (true) {
				JAXBContext jaxbContext;

				jaxbContext = JAXBContext.newInstance(OperationResponse.class,
						OperationRequest.class, Phone.class, Subscriber.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();

				String xmlRequest = in.readUTF();
				OperationRequest request = (OperationRequest) jaxbUnmarshaller
						.unmarshal(new StringReader(xmlRequest));
				OperationResponse response = requestExecutor
						.executeRequest(request);

				StringWriter sw = new StringWriter();
				jaxbMarshaller.marshal(response, sw);
				String xmlString = sw.toString();

				out.writeUTF(xmlString);
				out.flush();
				out.reset();

			}
		} catch (JAXBException e) {
			e.printStackTrace();

		} catch (SocketException e) {
			System.out.println("Client disconnected");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
