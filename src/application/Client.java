package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;

import javax.swing.JOptionPane;

import view.Desktop;
import controller.ClientSideNotificationController;
import controller.ClientSideNotificationControllerImpl;
import controller.Controller;
import controller.NotificationsListener;
import controller.client.ClientController;

public class Client {
	public static void main(String args[]) throws NotBoundException {

		try {
			
			ServerSocket serverSocket = new ServerSocket(4444);
			Socket server = new Socket("localhost", 5555);
			Socket notificationSocket = serverSocket.accept();

			Controller controller = new ClientController(server);
			ClientSideNotificationController notificationController = new ClientSideNotificationControllerImpl();
			
		    final Desktop desktop = new Desktop(controller);
			notificationController.registerListener(desktop);

			Thread notificationListener = new Thread(new NotificationsListener(
					notificationSocket, notificationController));
			notificationListener.start();
		   
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try {
						desktop.pack();
						desktop.setSize(640, 480);
						desktop.setVisible(true);
					} catch (Exception ex) {

					}
				}
			});

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Some error during initialization, maybe you have not started server yet");
			e.printStackTrace();
		}
	}

}
