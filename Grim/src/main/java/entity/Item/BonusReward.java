package entity.item;

import gfx.Assets;

import java.awt.*;

/**
 * This class represents bonus rewards (golden souls), which are not required be collected in order to win
 * the game
 *
 * @author Wai Lee
 * @version 0.1
 * @since 2021-03-06
 */

public class BonusReward extends Item {
    private int remainingTick = 300;

    public BonusReward(Point pt) {
        super(pt);
        value = 100;
        img = Assets.goldSoul;
    }

    public void countDown() {
        if (remainingTick > 0) {
            remainingTick -= 1;
        } else {
            super.setCollected();
        }
    }
}
