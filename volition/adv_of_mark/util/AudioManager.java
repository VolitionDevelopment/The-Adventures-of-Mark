package volition.adv_of_mark.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Created by mccloskeybr on 4/19/16.
 */
public class AudioManager {

    private static AudioManager instance;

    public static AudioManager getInstance(){
        if (instance == null)
            instance = new AudioManager();

        return instance;
    }

    private static float volume;
    private static boolean isMuted;

    public static void setIsMuted(boolean muted) {
        isMuted = muted;
    }

    public static void setVolume(float vol) {
        volume = vol;
    }

    public void playAudio(String path) {
        if (!isMuted) {
            try {

                AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(path));
                Clip clip = AudioSystem.getClip();

                clip.open(ais);

                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volume);

                clip.start();

            } catch (Exception e) { e.printStackTrace(); }
        }
    }

}
