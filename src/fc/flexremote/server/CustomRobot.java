package fc.flexremote.server;

import static java.awt.event.KeyEvent.*;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.io.IOException;

public class CustomRobot {

    private Robot robot;

    public CustomRobot(Robot robot) {
        this.robot = robot;
    }
    
    public void mousePress(int button) {
      robot.mousePress(button);
    }
    
    public void cursorMoveRelative(int dX, int dY) {
      Point cursorPos = MouseInfo.getPointerInfo().getLocation();
      robot.mouseMove(cursorPos.x + dX, cursorPos.y + dY);
    }
    
    public void cursorMoveAbsolute(float x, float y) {
      robot.mouseMove((int) x, (int) y);
    }

    public void simulateKeyActionPress(String str) {
        switch (str) {
        case "a":           press(VK_A); break;
        case "b":           press(VK_B); break;
        case "c":           press(VK_C); break;
        case "d":           press(VK_D); break;
        case "e":           press(VK_E); break;
        case "f":           press(VK_F); break;
        case "g":           press(VK_G); break;
        case "h":           press(VK_H); break;
        case "i":           press(VK_I); break;
        case "j":           press(VK_J); break;
        case "k":           press(VK_K); break;
        case "l":           press(VK_L); break;
        case "m":           press(VK_M); break;
        case "n":           press(VK_N); break;
        case "o":           press(VK_O); break;
        case "p":           press(VK_P); break;
        case "q":           press(VK_Q); break;
        case "r":           press(VK_R); break;
        case "s":           press(VK_S); break;
        case "t":           press(VK_T); break;
        case "u":           press(VK_U); break;
        case "v":           press(VK_V); break;
        case "w":           press(VK_W); break;
        case "x":           press(VK_X); break;
        case "y":           press(VK_Y); break;
        case "z":           press(VK_Z); break;
        case "A":           press(VK_SHIFT, VK_A); break;
        case "B":           press(VK_SHIFT, VK_B); break;
        case "C":           press(VK_SHIFT, VK_C); break;
        case "D":           press(VK_SHIFT, VK_D); break;
        case "E":           press(VK_SHIFT, VK_E); break;
        case "F":           press(VK_SHIFT, VK_F); break;
        case "G":           press(VK_SHIFT, VK_G); break;
        case "H":           press(VK_SHIFT, VK_H); break;
        case "I":           press(VK_SHIFT, VK_I); break;
        case "J":           press(VK_SHIFT, VK_J); break;
        case "K":           press(VK_SHIFT, VK_K); break;
        case "L":           press(VK_SHIFT, VK_L); break;
        case "M":           press(VK_SHIFT, VK_M); break;
        case "N":           press(VK_SHIFT, VK_N); break;
        case "O":           press(VK_SHIFT, VK_O); break;
        case "P":           press(VK_SHIFT, VK_P); break;
        case "Q":           press(VK_SHIFT, VK_Q); break;
        case "R":           press(VK_SHIFT, VK_R); break;
        case "S":           press(VK_SHIFT, VK_S); break;
        case "T":           press(VK_SHIFT, VK_T); break;
        case "U":           press(VK_SHIFT, VK_U); break;
        case "V":           press(VK_SHIFT, VK_V); break;
        case "W":           press(VK_SHIFT, VK_W); break;
        case "X":           press(VK_SHIFT, VK_X); break;
        case "Y":           press(VK_SHIFT, VK_Y); break;
        case "Z":           press(VK_SHIFT, VK_Z); break;
        case "0":           press(VK_0); break;
        case "1":           press(VK_1); break;
        case "2":           press(VK_2); break;
        case "3":           press(VK_3); break;
        case "4":           press(VK_4); break;
        case "5":           press(VK_5); break;
        case "6":           press(VK_6); break;
        case "7":           press(VK_7); break;
        case "8":           press(VK_8); break;
        case "9":           press(VK_9); break;
        case "Space":       press(VK_SPACE); break;
        case "!":           press(VK_SHIFT, VK_1); break;
        case "\"":          press(VK_SHIFT, VK_QUOTE); break;
        case "#":           press(VK_SHIFT, VK_3); break;
        case "$":           press(VK_SHIFT, VK_4); break;
        case "%":           press(VK_SHIFT, VK_5); break;
        case "&":           press(VK_SHIFT, VK_7); break;
        case "'":           press(VK_QUOTE); break;
        case "(":           press(VK_SHIFT, VK_9); break;
        case ")":           press(VK_SHIFT, VK_0); break;
        case "*":           press(VK_SHIFT, VK_8); break;
        case "+":           press(VK_SHIFT, VK_EQUALS); break;
        case ",":           press(VK_COMMA); break;
        case "-":           press(VK_MINUS); break;
        case ".":           press(VK_PERIOD); break;
        case "/":           press(VK_SLASH); break;
        case ":":           press(VK_SHIFT, VK_SEMICOLON); break;
        case ";":           press(VK_SEMICOLON); break;
        case "<":           press(VK_SHIFT, VK_COMMA); break;
        case "=":           press(VK_EQUALS); break;
        case ">":           press(VK_SHIFT, VK_PERIOD); break;
        case "?":           press(VK_SHIFT, VK_SLASH); break;
        case "@":           press(VK_SHIFT, VK_2); break;
        case "[":           press(VK_OPEN_BRACKET); break;
        case "\\":          press(VK_BACK_SLASH); break;
        case "]":           press(VK_CLOSE_BRACKET); break;
        case "^":           press(VK_SHIFT, VK_6); break;
        case "_":           press(VK_SHIFT, VK_MINUS); break;
        case "`":           press(VK_BACK_QUOTE); break;
        case "{":           press(VK_SHIFT, VK_OPEN_BRACKET); break;
        case "|":           press(VK_SHIFT, VK_BACK_SLASH); break;
        case "}":           press(VK_SHIFT, VK_CLOSE_BRACKET); break;
        case "~":           press(VK_SHIFT, VK_BACK_QUOTE); break;
        case "\u2190":      press(VK_LEFT); break;
        case "\u2191":      press(VK_UP); break;
        case "\u2192":      press(VK_RIGHT); break;
        case "\u2193":      press(VK_DOWN); break;
        case "Esc":         press(VK_ESCAPE); break;
        case "Tab":         press(VK_TAB); break;
        case "CapsLock":    press(VK_CAPS_LOCK); break;
        case "Enter":       press(VK_ENTER); break;
        case "\u232B":      press(VK_BACK_SPACE); break;
        case "Shift":       press(VK_SHIFT); break;
        case "Ctrl":        press(VK_CONTROL); break;
        case "Alt":         press(VK_ALT); break;
        case "Delete":      press(VK_DELETE);
        case "Home":        press(VK_HOME); break;
        case "End":         press(VK_END); break;
        case "PrintScreen": press(VK_PRINTSCREEN); break;
        case "PageUp":      press(VK_PAGE_UP); break;
        case "PageDown":    press(VK_PAGE_DOWN); break;
        case "F1":          press(VK_F1); break;
        case "F2":          press(VK_F2); break;
        case "F3":          press(VK_F3); break;
        case "F4":          press(VK_F4); break;
        case "F5":          press(VK_F5); break;
        case "F6":          press(VK_F6); break;
        case "F7":          press(VK_F7); break;
        case "F8":          press(VK_F8); break;
        case "F9":          press(VK_F9); break;
        case "F10":         press(VK_F10); break;
        case "F11":         press(VK_F11); break;
        case "F12":         press(VK_F12); break;
        case "M1":          robot.mousePress(BUTTON1_DOWN_MASK); break;
        case "M2":          robot.mousePress(BUTTON2_DOWN_MASK); break;
        case "M3":          robot.mousePress(BUTTON3_DOWN_MASK); break;
        case "Copy":        press(VK_CONTROL, VK_C); break;
        case "Paste":       press(VK_CONTROL, VK_V); break;
        case "Undo":        press(VK_CONTROL, VK_Z); break;
        case "ShowDesktop": press(VK_WINDOWS, VK_D); break;
        case "Shutdown":    shutDownPc(); break;
        case "Restart":     restartPc(); break;
        default:            break;
        }
    }
    
    public void simulateKeyActionRelease(String str) {
        switch (str) {
        case "a":           release(VK_A); break;
        case "b":           release(VK_B); break;
        case "c":           release(VK_C); break;
        case "d":           release(VK_D); break;
        case "e":           release(VK_E); break;
        case "f":           release(VK_F); break;
        case "g":           release(VK_G); break;
        case "h":           release(VK_H); break;
        case "i":           release(VK_I); break;
        case "j":           release(VK_J); break;
        case "k":           release(VK_K); break;
        case "l":           release(VK_L); break;
        case "m":           release(VK_M); break;
        case "n":           release(VK_N); break;
        case "o":           release(VK_O); break;
        case "p":           release(VK_P); break;
        case "q":           release(VK_Q); break;
        case "r":           release(VK_R); break;
        case "s":           release(VK_S); break;
        case "t":           release(VK_T); break;
        case "u":           release(VK_U); break;
        case "v":           release(VK_V); break;
        case "w":           release(VK_W); break;
        case "x":           release(VK_X); break;
        case "y":           release(VK_Y); break;
        case "z":           release(VK_Z); break;
        case "A":           release(VK_SHIFT, VK_A); break;
        case "B":           release(VK_SHIFT, VK_B); break;
        case "C":           release(VK_SHIFT, VK_C); break;
        case "D":           release(VK_SHIFT, VK_D); break;
        case "E":           release(VK_SHIFT, VK_E); break;
        case "F":           release(VK_SHIFT, VK_F); break;
        case "G":           release(VK_SHIFT, VK_G); break;
        case "H":           release(VK_SHIFT, VK_H); break;
        case "I":           release(VK_SHIFT, VK_I); break;
        case "J":           release(VK_SHIFT, VK_J); break;
        case "K":           release(VK_SHIFT, VK_K); break;
        case "L":           release(VK_SHIFT, VK_L); break;
        case "M":           release(VK_SHIFT, VK_M); break;
        case "N":           release(VK_SHIFT, VK_N); break;
        case "O":           release(VK_SHIFT, VK_O); break;
        case "P":           release(VK_SHIFT, VK_P); break;
        case "Q":           release(VK_SHIFT, VK_Q); break;
        case "R":           release(VK_SHIFT, VK_R); break;
        case "S":           release(VK_SHIFT, VK_S); break;
        case "T":           release(VK_SHIFT, VK_T); break;
        case "U":           release(VK_SHIFT, VK_U); break;
        case "V":           release(VK_SHIFT, VK_V); break;
        case "W":           release(VK_SHIFT, VK_W); break;
        case "X":           release(VK_SHIFT, VK_X); break;
        case "Y":           release(VK_SHIFT, VK_Y); break;
        case "Z":           release(VK_SHIFT, VK_Z); break;
        case "0":           release(VK_0); break;
        case "1":           release(VK_1); break;
        case "2":           release(VK_2); break;
        case "3":           release(VK_3); break;
        case "4":           release(VK_4); break;
        case "5":           release(VK_5); break;
        case "6":           release(VK_6); break;
        case "7":           release(VK_7); break;
        case "8":           release(VK_8); break;
        case "9":           release(VK_9); break;
        case "Space":       release(VK_SPACE); break;
        case "!":           release(VK_SHIFT, VK_1); break;
        case "\"":          release(VK_SHIFT, VK_QUOTE); break;
        case "#":           release(VK_SHIFT, VK_3); break;
        case "$":           release(VK_SHIFT, VK_4); break;
        case "%":           release(VK_SHIFT, VK_5); break;
        case "&":           release(VK_SHIFT, VK_7); break;
        case "'":           release(VK_QUOTE); break;
        case "(":           release(VK_SHIFT, VK_9); break;
        case ")":           release(VK_SHIFT, VK_0); break;
        case "*":           release(VK_SHIFT, VK_8); break;
        case "+":           release(VK_SHIFT, VK_EQUALS); break;
        case ",":           release(VK_COMMA); break;
        case "-":           release(VK_MINUS); break;
        case ".":           release(VK_PERIOD); break;
        case "/":           release(VK_SLASH); break;
        case ":":           release(VK_SHIFT, VK_SEMICOLON); break;
        case ";":           release(VK_SEMICOLON); break;
        case "<":           release(VK_SHIFT, VK_COMMA); break;
        case "=":           release(VK_EQUALS); break;
        case ">":           release(VK_SHIFT, VK_PERIOD); break;
        case "?":           release(VK_SHIFT, VK_SLASH); break;
        case "@":           release(VK_SHIFT, VK_2); break;
        case "[":           release(VK_OPEN_BRACKET); break;
        case "\\":          release(VK_BACK_SLASH); break;
        case "]":           release(VK_CLOSE_BRACKET); break;
        case "^":           release(VK_SHIFT, VK_6); break;
        case "_":           release(VK_SHIFT, VK_MINUS); break;
        case "`":           release(VK_BACK_QUOTE); break;
        case "{":           release(VK_SHIFT, VK_OPEN_BRACKET); break;
        case "|":           release(VK_SHIFT, VK_BACK_SLASH); break;
        case "}":           release(VK_SHIFT, VK_CLOSE_BRACKET); break;
        case "~":           release(VK_SHIFT, VK_BACK_QUOTE); break;
        case "\u2190":      release(VK_LEFT); break;
        case "\u2191":      release(VK_UP); break;
        case "\u2192":      release(VK_RIGHT); break;
        case "\u2193":      release(VK_DOWN); break;
        case "Esc":         release(VK_ESCAPE); break;
        case "Tab":         release(VK_TAB); break;
        case "CapsLock":    release(VK_CAPS_LOCK); break;
        case "Enter":       release(VK_ENTER); break;
        case "\u232B":      release(VK_BACK_SPACE); break;
        case "Shift":       release(VK_SHIFT); break;
        case "Ctrl":        release(VK_CONTROL); break;
        case "Alt":         release(VK_ALT); break;
        case "Delete":      release(VK_DELETE); break;
        case "Home":        release(VK_HOME); break;
        case "End":         release(VK_END); break;
        case "PrintScreen": release(VK_PRINTSCREEN); break;
        case "PageUp":      release(VK_PAGE_UP); break;
        case "PageDown":    release(VK_PAGE_DOWN); break;
        case "F1":          release(VK_F1); break;
        case "F2":          release(VK_F2); break;
        case "F3":          release(VK_F3); break;
        case "F4":          release(VK_F4); break;
        case "F5":          release(VK_F5); break;
        case "F6":          release(VK_F6); break;
        case "F7":          release(VK_F7); break;
        case "F8":          release(VK_F8); break;
        case "F9":          release(VK_F9); break;
        case "F10":         release(VK_F10); break;
        case "F11":         release(VK_F11); break;
        case "F12":         release(VK_F12); break;
        case "M1":          robot.mouseRelease(BUTTON1_DOWN_MASK); break;
        case "M2":          robot.mouseRelease(BUTTON2_DOWN_MASK); break;
        case "M3":          robot.mouseRelease(BUTTON3_DOWN_MASK); break;
        case "Copy":        release(VK_CONTROL, VK_C); break;
        case "Paste":       release(VK_CONTROL, VK_V); break;
        case "Undo":        release(VK_CONTROL, VK_Z); break;
        case "ShowDesktop": release(VK_WINDOWS, VK_D); break;
        default:            break;
        }
    }

    private void press(int... keyCodes) {
        simulatePress(keyCodes, 0, keyCodes.length);
    }

    private void simulatePress(int[] keyCodes, int offset, int length) {
        if (length == 0) {
            return;
        }
        
        for (int keyCode : keyCodes) {
            try {
                robot.keyPress(keyCode);
            } catch (IllegalArgumentException iae) {
                // Proceed
            }
            
        }
    }
    
    private void release(int... keyCodes) {
        simulateRelease(keyCodes, 0, keyCodes.length);
    }

    private void simulateRelease(int[] keyCodes, int offset, int length) {
        if (length == 0) {
            return;
        }
      
        for (int keyCode : keyCodes) {
            try {
                robot.keyRelease(keyCode);
            } catch (IllegalArgumentException iae) {
                // Proceed
            }
        }
    }
    
    private void shutDownPc() {
      String operatingSystem = System.getProperty("os.name");

      try {
        if (operatingSystem.contains("nux")|| operatingSystem.startsWith("mac")) {
          Runtime.getRuntime().exec("shutdown -h now");
          System.exit(0);
        }
        else if (operatingSystem.contains("Windows")) {
          Runtime.getRuntime().exec("shutdown.exe -s -t 0");
          System.exit(0);
        }
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
      
    }
    
    private void restartPc() {
      String operatingSystem = System.getProperty("os.name");

      try {
        if (operatingSystem.contains("nux")|| operatingSystem.startsWith("mac")) {
          Runtime.getRuntime().exec("shutdown -r now");
          System.exit(0);
        }
        else if (operatingSystem.contains("Windows")) {
          Runtime.getRuntime().exec("shutdown.exe -r -t 0");
          System.exit(0);
        }
      } catch (IOException ioe) {
        // Just do nothing
      }
    }

}
