package main;

import javax.swing.ImageIcon;

import entity.Entity;
import tile.Tile;
import tile.TileManager;

import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import static main.BattleSystem.battle;
import static main.BattleSystem.winner;
import static main.TimedEventManager.eightSecsPassed;
import static main.TimedEventManager.tenSecsPassed;
import static tile.TileManager.*;

public class UI {

    GamePanel gp; // Instance variable for GamePanel
    Graphics2D g2; // Graphics2D context
    TimedEventManager timedEventManager;
    Font arial_40, arial_80B; // Fonts for UI
    public boolean messageOn; // Flag to indicate if a message is being displayed
    public String message = ""; // Message to be displayed
    int messageCounter = 0; // Counter for message display
    public boolean gameFinished; // Flag to indicate if the game is finished
    int[][] battleNum; // Array to store battle numbers

    double playTime; // Variable to store playtime
    DecimalFormat dFormat = new DecimalFormat("#0.00"); // Format for playtime display
    public static String text2; // Secondary text to be displayed
    String text = "IT'S ON!"; // Primary text to be displayed
    int n;



    // Constructor
    public UI(GamePanel gp) {
        this.gp = gp; // Initialize GamePanel instance
        arial_40 = new Font("Arial", Font.PLAIN, 40); // Initialize font
        arial_80B = new Font("Arial", Font.BOLD, 80); // Initialize font
    }

    // Method to display a message
    public void showMessage(String text) {
        message = text; // Set message
        messageOn = true; // Set message flag
    }

    // Method to draw UI elements
    public void draw(Graphics2D g2) {
        this.g2 = g2; // Set Graphics2D context

        // Set font and color
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // Draw pause screen if game is in battle state
        if (gp.gameState == gp.battleState) {
            drawBattleScreen();
        }
        else if (gp.gameState == 4) {
            while (n==0) {
                gp.playMusic(0);
                n++;
            }
            g2.drawImage(tile[12].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
            g2.drawImage(tile[55].image, (gp.screenWidth/2)-220, (gp.screenHeight/2)-100, bSize*5, bSize, null);
            if (eightSecsPassed) {
                drawStartScreen(); // this essentially means the program will wait 8.5 seconds before drawing the start screen.
            }
        }
        else if (gp.gameState == gp.playState) {
            gp.stopMusic(); // stops the track when the game begins. meaning when the user presses enter.
        }
    }

    private int currentImageIndex = 0;

    public void drawStartScreen() {
        // Get the current image from the array based on currentImageIndex
        BufferedImage currentImage = gifImages[currentImageIndex];

        // Draw the current image onto the screen
        g2.drawImage(currentImage, 0, 0, gp.screenWidth, gp.screenHeight, null); // this is to draw the rain effect on the start screen.
        g2.drawImage(tile[51].image, bSize*4, bSize, bSize * 8, bSize * 3, null);
        g2.drawImage(tile[41].image, 0, gp.screenHeight / 2, bSize * 8, bSize * 12, null);
        g2.drawImage(tile[42].image, bSize * 12, gp.screenHeight / 2, bSize * 8, bSize * 12, null);


        g2.drawImage(tile[54].image, bSize*8, bSize*12, bSize * 4, bSize, null);


        // Increment the image index for the next frame
        currentImageIndex++;

        // If we reached the end of the array, loop back to the beginning
        if (currentImageIndex >= gifImages.length) {
            currentImageIndex = 0;
        }

    }

    public void placeHolder() {

        System.out.println("Works!");

    }

    // Method to draw the pause screen
    public void drawBattleScreen() {
        // Draw grass tiles
        for (xT = 0; xT <= 20; xT++) {
            for (yT = 0; yT <= 20; yT++) {
                g2.drawImage(tile[31].image, bSize * xT, bSize * yT, bSize, bSize, null);
            }
        }

        // Draw UI elements
        g2.drawImage(tile[41].image, 0, gp.screenHeight / 2, bSize * 8, bSize * 12, null);
        g2.drawImage(tile[42].image, bSize * 12, gp.screenHeight / 2, bSize * 8, bSize * 12, null);
        g2.drawImage(tile[43].image, bSize, gp.screenHeight / 4, bSize * 6, bSize, null);
        g2.drawImage(tile[44].image, bSize, gp.screenHeight / 6, bSize * 6, bSize, null);
        g2.drawImage(tile[43].image, bSize * 12, gp.screenHeight / 4, bSize * 6, bSize, null);
        g2.drawImage(tile[44].image, bSize * 12, gp.screenHeight / 6, bSize * 6, bSize, null);

        if (tenSecsPassed) {
            g2.drawImage(tile[12].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        }

        // Set font size
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));

        // Draw text centered horizontally
        g2.drawString(text, getXforCenteredText(text), 80);
        g2.drawString(text2, getXforCenteredText(text2), 200);
    }

    public int getXforCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;

    }

}
