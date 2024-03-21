package main;

import entity.Entity;
import tile.Tile;
import tile.TileManager;

import java.awt.*;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import static main.BattleSystem.battle;
import static main.BattleSystem.winner;
import static tile.TileManager.bSize;
import static tile.TileManager.tile;
import static tile.TileManager.xT;
import static tile.TileManager.yT;

public class UI {

    GamePanel gp; // Instance variable for GamePanel
    Graphics2D g2; // Graphics2D context
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
            drawPauseScreen();
        }
    }

    // Method to draw the pause screen
    public void drawPauseScreen() {
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
