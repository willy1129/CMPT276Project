package gfx;

import java.awt.image.BufferedImage;

/**
 * Crops each sprite from the spritesheet to use
 *
 * @author Sarah Li
 * @version 1.0
 * @since 1.0
 */
public class Assets {
    public static BufferedImage wall, ground, blueSoul, redSoul, goldSoul, fire;
    public static BufferedImage grimFront1, grimFront2, grimBack1, grimBack2, grimLeft1, grimLeft2, grimRight1, grimRight2;
    public static BufferedImage angelFront1, angelFront2, angelBack1, angelBack2, angelLeft1, angelLeft2, angelLeft3, angelRight1, angelRight2, angelRight3;
    public static  BufferedImage menu, instructions, gameOver1, gameOver2, congrats1, congrats2, clock;

    private static final int width = 32, height = 32;

    /**
     * Crops each sprite from the spritesheet
     */
    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Spritesheet.png"));
        SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Menu.png"));
        SpriteSheet instructionSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Instructions.png"));
        SpriteSheet gameOver1Sheet = new SpriteSheet(ImageLoader.loadImage("/textures/GameOver1.png"));
        SpriteSheet gameOver2Sheet = new SpriteSheet(ImageLoader.loadImage("/textures/GameOver2.png"));
        SpriteSheet congrats1Sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Congratulations1.png"));
        SpriteSheet congrats2Sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Congratulations2.png"));
        SpriteSheet clockSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Clock.png"));

        ground = sheet.crop(0, 0, width, height);
        wall = sheet.crop(width, 0, width, height);
        fire = sheet.crop(width * 4, height, width, height);

        blueSoul = sheet.crop(width * 2, 0, width, height);
        redSoul = sheet.crop(width * 3, 0, width, height);
        goldSoul = sheet.crop(width * 4, 0, width, height);

        // Cropped Grim Reaper sprites
        grimFront1 = sheet.crop(0, height, width, height);
        grimFront2 = sheet.crop(0, height * 2, width, height);
        grimBack1 = sheet.crop(width, height, width, height);
        grimBack2 = sheet.crop(width, height * 2, width, height);
        grimLeft1 = sheet.crop(width * 2, height, width, height);
        grimLeft2 = sheet.crop(width * 2, height * 2, width, height);
        grimRight1 = sheet.crop(width * 3, height, width, height);
        grimRight2 = sheet.crop(width * 3, height * 2, width, height);

        // Cropped Angel Sprites
        // Note: Complete angel left and right animation loop is 1-2-1-3
        angelFront1 = sheet.crop(0, height * 3, width, height);
        angelFront2 = sheet.crop(0, height * 4, width, height);
        angelBack1 = sheet.crop(width, height * 3, width, height);
        angelBack2 = sheet.crop(width, height * 4, width, height);
        angelLeft1 = sheet.crop(width * 2, height * 3, width, height);
        angelLeft2 = sheet.crop(width * 3, height * 3, width, height);
        angelLeft3 = sheet.crop(width * 4, height * 3, width, height);
        angelRight1 = sheet.crop(width * 2, height * 4, width, height);
        angelRight2 = sheet.crop(width * 3, height * 4, width, height);
        angelRight3 = sheet.crop(width * 4, height * 4, width, height);

        // UI buffered images
        menu = menuSheet.crop(0,0,832,576);
        instructions = instructionSheet.crop(0,0,832,576);
        gameOver1 = gameOver1Sheet.crop(0,0,832,576);
        gameOver2 = gameOver2Sheet.crop(0,0,832,576);
        congrats1 = congrats1Sheet.crop(0,0,832,576);
        congrats2 = congrats2Sheet.crop(0,0,832,576);
        clock = clockSheet.crop(0, 0, 21, 21);
    }
}
