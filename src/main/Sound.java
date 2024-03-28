package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound () {

        soundURL[0] = getClass().getResource("/music/start.wav"); // this is the track that plays when the game begins.

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
