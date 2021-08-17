package entity.item;

import entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class represent static entities (souls) in the game
 *
 * @author Wai Lee
 * @version 0.1
 * @since 2021-03-06
 */
public abstract class Item extends Entity {
    //Default size of Items
    private final int DEFAULT_WIDTH = 18, DEFAULT_HEIGHT = 18;

    //The item exist if not collected and disappear if collected
    protected boolean collected;

    //How much score the item worth
    protected int value;

    protected BufferedImage img;

    public Item(Point pt) {
        position.setLocation(pt.x, pt.y);
        bound = new Rectangle(pt.x, pt.y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void setPosition(Point pt) {
        position = pt;
        bound = new Rectangle(pt.x, pt.y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void setCollected() {
        collected = true;
    }

    public boolean getCollected() {
        return collected;
    }

    public int getValue() {
        return value;
    }

    public void render(Graphics g) {
        if (!collected)
            g.drawImage(img, position.x, position.y, null);
    }
}
