package main;

import entity.Carlo;
import entity.Pablo;
import jdk.jfr.Event;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

import static entity.Carlo.dice;
import static entity.Pablo.dice2;
import static entity.Carlo.squares;
import static entity.Entity.*;
import static entity.Pablo.squares2;
import static main.BattleSystem.*;
import static tile.TileManager.bSize;

public class GamePanel extends JPanel implements Runnable {
    // GamePanel class, extending JPanel and implementing Runnable for game loop execution
    // Screen settings
    public final int originalTileSize = 32;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public UI ui = new UI(this); // User interface manager
    int FPS = 30; // Game Framerate

    // Game state management
    public static int gameState = 4; // Current game state
    public static final int playState = 1;
    public static final int battleState = 2;
    public static final int boardState = 3;
    public static final int startState = 4;
    public static final int marketState = 5;
    public static final int castleState = 6;
    //
    public boolean carloMarket, pabloMarket, carloCastle, pabloCastle, endState;



    TileManager tileM = new TileManager(this); // Manager for tiles and map drawing
    KeyHandler keyH = new KeyHandler(); // Handler for keyboard input

   // Game thread for the main game loop
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this); // Collision checker for the main player
    public CollisionChecker2 cChecker2 = new CollisionChecker2(this); // Collision checker for the second player
    Carlo carlo; // Main player object
    Pablo pablo; // Second player object
    TimedEventManager timedEventManager = new TimedEventManager(this);
    Sound sound = new Sound();


    // Method for initial game setup (currently empty)
    public void setupGame() {
    }

    // Constructor initializes the game panel and player objects
    public GamePanel() {
        // Initialization of player objects with their starting values
        carlo = new Carlo(this, keyH,0,500,0);
        carlo.setDefaultValues();
        carlo.getPlayerImage();

        pablo = new Pablo(this,keyH,0,500,0);
        pablo.setDefaultValues2();
        pablo.getPlayerImage2();

        // Set the size, background color, and buffering for the game panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    // Start the game thread
    // Starts the main game loop thread
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Function that repaints the screen 60 times a second.
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {// Main game loop, controlling game updates and rendering

            update();
            repaint();
            timedEventManager.handleTimedEvents();

            // Checks for an error then throws an exception.
            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // Convert to milliseconds

                if (remainingTime < 0) {

                    remainingTime = 0;

                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {

                throw new RuntimeException(e);

            }

        }

    }

    public void performTimedEvent() {
        // Your code to execute after the timed event occurs
        // For example, you might spawn an enemy, display a message, etc.
        System.out.println("Timed event occurred!");
    }

    // Updates the game state: checks for collisions and changes game states
    public void update() {
        // Checks for player collision to trigger battle state

        if (keyH.startGame) {
            gameState = playState;
        }

        if (WorldX == WorldX2 && WorldY == WorldY2 && WorldX != 0) {
            gameState = battleState;
            if (CarloWon) {
                WorldX2 = 0;
                WorldY2 = bSize;
                squares2 = 0;
            }
            else if (PabloWon) {
                WorldX = 0;
                WorldY = bSize;
                squares = 0;
            }
        }

        if (keyH.startBoard) {
            gameState = boardState;
        }

        if (( WorldX == bSize*4 && WorldY == bSize || WorldX == bSize*6 && WorldY == bSize*3 || WorldX == bSize && WorldY == bSize*7
                ||  WorldX == bSize*4 && WorldY == bSize*10 ||  WorldX == bSize*8 && WorldY == bSize*8 )&& !carloMarket && squares != dice.result) {
            gameState = marketState;
            carloMarket = true;
        }

        if (( WorldX2 == bSize*4 && WorldY2 == bSize || WorldX2 == bSize*6 && WorldY2 == bSize*3 || WorldX2 == bSize && WorldY2 == bSize*7
                ||  WorldX2 == bSize*4 && WorldY2 == bSize*10 ||  WorldX2 == bSize*8 && WorldY2 == bSize*8 )&& !pabloMarket && squares2 != dice2.result) {
            gameState = marketState;
            pabloMarket = true;
        }

        if (WorldX == bSize*6 && WorldY == bSize*5 && !carloCastle && squares != dice.result) {
            gameState = castleState;
            carloCastle = true;
        }

        // If Pablo lands at the castle tile, switch to Castle State.
        if (WorldX2 == bSize*6 && WorldY2 == bSize*5 && !pabloCastle && squares2 != dice2.result) { // "!pabloCastle" condition to only do the action once. "squares2 != dice2.result" condition so that player isn't stuck in the state if his turn ends on a castle tile.
            gameState = castleState; // switches to Castle State
            carloCastle = true; // sets "carloCastle" as to not repeat the action.
        }




        //
        if (gameState == playState) {
            // Update players and game state based on the current game state
            carlo.update();
            pablo.update2();
        }

       if (gameState == battleState) {
           // Engage battle mode
            battle(carlo, pablo);
        }

    }


    // Paint Function
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        carlo.draw(g2);
        pablo.draw2(g2);

        ui.draw(g2);

        g2.dispose();

    }

    public void playMusic(int i) {

        sound.setFIle(i);
        sound.play();
        sound.loop();

    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFIle(i);
        sound.play();
    }

}
