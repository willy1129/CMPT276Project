package state;

import java.awt.*;

/**
 * Represents a state of a Grim game
 *
 * @author Adrienne Kwan
 * @version 1.0
 * @since 1.0
 */
public interface GrimState {
    Graphics g = null;

    /**
     * Draws the state to the display
     * @param g Graphics object
     */
    void render(Graphics g);

    /**
     * Does what should be done every tick in this state
     *
     * @return Whether or not state should end
     */
    boolean tick();
}