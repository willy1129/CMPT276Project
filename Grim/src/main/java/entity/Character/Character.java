package entity.character;

import command.Command;
import controller.Controller;
import entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Parent class for all moving objects
 *
 * @author Wai Lee
 * @version 0.2
 * @since 2021-03-01
 */
public abstract class Character extends Entity {
    protected final int widBOUND = 820, heiBOUND = 550, DEFAULT_WIDTH = 15, DEFAULT_HEIGHT = 15;
    protected int lastAni = 1, renderTiming = 0;
    protected Command currentAnimation;
    protected BufferedImage up, down, left, right, upAni, downAni, leftAni1, rightAni1, leftAni2, rightAni2, img, img2, img3;
    private Point target;
    private boolean moving;
    protected Controller con;

    public Character(Point pt) {
        position.setLocation(pt.x, pt.y);
        bound = new Rectangle(pt.x, position.y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public boolean moveLeft() {
        con = Controller.getInstance();
        moving = false;
        target = new Point(position.x - defaultMove, position.y);
        if (position.getX() > 1 && !(con.checkWall(target))) {
            position.setLocation(target);
            moving = true;
        }
        return moving;
    }

    public boolean moveRight() {
        con = Controller.getInstance();
        moving = false;
        target = new Point(position.x + defaultMove, position.y);
        if (position.getX() < widBOUND&!con.checkWall(target)) {
            position.setLocation(target);
        }
        return moving;
    }

    public boolean moveUp() {
        con = Controller.getInstance();
        moving = false;
        target = new Point(position.x, position.y - defaultMove);
        if (position.getY() > 1 && !con.checkWall(target)) {
            position.setLocation(target);
        }
        return moving;
    }

    public boolean moveDown() {
        con = Controller.getInstance();
        moving = false;
        target = new Point(position.x, position.y + defaultMove);
        if (position.getY() < heiBOUND && !con.checkWall(target)) {
            position.setLocation(target);
        }
        return moving;
    }

    public Rectangle getBound() {
        bound = new Rectangle(position.x, position.y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        return bound;
    }

    public abstract void render(Graphics g);

}



