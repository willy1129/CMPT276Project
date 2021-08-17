package userInput;

import command.Command;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This Class uses the KeyListener to listen to keyboard inputs and return the key pressed
 *
 * @author Wai Lee
 * @version 1.0
 * @since 03-20-2021
 */
public class UserInput implements KeyListener {
    private static UserInput userInput;
    private Command move = Command.NONE;

    public static UserInput getInstance() {
        if (userInput == null) {
            userInput = new UserInput();
        }
        return userInput;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        move = checkInput(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        move = Command.NONE;
    }

    public void setMoveToNone() {
        move = Command.NONE;
    }

    @Override
    public void keyTyped(KeyEvent e){
    }

    public Command getMove(){
        return move;
    }

    private Command checkInput(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            move = Command.UP;
        }
        else if(key == KeyEvent.VK_S) {
            move = Command.DOWN;
        }
        else if(key == KeyEvent.VK_A) {
            move = Command.LEFT;
        }
        else if(key == KeyEvent.VK_D) {
            move = Command.RIGHT;
        }
        else if(key == KeyEvent.VK_ENTER) {
            move = Command.ENTER;
        }
        else {
            move = Command.OTHER;
        }
        return move;
    }
}
