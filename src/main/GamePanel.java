package main;

import entity.Player;
import entity.Player2;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

import static entity.Entity.*;
import static entity.Player.dice;
import static entity.Player.squares;
import static entity.Player2.squares2;
import static main.BattleSystem.battle;

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
    int FPS = 120; // Game Framerate

    // Game state management
    public static int gameState = 1; // Current game state
    public static final int playState = 1; // ID for the gameplay state
    public static final int battleState = 2; // ID for the battle state

    TileManager tileM = new TileManager(this); // Manager for tiles and map drawing
    KeyHandler keyH = new KeyHandler(); // Handler for keyboard input

   // Game thread for the main game loop
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this); // Collision checker for the main player
    public CollisionChecker2 cChecker2 = new CollisionChecker2(this); // Collision checker for the second player
    Player player; // Main player object
    Player2 player2; // Second player object


    // Method for initial game setup (currently empty)
    public void setupGame() {
    }

    // Constructor initializes the game panel and player objects
    public GamePanel() {
        // Initialization of player objects with their starting values
        player = new Player(this, keyH,100,500,30);
        player.setDefaultValues();
        player.getPlayerImage();

        player2 = new Player2(this,keyH,100,500,10);
        player2.setDefaultValues2();
        player2.getPlayerImage2();

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

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {// Main game loop, controlling game updates and rendering


            update();
            repaint();

            // Checks for an error then throws an exception.
            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; // Convert to milliseconds

                if (remainingTime < 0) {

                    remainingTime = 0;

                }

                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;


            } catch (InterruptedException e) {

                throw new RuntimeException(e);

            }


        }

    }

    // Updates the game state: checks for collisions and changes game states
    public void update() {
        // Checks for player collision to trigger battle state
        if (WorldX == WorldX2 && WorldY == WorldY2 && WorldX != 0 && ((squares == 0 || squares2 == 0) && (squares == dice.result*80 || squares2 == dice.result*80))) {
            gameState = battleState;
        }

        if (gameState == playState) {
            // Update players and game state based on the current game state
            player.update();
            player2.update2();
        }

       if (gameState == battleState) {
           // Engage battle mode
            battle(player, player2);
        }

    }


    // Paint Function
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);
        player2.draw2(g2);

        ui.draw(g2);

        g2.dispose();

    }

}
