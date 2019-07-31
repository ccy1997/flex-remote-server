package fc.flexremote.server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Controller {
  private View view;
  private ArrayList<String> localIPs;
  private ServerSocket server;

  public void setView(View v) {
    view = v;
  }

  public void setLocalIPsLabel(String ip) {
    view.getLocalIPLabel().setText(ip);
  }

  public ArrayList<String> getLocalIPs() {
    return localIPs;
  }

  public ServerSocket getServerSocket() {
    return server;
  }

  public void generateLogViewText(String text) {
    view.getLogView().appendText(text + "\n");
  }

  public void clearLogViewText() {
    view.getLogView().clear();
  }

  public void exitOnNoNetworkConnection() {
	localIPs = getAppropriateLocalIPs(); 
	  
    if (localIPs.isEmpty()) { // No network connection
      Alert alert = new Alert(AlertType.NONE, "No network connection", ButtonType.OK);
      alert.showAndWait();
      System.exit(0);
    }
  }

  public void exitOnMultipleInstanceRunning() {
    try {
      server = new ServerSocket(9090);
    } catch (IOException e) {
      Alert alert = new Alert(AlertType.NONE, "Remote server is already running!", ButtonType.OK);
      alert.showAndWait();
      System.exit(0);
    }
  }

  private ArrayList<String> getAppropriateLocalIPs() {
    ArrayList<String> appropirateLocalIPs = new ArrayList<>();  
	  
    try {
      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

      while (interfaces.hasMoreElements()) {
        NetworkInterface networkInterface = interfaces.nextElement();
        // drop inactive
        if (!networkInterface.isUp())
          continue;

        // smth we can explore
        Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
        while (addresses.hasMoreElements()) {
          InetAddress addr = addresses.nextElement();

          if (!(addr instanceof Inet4Address))
            continue;

          if (!addr.isSiteLocalAddress())
            continue;

          if (addr.isLoopbackAddress())
            continue;

          appropirateLocalIPs.add(addr.getHostAddress());

        }
      }
    } catch (SocketException se) {
      se.printStackTrace();
    }

    return appropirateLocalIPs;
  }

}
