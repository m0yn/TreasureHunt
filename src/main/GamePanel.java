package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 32;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 8;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();

    // In-game clock
    Thread gameThread;
    Player player = new Player(this,keyH);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    // Function that repaints the screen 60 times a second.
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

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


    // Paint Function
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();

    }


}
