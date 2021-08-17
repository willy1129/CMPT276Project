package sound;

/**
 * Plays sound effects
 *
 * @author Sarah Li
 * @version 1.0
 * @since 1.0
 */
public class SoundAssets {

    public static void reward_sound() {
        SoundLoader.loadSound("sounds/reward_sound.wav").start();
    }

    public static void bonus_sound() {
        SoundLoader.loadSound("sounds/bonus_sound.wav").start();
    }

    public static void damage_sound() {
        SoundLoader.loadSound("sounds/damage_sound.wav").start();
    }

    public static void win_sound() {
        SoundLoader.loadSound("sounds/win_sound.wav").start();
    }

    public static void lose_sound() {
        SoundLoader.loadSound("sounds/lose_sound.wav").start();
    }
}
