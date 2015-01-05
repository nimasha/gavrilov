package application;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import controller.RequestProcessor;
import controller.server.Saver;
import controller.server.ServerControllerImpl;

public class Server {
	private static ServerControllerImpl serverController;
	private static ScheduledExecutorService scheduler = Executors
			.newScheduledThreadPool(1);

	public static void main(String[] args) throws Exception {

		ServerSocket serv = new ServerSocket(5555);
		System.out.println("Server started!");
		serverController = new ServerControllerImpl();
		File inputFile = new File("base.xml");
		if (inputFile.exists()) {
			serverController.setModel(inputFile);
		}
		scheduler.scheduleWithFixedDelay(new Saver(serverController), 60, 60,
				TimeUnit.SECONDS);

		while (true) {
			Socket client = serv.accept();
			new Thread(new RequestProcessor(client, serverController)).start();
		}

	}
}


