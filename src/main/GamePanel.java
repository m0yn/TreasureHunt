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

    // Screen settings
    public final int originalTileSize = 32;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public UI ui = new UI(this);
    int FPS = 120; // Game Framerate

    /// Game State
    public static int gameState = 1;
    public static final int playState = 1;
    public static final int battleState = 2;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();

    // In-game clock
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public CollisionChecker2 cChecker2 = new CollisionChecker2(this);
    Player player;
    Player2 player2;

    public void setupGame() {



    }

    // Constructor for the GamePanel with default settings.
    public GamePanel() {

        player = new Player(this, keyH,100,500,12);
        player.setDefaultValues();
        player.getPlayerImage();

        player2 = new Player2(this,keyH,100,500,10);
        player2.setDefaultValues2();
        player2.getPlayerImage2();


        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    // Start the game thread
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    // Function that repaints the screen 60 times a second.
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

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

    public void update() {

        if (WorldX == WorldX2 && WorldY == WorldY2 && WorldX != 0 && ((squares == 0 || squares2 == 0) && (squares == dice.result*80 || squares2 == dice.result*80))) {
            gameState = battleState;
        }

        if (gameState == playState) {
            player.update();
            player2.update2();
        }

       if (gameState == battleState) {
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
