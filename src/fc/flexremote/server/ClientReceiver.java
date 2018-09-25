package fc.flexremote.server;

import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.io.IOException;
import java.io.ObjectInputStream;
import fc.flexremote.common.Message;

public class ClientReceiver implements Runnable {
  private String ip;
  private String device;
  private ObjectInputStream fromClient;
  private Controller controller;
  private CustomRobot customRobot;
  private int dX;
  private int dY;
  private float x;
  private float y;
  int screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
      .getDisplayMode().getWidth();
  int screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
      .getDisplayMode().getHeight();
  private boolean run;


  public ClientReceiver(String ip, ObjectInputStream fromClient, Controller controller) {
    this.ip = ip;
    this.fromClient = fromClient;
    this.controller = controller;

    try {
      Robot robot = new Robot();
      customRobot = new CustomRobot(robot);
    } catch (AWTException e) {
      // Do nothing
    }

    run = true;
  }

  @Override
  public void run() {
    while (run) {
      try {
        Message message = (Message) fromClient.readObject();

        switch (message.getMessageType()) {
          case (Message.MESSAGE_RELATIVE_TOUCHPAD_EVENT):
            dX = message.getdX();
            dY = message.getdY();
            customRobot.cursorMoveRelative(dX, dY);
            break;

          case (Message.MESSAGE_ABSOLUTE_TOUCHPAD_EVENT):
            x = message.getXNormalized() * screenWidth;
            y = message.getYNormalized() * screenHeight;
            customRobot.cursorMoveAbsolute(x, y);
            break;

          case (Message.MESSAGE_KEY_EVENT):
            String keyAction = message.getKeyAction();

            if (message.getTouchEvent() == Message.TOUCH_ACTION_KEY_DOWN)
              customRobot.simulateKeyActionPress(keyAction);
            else if (message.getTouchEvent() == Message.TOUCH_ACTION_KEY_UP)
              customRobot.simulateKeyActionRelease(keyAction);

            break;

          case (Message.MESSAGE_DEVICE):
            device = message.getDevice();
            controller.generateLogViewText(device + " (" + ip + ") connected.");
            break;

          case (Message.MESSAGE_DISCONNECT):
            run = false;
            break;

          default:
            break;
        }

      } catch (IOException ioe) {
        run = false;
      } catch (ClassNotFoundException cnfe) {
        run = false;
      }

    }

    try {
      fromClient.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    controller.generateLogViewText(device + " (" + ip + ") disconnected.");
  }

}
