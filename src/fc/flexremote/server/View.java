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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class View {
  private View view;
  private Stage stage;
  private Scene scene;
  private VBox vbox;
  private Label localIP;
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
      initializeVBox();
      initializeLocalIPLabel();
      initializeInstructionLabel();
      initializeLogView();
      initializeClearTextButton();
      addChildrenToRoot(localIP);
      addChildrenToRoot(instruction);
      addChildrenToRoot(logView);
      addChildrenToRoot(clearLogView);
      scene = new Scene(getRoot());
      stage.setTitle("Remote PC server");
      stage.setResizable(false);
      stage.setScene(scene);
      setupTrayIcon(stage);
      view = this;
    }
  }

  public Stage getStage() {
    return stage;
  }

  public Pane getRoot() {
    return vbox;
  }

  public Label getLocalIPLabel() {
    return localIP;
  }

  public TextArea getLogView() {
    return logView;
  }
  
  public Button getClearLogViewButton() {
    return clearLogView;
  }

  private void initializeVBox() {
    vbox = new VBox();
    vbox.setAlignment(Pos.TOP_CENTER);
    vbox.setPadding(new Insets(10));
    vbox.setSpacing(8);
  }

  private void addChildrenToRoot(Node child) {
    this.getRoot().getChildren().add(child);
  }

  private void initializeLocalIPLabel() {
    localIP = new Label();
    localIP.setText("Local IP of this PC: -");
  }

  private void initializeInstructionLabel() {
    instruction = new Label();
    instruction.setText("1. Enter the IP shown above at the setting page of the remote app\n"
        + "2. Create a remote from the app and start the connection");
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
