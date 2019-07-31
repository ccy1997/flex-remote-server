package fc.flexremote.server;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class View {
  private View view;
  private Stage stage;
  private Scene scene;
  private HBox hBox;
  private VBox vBoxLeft;
  private VBox vBoxRight;
  private Label localIPs;
  private Label instruction;
  private TextArea logView;
  private Button clearLogView;
  private TrayIcon trayIcon;
  private PopupMenu popupMenu;
  private MenuItem showItem;
  private MenuItem closeItem;
  private Controller controller;
  boolean firstTimeMinimize;

  public View(Stage stage, Controller controller) {
    if (view == null) {
      this.controller = controller;
      this.stage = stage;
      stage.getIcons().add(new javafx.scene.image.Image("/tray_icon.png"));
      firstTimeMinimize = true;
      initializeHBox();
      initializeVBoxLeft();
      initializeVBoxRight();
      initializeLocalIPLabel();
      initializeInstructionLabel();
      initializeLogView();
      initializeClearTextButton();
      hBox.getChildren().add(vBoxLeft);
      hBox.getChildren().add(vBoxRight);
      vBoxLeft.getChildren().add(localIPs);
      vBoxRight.getChildren().add(instruction);
      vBoxRight.getChildren().add(logView);
      vBoxRight.getChildren().add(clearLogView);
      scene = new Scene(hBox);
      stage.setTitle("FlexRemote server");
      stage.setResizable(false);
      stage.setScene(scene);
      setupTrayIcon(stage);
      view = this;
    }
  }

  public Stage getStage() {
    return stage;
  }

  public Label getLocalIPLabel() {
    return localIPs;
  }

  public TextArea getLogView() {
    return logView;
  }
  
  public Button getClearLogViewButton() {
    return clearLogView;
  }
  
  private void initializeHBox() {
	  hBox = new HBox();
	  hBox.setPadding(new Insets(10));
	  hBox.setSpacing(8);
  }
  
  private void initializeVBoxLeft() {
	vBoxLeft = new VBox();
	vBoxLeft.setAlignment(Pos.TOP_CENTER);
	vBoxLeft.setPadding(new Insets(10));
	vBoxLeft.setSpacing(8);
  }

  private void initializeVBoxRight() {
    vBoxRight = new VBox();
    vBoxRight.setAlignment(Pos.TOP_CENTER);
    vBoxRight.setPadding(new Insets(10));
    vBoxRight.setSpacing(8);
  }

  private void initializeLocalIPLabel() {
    localIPs = new Label();
    localIPs.setText("Local IP of this PC:");
    localIPs.setPrefWidth(150);
  }

  private void initializeInstructionLabel() {
    instruction = new Label();
    instruction.setText("Instruction:\n" +
    					"1. Enter the IP shown on the left to the setting page of the app.\n\n" +
    				    "2. If there are multiple IPs being shown, choose the one which associate to\n" +
    				    "the Local area network (LAN) your mobile device is connected to.\n\n" +
    				    "Example: Your PC is connected to two local networks:\n" + 
    				    "(1) a router with IP 192.168.0.2 (wifi) + 192.168.0.3 (ethernet)\n" +
    				    "(2) a mobile tethering hotspot with IP 192.168.34.86.\n" + 
    				    "If your mobile device is connected to (2), you should enter 192.168.34.86 in your app.\n\n" +
    					"3. Create a remote control from the app and start the connection.\n\n" + 
    				    "Tips: Connect PC and mobile device through mobile tethering hotspot to avoid lag spike when using touchpad.");
  }

  private void initializeLogView() {
    logView = new TextArea();
    logView.setPrefSize(500, 500);
    logView.setEditable(false);
  }
  
  private void initializeClearTextButton() {
    clearLogView = new Button();
    clearLogView.setText("Clear log");
    clearLogView.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        controller.clearLogViewText();
      }
      
    });
  }

  private void setupTrayIcon(Stage stage) {
    if (SystemTray.isSupported()) {

      stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent t) {
          Platform.runLater(new Runnable() {

            @Override
            public void run() {
              stage.hide();
              showProgramIsMinimizedMsg();
            }

          });
        }
      });

      ActionListener closeListener = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
          System.exit(0);
        }
      };

      ActionListener showListener = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              stage.show();
            }
          });
        }
      };

      java.awt.Image trayIconImage = readImageFromPath("/tray_icon.png");
      createPopUpMenuForTray(showListener, closeListener);
      setTrayIcon(trayIconImage, popupMenu, showListener);
      addTrayIconToTray();
      Platform.setImplicitExit(false);
    }
  }

  private java.awt.Image readImageFromPath(String path) {
    java.awt.Image image = null;
    
    try {
      image = ImageIO.read(getClass().getResource(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return image;
  }

  private void showProgramIsMinimizedMsg() {
    if (firstTimeMinimize) {
      trayIcon.displayMessage("FlexRemote Server", "Server running in background",
          TrayIcon.MessageType.NONE);
      firstTimeMinimize = false;
    }
  }

  private void createPopUpMenuForTray(ActionListener showListener, ActionListener closeListener) {
    popupMenu = new PopupMenu();

    showItem = new MenuItem("Open");
    showItem.addActionListener(showListener);
    popupMenu.add(showItem);

    closeItem = new MenuItem("Exit");
    closeItem.addActionListener(closeListener);
    popupMenu.add(closeItem);
  }

  private void setTrayIcon(java.awt.Image tryaIconImage, PopupMenu popupMenu,
      ActionListener iconListener) {
    int trayIconWidth = new TrayIcon(tryaIconImage).getSize().width;
    trayIcon = new TrayIcon(
        tryaIconImage.getScaledInstance(trayIconWidth, -1, java.awt.Image.SCALE_SMOOTH),
        "FlexRemote Server", popupMenu);
    trayIcon.addActionListener(iconListener);
  }
  
  private void addTrayIconToTray() {
    SystemTray tray = SystemTray.getSystemTray();
    
    try {
      tray.add(trayIcon);
    } catch (AWTException e) {
      e.printStackTrace();
    }
  }

}
