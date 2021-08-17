package entity.character;

import command.Command;
import gfx.Assets;

import java.awt.*;

/**
 * This class represents the main character(grim reaper) controlled by the user
 *
 * @author Wai Lee
 * @version 0.2
 * @since 2021-03-01
 */

public class MainCharacter extends Character {
    // x and y are the initial position of the character
    private Command current = Command.DOWN;

    public MainCharacter(Point pt) {
        super(pt);
        up = Assets.grimBack1;
        down = Assets.grimFront1;
        left = Assets.grimLeft1;
        right = Assets.grimRight1;
        upAni = Assets.grimBack2;
        leftAni1 = Assets.grimLeft2;
        rightAni1 = Assets.grimRight2;
    }

    /**
     * This a a method to receive user input and update
     * position of the character
     *
     * @param command this is the user input
     * @return position this is the updated position
     */
    public Point move(Command command) {
        current = command;
        switch (command) {
            case UP -> {
                moveUp();
                currentAnimation = Command.UP;
            }
            case DOWN -> {
                moveDown();
                currentAnimation = Command.DOWN;
            }
            case LEFT -> {
                moveLeft();
                currentAnimation = Command.LEFT;
            }
            case RIGHT -> {
                moveRight();
                currentAnimation = Command.RIGHT;
            }
            default -> {
                position = this.getPosition();
                currentAnimation = Command.NONE;
            }
        }
        return position;
    }

    @Override
    public void render(Graphics g) {
        switch (current) {
            case UP:
                img = Assets.grimBack1;
                img2 = Assets.grimBack2;
                break;
            case DOWN:
                img = Assets.grimFront1;
                img2 = Assets.grimFront2;
                break;
            case LEFT:
                img = Assets.grimLeft1;
                img2 = Assets.grimLeft2;
                break;
            case RIGHT:
                img = Assets.grimRight1;
                img2 = Assets.grimRight2;
                break;
            default:
        }
        if (renderTiming < 30) {
            g.drawImage(img, position.x, position.y, null);
            renderTiming++;
        } else if(renderTiming < 60){
            g.drawImage(img2, position.x, position.y, null);
            renderTiming++;
        }
        else{
            g.drawImage(img, position.x, position.y, null);
            renderTiming = 0;
        }
    }
}
