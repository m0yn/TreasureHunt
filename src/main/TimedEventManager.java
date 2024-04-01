package main;

import java.awt.*;


import static main.BattleSystem.battleStarted;

public class TimedEventManager {

    GamePanel gp;
    private UI ui;
    Graphics2D g2;
    private boolean eventOccurred, event1Occurred, event2Occured;
    private long event1StartTime = System.nanoTime();
    private long event2StartTime;
    private long battleStartTime;
    public long currentTime = 0;
    int i;
    public static boolean tenSecsPassed, eightSecsPassed, bTimeSet;


    public void handleTimedEvents() {
        currentTime = System.nanoTime();
        long elapsedTime = currentTime - event1StartTime;

        // Check for first timed event (after 10 seconds)
        if (!event1Occurred && elapsedTime >= 8_500_000_000L) {
            eightSecsPassed = true;
            event1Occurred = true;
            event1StartTime = currentTime; // Record the time of the first event
        }

        if (battleStarted) {
            while (!bTimeSet) {
                battleStartTime = System.nanoTime(); // Set battle start time only once
                bTimeSet = true;
            }
                if (currentTime - battleStartTime >= 10_000_000_000L) {
                    tenSecsPassed = true;
            }
        }

    }

    public TimedEventManager(GamePanel gp) {
        this.gp = gp;
    }

    public void performTimedEvent2(GamePanel gp) {

        this.gp = gp;

        gp.gameState = 4;

    }

}