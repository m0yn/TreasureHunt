package main;

import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import static entity.Entity.*;
import static main.BattleSystem.*;

import static main.KeyHandler.*;
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
    private boolean playSong, fightSong, boardSong, marketSong, castleSong, endSong;
    public static Randomizer randomizer = new Randomizer();
    int yD = 51;
    public static int yX = 1;
    public static int yS = bSize*2-25;
    public static int ySX = 0;
    public boolean treasureMap, treasureSought, lostSought;
    public static int tt, qq, randT, randP;
    public int li;
    public int q = 0;
    public int ss = 69;
    public boolean darw;


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

            if (!treasureSought) {
                randT = Randomizer.treasureSelect();
                treasureSought = true;
            }

            wait = true;
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

            darw = false;
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

        else if (gp.gameState == gp.trapState) {

                g2.drawImage(tile[109].image, bSize * 13, gp.screenHeight/8, bSize * 5, bSize * 5, null);


                //g2.drawImage(tile[66].image, bSize * 12 + 10, gp.screenHeight / 2 - 10, bSize / 2, bSize / 3, null);
                if (ySX > 1)
                    ySX = 1;
                else if (ySX < 0)
                    ySX = 0;

                g2.drawImage(tile[66].image, bSize*12+10, (gp.screenHeight/2-80)-(yS*ySX), bSize/2, bSize/3, null);


        }

        else if (gp.gameState == gp.lostState) {


            if (!lostSought) {
                li = Randomizer.lostSelect();
                lostSought = true;
            }

            g2.drawImage(tile[q].image, bSize * 13, gp.screenHeight/8, bSize * 5, bSize * 4, null);

        }

        else if (gp.gameState == gp.treasureState) {

            g2.drawImage(tile[qq].image, bSize * 13, gp.screenHeight/8, bSize * 5, bSize * 4, null);


        }

        else if (gp.gameState == gp.endState) {

            drawEndScreen();
            if (!endSong) {
                gp.stopMusic();
                gp.playMusic(9);
                endSong = true;
                castleSong = false;
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
        g2.drawImage(tile[42].image, bSize * 12, gp.screenHeight / 2, bSize * 8, bSize * 12, null);
        g2.drawImage(tile[41].image, 0, gp.screenHeight / 2, bSize * 8, bSize * 12, null);
        g2.drawImage(tile[79].image, 0, bSize*2, bSize*2, bSize*2, null);
        g2.drawImage(tile[79].image, gp.screenWidth-140, bSize*2, bSize*2, bSize*2, null);
        g2.drawImage(tile[80].image, bSize/8, bSize*5, bSize*3/2, bSize*3/2, null);
        g2.drawImage(tile[80].image, gp.screenWidth-bSize*3/2, bSize*5, bSize*3/2, bSize*3/2, null);

        // Pablo's Power
        if (!startBattle && wait) {
            switch (pabloPowerB) {
                case 1:
                    g2.drawImage(tile[81].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
            }
        } else if (startBattle || !wait) {
            switch (pabloPowerA) {
                case 1:
                    g2.drawImage(tile[81].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, (gp.screenWidth - bSize * 3 / 2) - bSize * 3 / 2, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
            }
        }

        // Carlo's Power
        if (!startBattle && wait) {
            switch (carloPowerB) {
                case 1:
                    g2.drawImage(tile[81].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
            }
        } else if (startBattle || !wait) {
            switch (carloPowerA) {
                case 1:
                    g2.drawImage(tile[81].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, bSize * 2 + bSize / 8, bSize * 5 - 20, bSize, bSize * 2, null);
                    break;
            }
        }

        // Pablo's Money

        if (!startBattle && wait) {

            switch (pabloMoneyB/1000) {
                case 1:
                    g2.drawImage(tile[81].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
            }

            switch ((pabloMoneyB/100)%10) {

                case 1:
                    g2.drawImage(tile[81].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;

            }

            switch ((pabloMoneyB/10)%10) {

                case 1:
                    g2.drawImage(tile[81].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;

            }

            switch (pabloMoneyB%10) {

                case 1:
                    g2.drawImage(tile[81].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;

            }

        } else if (startBattle || !wait) {

            switch (pabloMoneyA/1000) {
                case 1:
                    g2.drawImage(tile[81].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, (gp.screenWidth-140)-bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
            }

            switch ((pabloMoneyA/100)%10) {

                case 1:
                    g2.drawImage(tile[81].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, (gp.screenWidth-140)-bSize*3, bSize*2, bSize, bSize*2, null);
                    break;

            }

            switch ((pabloMoneyA/10)%10) {

                case 1:
                    g2.drawImage(tile[81].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, (gp.screenWidth-140)-bSize*2, bSize*2, bSize, bSize*2, null);
                    break;

            }

            switch (pabloMoneyA%10) {

                case 1:
                    g2.drawImage(tile[81].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, (gp.screenWidth-140)-bSize, bSize*2, bSize, bSize*2, null);
                    break;

            }

        }


        // Carlo's Money

        if (!startBattle && wait) {

            switch (carloMoneyB/1000) {

                case 1:
                    g2.drawImage(tile[81].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;

            }

            switch ((carloMoneyB/100)%10) {

                case 1:
                    g2.drawImage(tile[81].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;

            }

            switch ((carloMoneyB)/10%10) {

                case 1:
                    g2.drawImage(tile[81].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;

            }

            switch (carloMoneyB%10) {

                case 1:
                    g2.drawImage(tile[81].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;

            }

        } else if (startBattle || !wait) {

            switch (carloMoneyA/1000) {
                case 1:
                    g2.drawImage(tile[81].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, bSize*2, bSize*2, bSize, bSize*2, null);
                    break;
            }

            switch ((carloMoneyA/100)%10) {

                case 1:
                    g2.drawImage(tile[81].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, bSize*3, bSize*2, bSize, bSize*2, null);
                    break;

            }

            switch ((carloMoneyA/10)%10) {

                case 1:
                    g2.drawImage(tile[81].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, bSize*4, bSize*2, bSize, bSize*2, null);
                    break;

            }

            switch (carloMoneyA%10) {

                case 1:
                    g2.drawImage(tile[81].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 2:
                    g2.drawImage(tile[82].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 3:
                    g2.drawImage(tile[83].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 4:
                    g2.drawImage(tile[84].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 5:
                    g2.drawImage(tile[85].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 6:
                    g2.drawImage(tile[86].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 7:
                    g2.drawImage(tile[87].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 8:
                    g2.drawImage(tile[88].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 9:
                    g2.drawImage(tile[89].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;
                case 0:
                    g2.drawImage(tile[90].image, bSize*5, bSize*2, bSize, bSize*2, null);
                    break;

            }

        }

        if (!startBattle && wait) {
            g2.drawImage(tile[91].image, gp.screenWidth/2 - 310, 0, bSize * 8, bSize*5, null);
        } else if (startBattle || !wait) {
            g2.drawImage(tile[92].image, gp.screenWidth/2 - 175, 0, bSize * 5, bSize*3, null);
            if (CarloWon && !PabloWon) {
                if (tenSecsPassed) {
                    g2.drawImage(tile[68].image, gp.screenWidth / 2 - bSize * 2, bSize * 3, bSize * 2, bSize, null);
                    g2.drawImage(tile[93].image, gp.screenWidth / 2 - bSize * 3 / 2 + bSize * 2, bSize * 3, bSize * 2, bSize, null);
                }
            } else if (!CarloWon && PabloWon) {
                if (tenSecsPassed) {
                    g2.drawImage(tile[67].image, gp.screenWidth / 2 - bSize * 2, bSize * 3, bSize * 2, bSize, null);
                    g2.drawImage(tile[93].image, gp.screenWidth / 2 - bSize * 3 / 2 + bSize * 2, bSize * 3, bSize * 2, bSize, null);
                }
            }
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

        if (turn1) {
            g2.drawImage(tile[68].image, gp.screenWidth/2 - 60, bSize * 7/2, bSize * 2, bSize, null);
        } else if (turn2) {
            g2.drawImage(tile[67].image, gp.screenWidth/2 - 60, bSize * 7/2, bSize * 2, bSize, null);
        }

        switch (gp.remainingTreasures) {
            case 1:
                g2.drawImage(tile[81].image, gp.screenWidth/2 , bSize * 10-30, bSize/2, bSize, null);
                break;
            case 2:
                g2.drawImage(tile[82].image, gp.screenWidth/2 , bSize * 10-30, bSize/2, bSize, null);
                break;
            case 3:
                g2.drawImage(tile[83].image, gp.screenWidth/2 , bSize * 10-30, bSize/2, bSize, null);
                break;
            case 4:
                g2.drawImage(tile[84].image, gp.screenWidth/2, bSize * 10-30, bSize/2, bSize, null);
                break;
            case 5:
                g2.drawImage(tile[85].image, gp.screenWidth/2, bSize * 10-30, bSize/2, bSize, null);
                break;
            case 6:
                g2.drawImage(tile[86].image, gp.screenWidth/2, bSize * 10-30, bSize/2, bSize, null);
                break;
            case 7:
                g2.drawImage(tile[87].image, gp.screenWidth/2, bSize * 10-30, bSize/2, bSize, null);
                break;
            case 8:
                g2.drawImage(tile[88].image, gp.screenWidth/2, bSize * 10-30, bSize/2, bSize, null);
                break;
            case 9:
                g2.drawImage(tile[89].image, gp.screenWidth/2, bSize * 10-30, bSize/2, bSize, null);
                break;
            case 0:
                g2.drawImage(tile[90].image, gp.screenWidth/2, bSize * 10-30, bSize/2, bSize, null);
                break;
        }

        switch (randT) {
            case 1:
                tt = 71;
                break;
            case 2:
                tt = 72;
                break;
            case 3:
                tt = 73;
                break;
            case 4:
                tt = 74;
                break;
            case 5:
                tt = 75;
                break;
            case 6:
                tt = 76;
                break;
            case 7:
                tt = 77;
                break;
            case 8:
                tt = 78;
                break;
        }

        g2.drawImage(tile[tt].image, gp.screenWidth/2-bSize*2-30, bSize * 13/2, bSize*5, bSize, null);

        g2.drawImage(tile[79].image, 0, bSize*3, bSize*2, bSize*2, null);

        switch (gp.carloMoney/1000) {

            case 1:
                g2.drawImage(tile[81].image, bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 2:
                g2.drawImage(tile[82].image, bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 3:
                g2.drawImage(tile[83].image, bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 4:
                g2.drawImage(tile[84].image, bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 5:
                g2.drawImage(tile[85].image, bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 6:
                g2.drawImage(tile[86].image, bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 7:
                g2.drawImage(tile[87].image, bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 8:
                g2.drawImage(tile[88].image, bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 9:
                g2.drawImage(tile[89].image, bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 0:
                g2.drawImage(tile[90].image, bSize*2, bSize*3, bSize, bSize*2, null);
                break;
        }

        switch ((gp.carloMoney/100)%10) {

            case 1:
                g2.drawImage(tile[81].image, bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 2:
                g2.drawImage(tile[82].image, bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 3:
                g2.drawImage(tile[83].image, bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 4:
                g2.drawImage(tile[84].image, bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 5:
                g2.drawImage(tile[85].image, bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 6:
                g2.drawImage(tile[86].image, bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 7:
                g2.drawImage(tile[87].image, bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 8:
                g2.drawImage(tile[88].image, bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 9:
                g2.drawImage(tile[89].image, bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 0:
                g2.drawImage(tile[90].image, bSize*3, bSize*3, bSize, bSize*2, null);
                break;

        }

        switch ((gp.carloMoney/10)%10) {

            case 1:
                g2.drawImage(tile[81].image, bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 2:
                g2.drawImage(tile[82].image, bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 3:
                g2.drawImage(tile[83].image, bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 4:
                g2.drawImage(tile[84].image, bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 5:
                g2.drawImage(tile[85].image, bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 6:
                g2.drawImage(tile[86].image, bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 7:
                g2.drawImage(tile[87].image, bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 8:
                g2.drawImage(tile[88].image, bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 9:
                g2.drawImage(tile[89].image, bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 0:
                g2.drawImage(tile[90].image, bSize*4, bSize*3, bSize, bSize*2, null);
                break;

        }

        switch (gp.carloMoney%10) {

            case 1:
                g2.drawImage(tile[81].image, bSize*5, bSize*3, bSize, bSize*2, null);
                break;
            case 2:
                g2.drawImage(tile[82].image, bSize*5, bSize*3, bSize, bSize*2, null);
                break;
            case 3:
                g2.drawImage(tile[83].image, bSize*5, bSize*3, bSize, bSize*2, null);
                break;
            case 4:
                g2.drawImage(tile[84].image, bSize*5, bSize*3, bSize, bSize*2, null);
                break;
            case 5:
                g2.drawImage(tile[85].image, bSize*5, bSize*3, bSize, bSize*2, null);
                break;
            case 6:
                g2.drawImage(tile[86].image, bSize*5, bSize*3, bSize, bSize*2, null);
                break;
            case 7:
                g2.drawImage(tile[87].image, bSize*5, bSize*3, bSize, bSize*2, null);
                break;
            case 8:
                g2.drawImage(tile[88].image, bSize*5, bSize*3, bSize, bSize*2, null);
                break;
            case 9:
                g2.drawImage(tile[89].image, bSize*5, bSize*3, bSize, bSize*2, null);
                break;
            case 0:
                g2.drawImage(tile[90].image, bSize*5, bSize*3, bSize, bSize*2, null);
                break;

        }


        g2.drawImage(tile[79].image, gp.screenWidth-140, bSize*3, bSize*2, bSize*2, null);


        switch (gp.pabloMoney/1000) {

            case 1:
                g2.drawImage(tile[81].image, (gp.screenWidth-140)-bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 2:
                g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 3:
                g2.drawImage(tile[83].image, (gp.screenWidth-140)-bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 4:
                g2.drawImage(tile[84].image, (gp.screenWidth-140)-bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 5:
                g2.drawImage(tile[85].image, (gp.screenWidth-140)-bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 6:
                g2.drawImage(tile[86].image, (gp.screenWidth-140)-bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 7:
                g2.drawImage(tile[87].image, (gp.screenWidth-140)-bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 8:
                g2.drawImage(tile[88].image, (gp.screenWidth-140)-bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 9:
                g2.drawImage(tile[89].image, (gp.screenWidth-140)-bSize*4, bSize*3, bSize, bSize*2, null);
                break;
            case 0:
                g2.drawImage(tile[90].image, (gp.screenWidth-140)-bSize*4, bSize*3, bSize, bSize*2, null);
                break;

        }

        switch ((gp.pabloMoney/100)%10) {

            case 1:
                g2.drawImage(tile[81].image, (gp.screenWidth-140)-bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 2:
                g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 3:
                g2.drawImage(tile[83].image, (gp.screenWidth-140)-bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 4:
                g2.drawImage(tile[84].image, (gp.screenWidth-140)-bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 5:
                g2.drawImage(tile[85].image, (gp.screenWidth-140)-bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 6:
                g2.drawImage(tile[86].image, (gp.screenWidth-140)-bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 7:
                g2.drawImage(tile[87].image, (gp.screenWidth-140)-bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 8:
                g2.drawImage(tile[88].image, (gp.screenWidth-140)-bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 9:
                g2.drawImage(tile[89].image, (gp.screenWidth-140)-bSize*3, bSize*3, bSize, bSize*2, null);
                break;
            case 0:
                g2.drawImage(tile[90].image, (gp.screenWidth-140)-bSize*3, bSize*3, bSize, bSize*2, null);
                break;

        }

        switch ((gp.pabloMoney/10)%10) {

            case 1:
                g2.drawImage(tile[81].image, (gp.screenWidth-140)-bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 2:
                g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 3:
                g2.drawImage(tile[83].image, (gp.screenWidth-140)-bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 4:
                g2.drawImage(tile[84].image, (gp.screenWidth-140)-bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 5:
                g2.drawImage(tile[85].image, (gp.screenWidth-140)-bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 6:
                g2.drawImage(tile[86].image, (gp.screenWidth-140)-bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 7:
                g2.drawImage(tile[87].image, (gp.screenWidth-140)-bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 8:
                g2.drawImage(tile[88].image, (gp.screenWidth-140)-bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 9:
                g2.drawImage(tile[89].image, (gp.screenWidth-140)-bSize*2, bSize*3, bSize, bSize*2, null);
                break;
            case 0:
                g2.drawImage(tile[90].image, (gp.screenWidth-140)-bSize*2, bSize*3, bSize, bSize*2, null);
                break;

        }

        switch (gp.pabloMoney%10) {

            case 1:
                g2.drawImage(tile[81].image, (gp.screenWidth-140)-bSize, bSize*3, bSize, bSize*2, null);
                break;
            case 2:
                g2.drawImage(tile[82].image, (gp.screenWidth-140)-bSize, bSize*3, bSize, bSize*2, null);
                break;
            case 3:
                g2.drawImage(tile[83].image, (gp.screenWidth-140)-bSize, bSize*3, bSize, bSize*2, null);
                break;
            case 4:
                g2.drawImage(tile[84].image, (gp.screenWidth-140)-bSize, bSize*3, bSize, bSize*2, null);
                break;
            case 5:
                g2.drawImage(tile[85].image, (gp.screenWidth-140)-bSize, bSize*3, bSize, bSize*2, null);
                break;
            case 6:
                g2.drawImage(tile[86].image, (gp.screenWidth-140)-bSize, bSize*3, bSize, bSize*2, null);
                break;
            case 7:
                g2.drawImage(tile[87].image, (gp.screenWidth-140)-bSize, bSize*3, bSize, bSize*2, null);
                break;
            case 8:
                g2.drawImage(tile[88].image, (gp.screenWidth-140)-bSize, bSize*3, bSize, bSize*2, null);
                break;
            case 9:
                g2.drawImage(tile[89].image, (gp.screenWidth-140)-bSize, bSize*3, bSize, bSize*2, null);
                break;
            case 0:
                g2.drawImage(tile[90].image, (gp.screenWidth-140)-bSize, bSize*3, bSize, bSize*2, null);
                break;

        }





        g2.drawImage(tile[80].image, bSize/8, bSize*6, bSize*3/2, bSize*3/2, null);

        switch (gp.carloPower) {
            case 1:
                g2.drawImage(tile[81].image, bSize*2+bSize/8, bSize*6-20, bSize, bSize*2, null);
                break;
            case 2:
                g2.drawImage(tile[82].image, bSize*2+bSize/8, bSize*6-20, bSize, bSize*2, null);
                break;
            case 3:
                g2.drawImage(tile[83].image, bSize*2+bSize/8, bSize*6-20, bSize, bSize*2, null);
                break;
            case 4:
                g2.drawImage(tile[84].image, bSize*2+bSize/8, bSize*6-20, bSize, bSize*2, null);
                break;
            case 5:
                g2.drawImage(tile[85].image, bSize*2+bSize/8, bSize*6-20, bSize, bSize*2, null);
                break;
            case 6:
                g2.drawImage(tile[86].image, bSize*2+bSize/8, bSize*6-20, bSize, bSize*2, null);
                break;
            case 7:
                g2.drawImage(tile[87].image, bSize*2+bSize/8, bSize*6-20, bSize, bSize*2, null);
                break;
            case 8:
                g2.drawImage(tile[88].image, bSize*2+bSize/8, bSize*6-20, bSize, bSize*2, null);
                break;
            case 9:
                g2.drawImage(tile[89].image, bSize*2+bSize/8, bSize*6-20, bSize, bSize*2, null);
                break;
            case 0:
                g2.drawImage(tile[90].image, bSize*2+bSize/8, bSize*6-20, bSize, bSize*2, null);
                break;
        }

        g2.drawImage(tile[80].image, gp.screenWidth-bSize*3/2, bSize*6, bSize*3/2, bSize*3/2, null);

        switch (gp.pabloPower) {
            case 1:
                g2.drawImage(tile[81].image,(gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*6-20, bSize, bSize*2, null);
                break;
            case 2:
                g2.drawImage(tile[82].image,(gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*6-20, bSize, bSize*2, null);
                break;
            case 3:
                g2.drawImage(tile[83].image,(gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*6-20, bSize, bSize*2, null);
                break;
            case 4:
                g2.drawImage(tile[84].image,(gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*6-20, bSize, bSize*2, null);
                break;
            case 5:
                g2.drawImage(tile[85].image,(gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*6-20, bSize, bSize*2, null);
                break;
            case 6:
                g2.drawImage(tile[86].image,(gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*6-20, bSize, bSize*2, null);
                break;
            case 7:
                g2.drawImage(tile[87].image,(gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*6-20, bSize, bSize*2, null);
                break;
            case 8:
                g2.drawImage(tile[88].image,(gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*6-20, bSize, bSize*2, null);
                break;
            case 9:
                g2.drawImage(tile[89].image,(gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*6-20, bSize, bSize*2, null);
                break;
            case 0:
                g2.drawImage(tile[90].image,(gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*6-20, bSize, bSize*2, null);
                break;
        }


        g2.drawImage(tile[20].image, bSize/4, bSize*9, bSize, bSize, null);

        switch (gp.carloPoints) {
            case 1:
                g2.drawImage(tile[81].image, bSize*2+bSize/8, bSize*9-20, bSize, bSize*2, null);
                break;
            case 2:
                g2.drawImage(tile[82].image, bSize*2+bSize/8, bSize*9-20, bSize, bSize*2, null);
                break;
            case 3:
                g2.drawImage(tile[83].image, bSize*2+bSize/8, bSize*9-20, bSize, bSize*2, null);
                break;
            case 4:
                g2.drawImage(tile[84].image, bSize*2+bSize/8, bSize*9-20, bSize, bSize*2, null);
                break;
            case 5:
                g2.drawImage(tile[85].image, bSize*2+bSize/8, bSize*9-20, bSize, bSize*2, null);
                break;
            case 6:
                g2.drawImage(tile[86].image, bSize*2+bSize/8, bSize*9-20, bSize, bSize*2, null);
                break;
            case 7:
                g2.drawImage(tile[87].image, bSize*2+bSize/8, bSize*9-20, bSize, bSize*2, null);
                break;
            case 8:
                g2.drawImage(tile[88].image, bSize*2+bSize/8, bSize*9-20, bSize, bSize*2, null);
                break;
            case 9:
                g2.drawImage(tile[89].image, bSize*2+bSize/8, bSize*9-20, bSize, bSize*2, null);
                break;
            case 0:
                g2.drawImage(tile[90].image, bSize*2+bSize/8, bSize*9-20, bSize, bSize*2, null);
                break;
        }




        g2.drawImage(tile[20].image, gp.screenWidth-100, bSize*9, bSize, bSize, null);


        switch (gp.pabloPoints) {
            case 1:
                g2.drawImage(tile[81].image, (gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*9-20, bSize, bSize*2, null);
                break;
            case 2:
                g2.drawImage(tile[82].image, (gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*9-20, bSize, bSize*2, null);
                break;
            case 3:
                g2.drawImage(tile[83].image, (gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*9-20, bSize, bSize*2, null);
                break;
            case 4:
                g2.drawImage(tile[84].image, (gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*9-20, bSize, bSize*2, null);
                break;
            case 5:
                g2.drawImage(tile[85].image, (gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*9-20, bSize, bSize*2, null);
                break;
            case 6:
                g2.drawImage(tile[86].image, (gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*9-20, bSize, bSize*2, null);
                break;
            case 7:
                g2.drawImage(tile[87].image, (gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*9-20, bSize, bSize*2, null);
                break;
            case 8:
                g2.drawImage(tile[88].image, (gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*9-20, bSize, bSize*2, null);
                break;
            case 9:
                g2.drawImage(tile[89].image, (gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*9-20, bSize, bSize*2, null);
                break;
            case 0:
                g2.drawImage(tile[90].image, (gp.screenWidth-bSize*3/2)-bSize*3/2, bSize*9-20, bSize, bSize*2, null);
                break;
        }


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

        if (!darw) {
            g2.drawImage(tile[ss].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
            if (select) {
                ss = 115;
            }
            darw = true;
        }
        g2.drawImage(tile[41].image, 0, gp.screenHeight / 2, bSize * 8, bSize * 12, null);
        g2.drawImage(tile[70].image, bSize * 10, (gp.screenHeight/2)-bSize*2, bSize * 12, bSize * 12, null);

    }

    public void drawEndScreen () {

        // Get the current image from the array based on currentImageIndex
        BufferedImage currentImage = gif[currentImageIndex];

        // Draw the current image onto the screen
        g2.drawImage(currentImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(tile[51].image, bSize*4, bSize, bSize * 8, bSize * 3, null);
        if (gp.carloWonGame) {
            g2.drawImage(tile[113].image, 0, gp.screenHeight / 2, bSize * 8, bSize * 12, null);
            g2.drawImage(tile[68].image, gp.screenWidth / 2 + bSize * 2, bSize * 6, bSize * 2, bSize, null);
            g2.drawImage(tile[93].image, gp.screenWidth / 2 - bSize * 3 / 2 + bSize * 6, bSize * 6, bSize * 2, bSize, null);
        } else if (gp.pabloWonGame) {
            g2.drawImage(tile[112].image, 0, gp.screenHeight / 2, bSize * 8, bSize * 12, null);
            g2.drawImage(tile[67].image, gp.screenWidth / 2 + bSize * 2, bSize * 6, bSize * 2, bSize, null);
            g2.drawImage(tile[93].image, gp.screenWidth / 2 - bSize * 3 / 2 + bSize * 6, bSize * 6, bSize * 2, bSize, null);
        }



        // Increment the image index for the next frame
        currentImageIndex++;

        // If we reached the end of the array, loop back to the beginning
        if (currentImageIndex >= gif.length) {
            currentImageIndex = 0;
        }

    }

}
