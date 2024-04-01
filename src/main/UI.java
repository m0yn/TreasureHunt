package main;

import javax.swing.ImageIcon;

import entity.Dice;
import entity.Entity;
import entity.Pablo;
import tile.Tile;
import tile.TileManager;

import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import static entity.Carlo.dice;
import static main.BattleSystem.*;

import static main.TimedEventManager.*;
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
    private boolean playSong, fightSong, boardSong, marketSong, castleSong;
    public static Randomizer randomizer = new Randomizer();
    int yD = 51;
    public static int yX = 0;
    private boolean treasureMap;



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
            if (!fightSong) {
                gp.stopMusic();
                gp.playMusic(5);
                fightSong = true;
                playSong = false;
                boardSong = false;
                marketSong = false;
            }
        }

        else if (gp.gameState == gp.startState) {
            while (n==0) {
                gp.playMusic(0);
                n++;
            }
            g2.drawImage(tile[12].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
            g2.drawImage(tile[55].image, (gp.screenWidth/2)-220, (gp.screenHeight/2)-100, bSize*5, bSize, null);
            if (eightSecsPassed)
                drawStartScreen();
        }

        else if (gp.gameState == gp.playState) {
            battleOccurred = false;
            tenSecsPassed = false;
            bTimeSet = false;
            if (!playSong) {
                gp.stopMusic();
                gp.playMusic(Randomizer.randomize());
                playSong = true;
                fightSong = false;
                castleSong = false;
                boardSong = false;
                marketSong = false;
            }
        }

        else if (gp.gameState == gp.boardState) {
            drawBoardScreen();
            if (!boardSong) {
                gp.stopMusic();
                gp.playMusic(6);
                boardSong = true;
                castleSong = false;
                fightSong = false;
                playSong = false;
            }
        }

        else if (gp.gameState == gp.marketState) {

            drawMarketScreen();
            if (!marketSong) {
                gp.stopMusic();
                gp.playMusic(7);
                marketSong = true;
                castleSong = false;
                boardSong = false;
                fightSong = false;
                playSong = false;

            }

        }

        else if (gp.gameState == gp.castleState) {

            drawCastleScreen();
            if (!castleSong) {
                gp.stopMusic();
                gp.playMusic(8);
                castleSong = true;
                marketSong = false;
                boardSong = false;
                fightSong = false;
                playSong = false;
            }

        }



    }

    private int currentImageIndex = 0;

    public void drawStartScreen() {
        // Get the current image from the array based on currentImageIndex
        BufferedImage currentImage = gif[currentImageIndex];

        // Draw the current image onto the screen
        g2.drawImage(currentImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(tile[51].image, bSize*4, bSize, bSize * 8, bSize * 3, null);
        g2.drawImage(tile[41].image, 0, gp.screenHeight / 2, bSize * 8, bSize * 12, null);
        g2.drawImage(tile[42].image, bSize * 12, gp.screenHeight / 2, bSize * 8, bSize * 12, null);


        g2.drawImage(tile[54].image, bSize*8, bSize*12, bSize * 4, bSize, null);


        // Increment the image index for the next frame
        currentImageIndex++;

        // If we reached the end of the array, loop back to the beginning
        if (currentImageIndex >= gif.length) {
            currentImageIndex = 0;
        }

    }

    // Method to draw the pause screen
    public void drawBattleScreen() {
        // Draw background
        BufferedImage currentImage2 = gif2[currentImageIndex];
        g2.drawImage(currentImage2, 0, 0, gp.screenWidth, gp.screenHeight, null);

        // Increment the image index for the next frame
        currentImageIndex++;

        // If we reached the end of the array, loop back to the beginning
        if (currentImageIndex >= gif2.length) {
            currentImageIndex = 0;
        }


        // Draw UI elements
        g2.drawImage(tile[41].image, 0, gp.screenHeight / 2, bSize * 8, bSize * 12, null);
        g2.drawImage(tile[42].image, bSize * 12, gp.screenHeight / 2, bSize * 8, bSize * 12, null);
        g2.drawImage(tile[43].image, bSize, gp.screenHeight / 4, bSize * 6, bSize, null);
        g2.drawImage(tile[44].image, bSize, gp.screenHeight / 6, bSize * 6, bSize, null);
        g2.drawImage(tile[43].image, bSize * 12, gp.screenHeight / 4, bSize * 6, bSize, null);
        g2.drawImage(tile[44].image, bSize * 12, gp.screenHeight / 6, bSize * 6, bSize, null);

        g2.drawImage(tile[56].image, gp.screenWidth/2-bSize*2, bSize, bSize * 4, bSize, null);

        if (tenSecsPassed) {
            if (CarloWon && !PabloWon)
                g2.drawImage(tile[57].image, gp.screenWidth/2-bSize*4, bSize*4, bSize * 8, bSize*2, null);
        }


        // Set font size
        //g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));

        // Draw text centered horizontally
        //g2.drawString(text, getXforCenteredText(text), 80);
        //g2.drawString(text2, getXforCenteredText(text2), 200);
    }

    public int getXforCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;

    }

    public void drawBoardScreen() {

        g2.drawImage(tile[59].image, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.drawImage(tile[79].image, 0, bSize*3, bSize*2, bSize*2, null);
        g2.drawImage(tile[82].image, bSize*2, bSize*3, bSize*2, bSize*2, null);

        g2.drawImage(tile[79].image, gp.screenWidth-140, bSize*3, bSize*2, bSize*2, null);
        g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize*2, bSize*3, bSize*2, bSize*2, null);

        g2.drawImage(tile[80].image, bSize/8, bSize*6, bSize*3/2, bSize*3/2, null);
        g2.drawImage(tile[81].image, bSize*2+bSize/8, bSize*6-20, bSize, bSize*2, null);

        g2.drawImage(tile[80].image, gp.screenWidth-bSize*3/2, bSize*6, bSize*3/2, bSize*3/2, null);
        g2.drawImage(tile[81].image, (gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*6-20, bSize, bSize*2, null);

        g2.drawImage(tile[20].image, bSize/4, bSize*9, bSize, bSize, null);
        g2.drawImage(tile[81].image, bSize*2+bSize/8, bSize*9-20, bSize, bSize*2, null);

        g2.drawImage(tile[20].image, gp.screenWidth-100, bSize*9, bSize, bSize, null);
        g2.drawImage(tile[81].image, (gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*9-20, bSize, bSize*2, null);

    }

    public void drawMarketScreen () {

        g2.drawImage(tile[64].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(tile[63].image, gp.screenWidth/2-bSize*5, bSize/2, bSize*10, gp.screenHeight-bSize, null);

        // If-statement for arrow item selection
        if (yX < 0)
            yX = 0;
            else if (yX > 9)
                yX = 9;



        if (yX != 9) // Selection for weapons
        g2.drawImage(tile[66].image, gp.screenWidth/2-bSize*3, 345+(yX*yD), bSize/2, bSize/3, null);

        if (yX == 9) // selection for treasure map. Which is a bit farther.
        g2.drawImage(tile[66].image, gp.screenWidth/2-bSize*3, 345+525, bSize/2, bSize/3, null);

        // Bronze Sword
        g2.drawImage(tile[65].image, gp.screenWidth/2+bSize*2, 345, bSize/3, bSize/3, null); // Star Quality
        // Meteorite Sword
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2), 345+yD, bSize/3, bSize/3, null); // Star Quality
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2)+bSize/3, 345+yD, bSize/3, bSize/3, null);
        // Jewel-encrusted Sword
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2), 345+yD*2, bSize/3, bSize/3, null); // Star Quality
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2)+bSize/3, 345+yD*2, bSize/3, bSize/3, null);
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2)+53, 345+yD*2, bSize/3, bSize/3, null);
        // Wooden Bow
        g2.drawImage(tile[65].image, gp.screenWidth/2+bSize*2, 345+yD*3, bSize/3, bSize/3, null); // Star Quality
        // Horn Bow
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2), 345+yD*4, bSize/3, bSize/3, null); // Star Quality
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2)+bSize/3, 345+yD*4, bSize/3, bSize/3, null);
        // Crossbow
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2), 345+yD*5, bSize/3, bSize/3, null); // Star Quality
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2)+bSize/3, 345+yD*5, bSize/3, bSize/3, null);
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2)+53, 345+yD*5, bSize/3, bSize/3, null);
        // Leather Shield
        g2.drawImage(tile[65].image, gp.screenWidth/2+bSize*2, 345+yD*6, bSize/3, bSize/3, null); // Star Quality
        // Steel Shield
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2), 345+yD*7, bSize/3, bSize/3, null); // Star Quality
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2)+bSize/3, 345+yD*7, bSize/3, bSize/3, null);
        // Paladin's Shield
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2), 345+yD*8, bSize/3, bSize/3, null); // Star Quality
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2)+bSize/3, 345+yD*8, bSize/3, bSize/3, null);
        g2.drawImage(tile[65].image, (gp.screenWidth/2+bSize*2)+53, 345+yD*8, bSize/3, bSize/3, null);

    }

    public void drawCastleScreen () {

        g2.drawImage(tile[69].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(tile[41].image, 0, gp.screenHeight / 2, bSize * 8, bSize * 12, null);
        g2.drawImage(tile[70].image, bSize * 10, (gp.screenHeight/2)-bSize*2, bSize * 12, bSize * 12, null);

    }

}
