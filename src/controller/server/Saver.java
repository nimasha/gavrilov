package controller.server;

import java.io.File;
import java.io.IOException;

public class Saver implements Runnable {
    private final ServerController controller;

    public Saver(ServerController controller) {
        this.controller = controller;
    }

    public void run() {
        try {
            controller.saveModel(new File("base.cdb"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
