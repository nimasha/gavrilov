package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import remote.OperationRequest;
import remote.OperationResponse;
import remote.RequestExecutor;


public class NotificationsListener implements Runnable{
    private final Socket socketToListen;
    private final ClientSideNotificationController notificationController;
    private final RequestExecutor requestExecutor;

    public NotificationsListener(Socket socketToListen, ClientSideNotificationController notificationController) {
        this.socketToListen = socketToListen;
        this.notificationController = notificationController;
        requestExecutor = new RequestExecutor(this.notificationController);
    }

    public void run() {
        try {
            //ObjectOutputStream out = new ObjectOutputStream(socketToListen.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socketToListen.getInputStream());
            while (true)
            {
                OperationRequest request = (OperationRequest) in.readObject();
                //OperationResponse response =
                		requestExecutor.executeRequest(request);
                //out.writeObject(response);
                //out.flush();
                //out.reset();
            }
        } catch (ClassNotFoundException e) {
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
