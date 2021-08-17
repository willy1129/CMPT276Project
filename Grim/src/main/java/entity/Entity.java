package entity;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Abstract class for entities in the game Position represents position of the entity.
 *
 * @author Wai Lee
 * @version 0.1
 * @since 2021-03-01
 */


public abstract class Entity {
    protected Point position = new Point();
    protected BufferedImage img;
    protected Rectangle bound;
    protected int defaultMove = 3;

    /**
     * Draws the entity to the display
     * @param g Graphics object
     */
    public void render(Graphics g) {
        g.drawImage(img,position.x,position.y,null);
    }

    public Point getPosition(){
        return position;
    }
    public Rectangle getBound(){
        return bound;
    }
}
