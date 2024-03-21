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


    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished;
    int[][] battleNum;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public static String text2;
    String text = "IT'S ON!";

    public UI (GamePanel gp) {

        this.gp =gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if (gp.gameState == gp.battleState) {

            drawPauseScreen();

        }

    }

    public void drawPauseScreen() {

        for (xT = 0; xT <=20; xT++) {
            for ( yT = 0; yT <= 20; yT++) {
                // Grass tiles
                g2.drawImage(tile[31].image, bSize*xT, bSize*yT, bSize, bSize, null);
            }
        }

        g2.drawImage(tile[41].image, 0,gp.screenHeight/2, bSize*8, bSize*12, null);
        g2.drawImage(tile[42].image, bSize*12,gp.screenHeight/2, bSize*8, bSize*12, null);

        g2.drawImage(tile[43].image, bSize,gp.screenHeight/4, bSize*6, bSize, null);
        g2.drawImage(tile[44].image, bSize,gp.screenHeight/6, bSize*6, bSize, null);

        g2.drawImage(tile[43].image, bSize*12,gp.screenHeight/4, bSize*6, bSize, null);
        g2.drawImage(tile[44].image, bSize*12,gp.screenHeight/6, bSize*6, bSize, null);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));



        g2.drawString(text, getXforCenteredText(text) ,80);
        g2.drawString(text2, getXforCenteredText(text2) ,200);

    }

    public int getXforCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;

    }

}
