package state;

import command.Command;
import controller.Controller;
import sound.SoundAssets;
import userInput.UserInput;
import gfx.Assets;

import java.awt.*;

/**
 * Represents a GameState of a Grim game. Manages game information such as score and item counts.
 * Contains a Controller to which GameState delegates the initialization and movements of entities, board, and walls.
 *
 * @author Adrienne Kwan
 * @version 1.0
 * @since 1.0
 */
public class GameState implements GrimState {
    private static GameState instance;
    private static Controller c;
    private static boolean isOver = false;
    private static boolean hasWon = false;
    private static int currentScore = 0;
    private static int countRegsCollected = 0,countBonusCollected = 0, time = 0;
    private static final int countRegsNeeded = 6;
    private int lastTick = 0;
    public int finalTime, finalScore, finalRegsCollected, finalBonusCollected;

    /**
     * Constructor for GameState that creates a new Controller.Controller object and initializes graphics
     */
    private GameState() {
        c = Controller.getInstance();
    }

    /**
     * Returns (creating if necessary) a GameState instance
     * Singleton pattern
     *
     * @return a GameState instance
     */
    public static GameState instance() {
        if (instance == null)
            instance = new GameState();
        resetGame();
        return instance;
    }

    /**
     * Resets GameState fields that may change game-to-game
     */
    private static void resetGame() {
        c = Controller.resetInstance();
        isOver = false;
        hasWon = false;
        currentScore = 0;
        countRegsCollected = 0;
        countBonusCollected = 0;
        time = 0;
    }

    @Override
    public void render(Graphics g) {
        c.render(g);
        g.drawString(Integer.toString(time), 32, 22);
        g.drawImage(Assets.clock, 5, 5, null);
        g.drawImage(Assets.blueSoul,778, 5, 20, 20, null);
        g.drawString(countRegsCollected+"/"+countRegsNeeded, 800, 22);
        g.drawString("Score: " + currentScore, 750, 60);
        g.drawImage(Assets.fire, 480, 540, null);
        g.drawImage(Assets.fire, 352, 540, null);
    }

    /**
     * Moves character and checks for character's interactions with game elements
     *
     * @return whether or not state should end
     */
    @Override
    public boolean tick() {
        lastTick++;

        if (lastTick==60) {
            time++;
            lastTick=0;
        }

        UserInput input = UserInput.getInstance();
        Command move =  input.getMove();
        c.moveCharacter(move);
        c.moveMovingEnemies();
        c.manageBonusRewards();
        checkCollisions();
        return (checkEndCell() || checkScoreNeg());
    }

    /**
     * Changes game information if mainCharacter collides with items and enemies
     */
    private void checkCollisions() {
        Rectangle mBound = c.mainCharacter.getBound();

        if (addScore( c.checkRegCollision(mBound, true))) {
            addRegCount();
            SoundAssets.reward_sound();
        }

        if (addScore( c.checkBonusCollision(mBound, true))) {
            addBonusCount();
            SoundAssets.bonus_sound();
        }

        addScore( c.checkPunCollision(mBound, true) );
        addScore( c.checkEnemyCollision(mBound, true) );
    }

    /**
     * Checks if mainCharacter is at end cell and if so, if all regular rewards have been collected
     *
     * @return return if game has been won
     */
    private boolean checkEndCell() {
        Rectangle end = new Rectangle(384,540,96,32);
        hasWon = (countRegsCollected >= countRegsNeeded && end.contains(c.mainCharacter.getPosition()));

        if (hasWon) {
            SoundAssets.win_sound();
        }
        return hasWon;
    }

    /**
     * Ends game with user loss if mainCharacter's score has fallen below zero
     *
     * @return return if game has been lost
     */
    private boolean checkScoreNeg() {
        isOver = (currentScore < 0);
        finalTime = time;
        finalScore = currentScore;
        finalRegsCollected = countRegsCollected;
        finalBonusCollected = countBonusCollected;
        return isOver;
    }

    /**
     * Returns whether game has been won or not and fills final info fields
     *
     * @return return if game has been won
     */
    public boolean hasWon() {
        finalTime = time;
        finalScore = currentScore;
        finalRegsCollected = countRegsCollected;
        finalBonusCollected = countBonusCollected;
        return hasWon;
    }

    /**
     * Adds given value to GameState score field
     *
     * @param val value to add to score field
     * @return true if value is zero, false otherwise
     */
    private static boolean addScore(int val){
        currentScore += val;
        return val != 0;
    }

    private static void addRegCount(){
        countRegsCollected++;
    }

    private static void addBonusCount(){
        countBonusCollected++;
    }
}
