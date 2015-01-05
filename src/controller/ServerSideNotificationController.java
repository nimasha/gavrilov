package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Phone;
import model.Subscriber;
import remote.OperationRequest;
import remote.OperationResponse;

public class ServerSideNotificationController implements NotificationController {

	private List<Socket> listeners;
	private Map<Socket, ObjectOutputStream> outputStreams = new HashMap<>();

	public ServerSideNotificationController() {
		listeners = new CopyOnWriteArrayList<Socket>();
	}

	public void subscriberChanged(Subscriber subscriber) {
		notifyListeners(new OperationRequest("subscriberChanged", subscriber));
	}

	public void subscriberAdded(Subscriber subscriber) {
		notifyListeners(new OperationRequest("subscriberAdded", subscriber));
	}

	public void subscriberRemoved(Subscriber subscriber) {
		notifyListeners(new OperationRequest("subscriberRemoved", subscriber));
	}

	public void phoneChanged(Phone phone) {
		notifyListeners(new OperationRequest("phoneChanged", phone));
	}

	public void phoneAdded(Phone phone) {
		notifyListeners(new OperationRequest("phoneAdded", phone));
	}

	public void phoneRemoved(Phone phone) {
		notifyListeners(new OperationRequest("phoneRemoved", phone));
	}

	private synchronized void notifyListeners(OperationRequest request) {
		List<Socket> itemsToDelete = null;
		for (Socket soc : listeners) {
			try {

				ObjectOutputStream out = outputStreams.get(soc);
				out.writeObject(request);
				out.flush();
				out.reset();
			} catch (SocketException e) {
				System.out.println("Connection closed");
				if (itemsToDelete == null) {
					itemsToDelete = new ArrayList<Socket>(Arrays.asList(soc));
				} else {
					itemsToDelete.add(soc);
				}
			} catch (IOException e) {
				System.out.println("Some error in notification"
						+ e.getStackTrace());
			}
		}

		if (itemsToDelete != null) {
			listeners.removeAll(itemsToDelete);
			for (Socket soc : itemsToDelete) {
				try {
					outputStreams.get(soc).close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				outputStreams.remove(soc);
				
			}
		}
	}

	public void addListener(Socket soc) {
		listeners.add(soc);
		try {
			outputStreams.put(soc,
					new ObjectOutputStream(soc.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
