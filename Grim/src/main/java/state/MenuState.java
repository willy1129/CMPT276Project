package state;

import command.Command;
import userInput.UserInput;
import gfx.Assets;

import java.awt.*;

/**
 * Represents a menu state of a Grim game
 *
 * @author Adrienne Kwan
 * @version 1.0
 * @since 1.0
 */
public class MenuState implements GrimState {
    private static MenuState instance;
    private static int ticksLeftBeforeSwitching = 10;

    private MenuState() {}

    /**
     * Returns (creating if necessary) a MenuState instance
     * Singleton pattern
     *
     * @return a MenuState instance
     */
    public static MenuState instance() {
        if (instance == null) instance = new MenuState();
        ticksLeftBeforeSwitching = 10;
        return instance;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menu, 0, 0, null);
    }

    /**
     * Checks user's move and returns whether the state should end
     *
     * @return whether or not the current state should end
     */
    @Override
    public boolean tick() {
        UserInput input = UserInput.getInstance();
        Command move = input.getMove();
        ticksLeftBeforeSwitching--;
        if (ticksLeftBeforeSwitching <= 0)
            return (move != Command.NONE);
        else return false;
    }
}
