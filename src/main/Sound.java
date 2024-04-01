package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound () {

        soundURL[0] = getClass().getResource("/music/start.wav");
        soundURL[1] = getClass().getResource("/music/vkiller.wav");
        soundURL[2] = getClass().getResource("/music/btears.wav");
        soundURL[3] = getClass().getResource("/music/wicked.wav");
        soundURL[4] = getClass().getResource("/music/silence.wav");
        soundURL[5] = getClass().getResource("/music/fight.wav");
        soundURL[6] = getClass().getResource("/music/tthemeO.wav");
        soundURL[7] = getClass().getResource("/music/silenceO.wav");
        soundURL[8] = getClass().getResource("/music/vkillerO.wav");

    }

    public void setFIle(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {}

    }

    public void play() {

        clip.start();

    }

    public void loop() {

        clip.loop(clip.LOOP_CONTINUOUSLY);

    }

    public void stop() {

        clip.stop();

    }

}
