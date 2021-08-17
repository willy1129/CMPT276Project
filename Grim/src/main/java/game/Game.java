package game;

import display.Display;
import state.*;
import userInput.UserInput;
import gfx.Assets;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Represents a game
 *
 * @author Adrienne Kwan
 * @version 1.0
 * @since 1.0
 */
public class Game implements Runnable {

    /**
     * Width of the application window
     */
    public static final int DEFAULT_WIDTH = 832;
    /**
     * Height of the application window
     */
    public static final int DEFAULT_HEIGHT = 576;
    /**
     * Title of the application window
     */
    private final String DEFAULT_NAME = "GRIM";

    /**
     * BufferStrategy
     */
    private BufferStrategy bs = null;

    /**
     * Graphics
     */
    private Graphics g;

    private Display display;

    /**
     * Stores current state instance of game
     */
    private GrimState state;

    private boolean running = false;

    /**
     * Thread
     */
    private Thread thread;

    /**
     * Constructor which initializes state to menu state
     */
    public Game() {
        state = MenuState.instance();
    }


    /**
     * Initializes display and assets
     */
    public void init() {
        display = new Display(DEFAULT_NAME, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Assets.init();
    }

    /**
     * Calls functionality required in each tick of the game. Also changes states depending on return value of tick().
     */

    public void tick() {
        // All state tick methods return a boolean.
        // The boolean is true if we want to change states, and false otherwise.
        boolean switchStates = state.tick();
        if (switchStates) {
            if (state instanceof GameState) {
                state = (((GameState) state).hasWon()) ? WinEndState.instance() : LoseEndState.instance();
            } else if (state instanceof MenuState){
                UserInput.getInstance().setMoveToNone();
                state = InstructionsState.instance();
            } else if (state instanceof InstructionsState){
                state = GameState.instance();
            } else if (state instanceof LoseEndState) {
                state = LoseEndState.nextState();
            } else if (state instanceof WinEndState) {
                state = WinEndState.nextState();
            }
            else {
                state = MenuState.instance();
            }
        }
    }

    /**
     * Game.Game loop. Ticks and renders once every 1/60 seconds.
     */
    @Override
    public void run() {
        init();

        final float FPS = 60; // Targeted frame per second
        double timePerTick = 1000000000/FPS; // Max number of ticks in 1 second;
        long now; // Time now
        long last = System.nanoTime(); // Time last tick
        double delta = 0; // Difference between time now and last tick


        while (running) {
            now = System.nanoTime();
            delta += (now - last)/timePerTick;
            last = now;

            // Tick if delta is greater than or equal to 1
            if (delta >= 1){
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    public void render() {
        // Set triple buffering if the BufferStrategy is null;
        bs = display.getCanvas().getBufferStrategy();

        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        //Set font of the UI
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        // Clear screen
        g.clearRect(0, 0, DEFAULT_WIDTH,DEFAULT_HEIGHT);

        // Draw
        if(state != null) {
            state.render(g);
        }
       
        // End drawing
        bs.show();
        g.dispose();
    }

    /**
     * Starts the game by starting a thread. Returns if game is already running.
     *
     * @author Sarah Li
     */
    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stops the game. Returns if already stopped.
     *
     * @author Sarah Li
     */
    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Graphics getG(){
        return g;
    }
}