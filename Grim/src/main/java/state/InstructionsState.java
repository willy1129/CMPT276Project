package state;

import command.Command;
import userInput.UserInput;
import gfx.Assets;

import java.awt.*;

/**
 * State that shows the user how to play the game
 *
 * @author Wai Lee
 * @version 1.0
 * @since 1.0
 */
public class InstructionsState implements GrimState {
    private static InstructionsState instance;

    public static InstructionsState instance() {
        if (instance == null) instance = new InstructionsState();
        return instance;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.instructions, 0, 0, null);

    }

    @Override
    public boolean tick() {
        UserInput input = UserInput.getInstance();
        Command move = input.getMove();
        return (move != Command.NONE);
    }
}


