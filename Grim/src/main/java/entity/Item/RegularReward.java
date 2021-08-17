package entity.item;


import gfx.Assets;

import java.awt.*;

/**
 * This class represents regular rewards (blue souls) that have to be collected in order to win
 * the game
 *
 * @author Wai Lee
 * @version 0.1
 * @since 2021-03-06
 */

public class RegularReward extends Item {
    public RegularReward(Point pt) {
        super(pt);
        value = 50;
        img = Assets.blueSoul;
    }
}
