package main;

import java.awt.*;


import static main.BattleSystem.battleStarted;

public class TimedEventManager {

    GamePanel gp; // ignore.
    private UI ui; // ignore.
    Graphics2D g2; // ignore.
    private boolean eventOccurred, event1Occurred, event2Occured;
    private long event1StartTime = System.nanoTime();
    private long battleStartTime;
    public long currentTime = 0;
    int i;
    public static boolean tenSecsPassed;
    public static boolean eightSecsPassed;


    public void handleTimedEvents() {
        currentTime = System.nanoTime();
        long elapsedTime = currentTime - event1StartTime;

        // this is just a place holder to explain how timed events work. it will draw a screen that says "the game will start in 8.5 seconds" and then draw the start screen
        if (!event1Occurred && elapsedTime >= 8_500_000_000L) { // checks if event has not yet occurred and if 8.5 seconds have passed.
            eightSecsPassed = true; // sets the flag to true, which we will use in an if-statement in UI to draw the start screen.
            event1Occurred = true; // sets event1occurred to true so that it does not loop over and over.
            event1StartTime = currentTime; // Records the time of the first event, so that if we want to do another even say 10 seconds after this one occurred.
        }

        if (battleStarted) { // battleStarted flag is set to true in battleSystem class if and when battle() is called.
            while (i==0) { // while loop to insure what's inside only happens once. I could also use the way chatGPT does it which is like the one above.
                battleStartTime = System.nanoTime();
                i++;
            }
                if (currentTime - battleStartTime >= 10_000_000_000L) { // checks if 10 seconds have passed since battle() was called.
                    tenSecsPassed = true; // sets the flag to true, which we will use in UI class to draw a black screen on display. this is just a placeholder or a test.
            }
        }

    }

    public TimedEventManager(GamePanel gp) { // this is not necessary for now. Ignore it.
        this.gp = gp;
    }

}