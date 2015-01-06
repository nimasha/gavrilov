package controller;

public interface ClientSideNotificationController extends NotificationController {
    void registerListener(NotificationListener listener);
}
