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

    private static float effectsVolume;
    private static float musicVolume;
    private boolean isMuted;

    private Clip music;


    public static void setEffectsVolume(float vol) {
        effectsVolume = vol;
    }

    public static void setMusicVolume(float vol) {
        musicVolume = vol;
    }

    public boolean isMuted(){
        return isMuted;
    }

    public void setIsMuted(boolean isMuted) {
        this.isMuted = isMuted;

        if (isMuted)
            stopMusic();

        else if (music != null)
            music.start();

    }

    public void playSound(String path) {
        if (!isMuted) {
            try {

                AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(path));
                Clip clip = AudioSystem.getClip();

                clip.open(ais);

                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(effectsVolume);

                clip.start();

            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void playMusic(String path) {

        if (!isMuted) {
            try {

                if (music != null)
                    stopMusic();

                AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(path));
                music = AudioSystem.getClip();

                music.open(ais);

                FloatControl gainControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(musicVolume);

                music.start();

            } catch (Exception e) { e.printStackTrace(); }
        }

    }

    public void stopMusic(){
        if (music != null)
            music.stop();
    }

    public boolean musicDone(){
        return music == null || !music.isRunning();
    }

}
