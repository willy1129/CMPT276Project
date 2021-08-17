package sound;
import utils.Utils;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Loads sound files from given path
 *
 * @author Sarah Li
 * @version 1.0
 * @since 1.0
 */
public class SoundLoader {

    /**
     * Loads Clip files from path
     *
     * @param path of sound file
     * @return clip
     */
    public static Clip loadSound(String path)
    {
        try {
            String editedPath = Utils.editPath(path);
            InputStream in = SoundLoader.class.getResourceAsStream(editedPath);
            if (in == null) in = SoundLoader.class.getResourceAsStream("/"+editedPath);
            //add buffer for mark/reset support (https://exceptionshub.com/java-io-ioexception-markreset-not-supported.html)
            InputStream bufferedIn = new BufferedInputStream(in);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(bufferedIn));
            return clip;
        }
        catch (Exception exc) {
            System.out.println(System.getProperty("user.dir"));
            exc.printStackTrace(System.out);
            System.exit(1);
        }
        return null;
    }
}
