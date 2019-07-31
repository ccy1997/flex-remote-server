package fc.flexremote.server;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    Controller controller = new Controller();
    controller.exitOnNoNetworkConnection();
    controller.exitOnMultipleInstanceRunning();
    View view = new View(primaryStage, controller);
    view.getStage().show();
    controller.setView(view);
    Thread serverThread =
        new Thread(new Server(controller.getLocalIPs(), controller.getServerSocket(), controller));
    serverThread.setDaemon(true);
    serverThread.start();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
