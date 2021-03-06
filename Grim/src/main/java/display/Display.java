package display;

import userInput.UserInput;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

/**
 * Creates the canvas and window for graphics to be displayed on
 *
 * @author Sarah Li
 * @version 1.0
 * @since 1.0
 */
public class Display {
    final private String title;
    final private int width, height;

    private Canvas canvas;

    /** Constructor takes in title, width and height of window and calls createDisplay() method
     *
     * @param title of the window
     * @param width in pixels of the window
     * @param height in pixels of the window
     */
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }

    /** Makes a new JFrame using the class's values for title, width and height. Adds a canvas to the JFrame.
     * Sets window to display correctly on screen without being able to alter the size.
     */
    private void createDisplay() {
        JFrame frame;

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setVisible(true); // makes sure display is visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes sure program closes
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // creates window in center of screen
        frame.addKeyListener(UserInput.getInstance());
        frame.setFocusable(true);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height)); // keeps canvas at given width and height
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack(); // to ensure the whole canvas is visible
        frame.setVisible(true);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}