package application;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

import model.DBModel;
import model.ModelInterface;
import controller.RequestProcessor;
import controller.server.ServerControllerImpl;
import db.DBHelper;

public class Server {
	private static ServerControllerImpl serverController;
	public static void main(String[] args) throws Exception {
		 Locale.setDefault(new Locale("en"));
		ServerSocket serv = new ServerSocket(5555);
		System.out.println("Server started!");
		serverController = new ServerControllerImpl();
		
		while (true) {
			Socket client = serv.accept();
			new Thread(new RequestProcessor(client, serverController)).start();
		}

	}
}


