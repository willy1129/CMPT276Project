package gfx;

import java.awt.image.BufferedImage;

/**
 * Takes a BufferedImage and returns a 32x32 pixel cropped BufferedImage part of the image
 *
 * @author Sarah Li
 * @version 1.0
 * @since 1.0
 */
public class SpriteSheet {
    private final BufferedImage sheet;

    /**
     * Constructor stores sprite sheet
     *
     * @param sheet Sprite sheet to crop
     */
    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    /**
     * Crops a width by height BufferedImage at position x and y from a SpriteSheet
     *
     * @param x Starting x coordinate of image
     * @param y Starting y coordinate of image
     * @param width Width of cropped image from starting point
     * @param height Height of cropped image from starting point
     * @return BufferedImage of size width x height from sprite sheet
     */
    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
}
