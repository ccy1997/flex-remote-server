package fc.flexremote.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Platform;

public class Server implements Runnable {
  private String localIP;
  private ServerSocket server;
  private Controller controller;
  private boolean run;

  public Server(String localIP, ServerSocket server, Controller controller) {

    this.localIP = localIP;
    this.server = server;
    this.controller = controller;
    run = true;

  }

  @Override
  public void run() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        controller.setLocalIPLabel("Local IP of this PC: " + localIP);
      }
    });
    controller.generateLogViewText("Remote server ready for connection.");

    while (run) {
      try {
        Socket client = server.accept();
        String clientIP = client.getInetAddress().getHostAddress();
        ObjectInputStream fromClient = new ObjectInputStream(client.getInputStream());
        Thread clientReceiver = new Thread(new ClientReceiver(clientIP, fromClient, controller));
        clientReceiver.setDaemon(true);
        clientReceiver.start();

      } catch (IOException ioe) {
        ioe.printStackTrace();
      }

    }

    try {
      server.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
