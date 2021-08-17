package entity.item;

import gfx.Assets;

import java.awt.*;

/**
 *This class represents punishments (red souls) that if collided with, will decrease the player's score
 *
 * @author Wai Lee
 * @version 0.1
 * @since 2021-03-06
 */
public class Punishment extends Item {

    public Punishment(Point pt) {
        super(pt);
        value = -75;
        img = Assets.redSoul;
    }
}
