package state;

import command.Command;
import userInput.UserInput;
import gfx.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;

import static command.Command.ENTER;

/**
 * Represents a winning end state of a Grim game
 *
 * @author Adrienne Kwan
 * @version 1.0
 * @since 1.0
 */
public class WinEndState implements GrimState {
    private static WinEndState instance;
    private static BufferedImage playOrMenu = Assets.congrats1;
    private final int minTicksBetweenChanges = 10;
    private int ticksSinceLastChange = minTicksBetweenChanges;

    /**
     * Returns (creates if necessary) a WinEndState instance
     * Singleton pattern
     *
     * @return a WinEndState instance
     */
    public static WinEndState instance() {
        if (instance == null) instance = new WinEndState();
        return instance;
    }

    /**
     * Renders game over final screen with scores
     *
     * @author Sarah Li
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(playOrMenu, 0, 0, null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString(Integer.toString(GameState.instance().finalRegsCollected), 452, 259);
        g.drawString(Integer.toString(GameState.instance().finalBonusCollected), 452, 295);
        g.drawString(Integer.toString(GameState.instance().finalTime), 430,384);
        g.drawString(Integer.toString(GameState.instance().finalScore), 456, 351);
        g.drawImage(Assets.blueSoul,383,232,32,32,null);
        g.drawImage(Assets.goldSoul,383,269,32,32,null);
    }

    /**
     * Checks user's move, changes assets, and returns whether the state should end
     *
     * @return whether or not the current state should end
     */
    @Override
    public boolean tick()
    {
        UserInput input = UserInput.getInstance();
        Command move =  input.getMove();
        ticksSinceLastChange++;
        if (ticksSinceLastChange >= minTicksBetweenChanges) {
            switch (move) {
                case UP:
                case DOWN:
                    playOrMenu = (playOrMenu == Assets.congrats1) ? Assets.congrats2 : Assets.congrats1;
                    ticksSinceLastChange = 0;
                    break;
                default:
                    break;
            }
        }
        return (move == ENTER);
    }

    /**
     * Checks and returns which GrimState instance the Game.Game should use next
     *
     * @return the next GrimState instance
     */
    public static GrimState nextState() {
        return (playOrMenu == Assets.congrats1) ? GameState.instance() : MenuState.instance();
    }
}
