package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

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
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try {
			Socket clientNotifier = new Socket(client.getInetAddress(), 4444);
			controller.addListener(clientNotifier);

			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());

			while (true) {
				OperationRequest request = (OperationRequest) in.readObject();
				OperationResponse response = requestExecutor
						.executeRequest(request);
				out.writeObject(response);
				out.flush();
				out.reset();
			}
		} catch (SocketException e) {
			System.out.println("Client disconnected");
			try {
				out.close();
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
