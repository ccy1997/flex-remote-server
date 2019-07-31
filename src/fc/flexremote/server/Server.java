package fc.flexremote.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javafx.application.Platform;

public class Server implements Runnable {
  private ArrayList<String> localIPs;
  private ServerSocket server;
  private Controller controller;
  private boolean run;

  public Server(ArrayList<String> localIP, ServerSocket server, Controller controller) {
    this.localIPs = localIP;
    this.server = server;
    this.controller = controller;
    run = true;

  }

  @Override
  public void run() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
    	String localIPsLabelText = "Local IP(s) of this PC:\n";
    	
    	for (String localIP : localIPs) {
    		localIPsLabelText += localIP + "\n";
    	}
    	
        controller.setLocalIPsLabel(localIPsLabelText);
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
