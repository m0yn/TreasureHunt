package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static entity.Player.dice;
import static entity.Player.squares;
import static tile.TileManager.bSize;


public class Player2 extends Entity {

    GamePanel gp;
    KeyHandler KeyH;
    public static Dice dice2 = new Dice();
    public static int squares2 = 0;


    public Player2(GamePanel gp, KeyHandler KeyH, int health, int money, int power) {

        this.gp = gp;
        this.KeyH = KeyH;
        this.health = health;
        this.money = money;
        this.power = power;
        setDefaultValues2();
        getPlayerImage2();
        solidArea = new Rectangle(0, 0, bSize, bSize);

    }

    public void setDefaultValues2() {

        WorldX2 = 0;
        WorldY2 = bSize;
        direction2 = "right";

    }

    public void getPlayerImage2() {

        try {

            xup1 = ImageIO.read(getClass().getResourceAsStream("/players/CarlosU1.png"));
            xdown1 = ImageIO.read(getClass().getResourceAsStream("/players/CarlosD1.png"));
            xleft1 = ImageIO.read(getClass().getResourceAsStream("/players/CarlosL1.png"));
            xright1 = ImageIO.read(getClass().getResourceAsStream("/players/CarlosR1.png"));
            xup2 = ImageIO.read(getClass().getResourceAsStream("/players/CarlosU2.png"));
            xdown2 = ImageIO.read(getClass().getResourceAsStream("/players/CarlosD2.png"));
            xleft2 = ImageIO.read(getClass().getResourceAsStream("/players/CarlosL2.png"));
            xright2 = ImageIO.read(getClass().getResourceAsStream("/players/CarlosR2.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    public void update2() {

        if (turn2 == true) {
            collisionOn2 = false;
            gp.cChecker2.checkTile2(this);

            if (collisionOn2) {

                turn1 = false;
                speed2 = 0;

                if (WorldX2 % 80 != 0 && WorldY2 % 80 == 0 && direction2 == "left") {
                    WorldX2 = ((WorldX2 / 80) + 1) * 80;
                    squares2 += 16;
                } else if (WorldX2 % 80 != 0 && WorldY2 % 80 == 0 && direction2 == "right") {
                    WorldX2 = (WorldX2 / 80) * 80;
                    squares2 += 16;
                }

                if (WorldY2 % 80 != 0 && WorldX2 % 80 == 0 && direction2 == "up") {
                    // Round up WorldY2 to the nearest multiple of 80
                    WorldY2 = ((WorldY2 / 80) + 1) * 80;
                    squares2 += 16;
                } else if (WorldY2 % 80 != 0 && WorldX2 % 80 == 0 && direction2 == "down") {
                    WorldY2 = (WorldY2 / 80) * 80;
                    squares2 += 16;
                }

                if (WorldY2 % 80 != 0 && WorldX2 % 80 != 0 && direction2 == "up") {
                    WorldY2 = ((WorldY2 / 80) + 1) * 80;
                    squares2 += 16;
                } else if (WorldY2 % 80 != 0 && WorldX2 % 80 != 0 && direction2 == "down") {
                    WorldY2 = (WorldY2 / 80) * 80;
                    squares2 += 16;
                } else if (WorldY2 % 80 != 0 && WorldX2 % 80 != 0 && direction2 == "right") {
                    WorldX2 = (WorldX2 / 80) * 80;
                    squares2 += 16;
                } else if (WorldY2 % 80 != 0 && WorldX2 % 80 != 0 && direction2 == "left") {
                    WorldX2 = ((WorldX2 / 80) + 1) * 80;
                    squares2 += 16;

                }


            } else {

                turn1 = false;
                speed2 = 16;
            }

            if (squares2 == 0) {
                speed2 = 0;

                int tileX = (WorldX2 + bSize / 2) / bSize;
                int tileY = (WorldY2 + bSize / 2) / bSize;

                WorldX2 = tileX * bSize;
                WorldY2 = tileY * bSize;

                turn1 = true;

                squares = dice.roll() * 80;

            }

        }

    }

    public void draw2(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction2) {

            case "up":
                if(spriteNum2 == 1) {
                    image = xup1;
                }
                if(spriteNum2 == 2) {
                    image = xup2;
                }
                break;
            case "down":
                if(spriteNum2 == 1) {
                    image = xdown1;
                }
                if(spriteNum2 == 2) {
                    image = xdown2;
                }
                break;
            case "left":
                if(spriteNum2 == 1) {
                    image = xleft1;
                }
                if(spriteNum2 == 2) {
                    image = xleft2;
                }
                break;
            case "right":
                if(spriteNum2 == 1) {
                    image = xright1;
                }
                if(spriteNum2 == 2) {
                    image = xright2;
                }
                break;

        }

        g2.drawImage(image, WorldX2, WorldY2, bSize, bSize, null);

    }

}