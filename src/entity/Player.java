package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static main.CollisionChecker.gp;
import static tile.TileManager.bSize;


public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    static Dice dice = new Dice();
    static int diceResult = dice.roll();
    public static int squares = diceResult * 80;


    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        solidArea = new Rectangle(0, 0, bSize, bSize);

    }

    public void setDefaultValues() {

        WorldX = 0;
        WorldY = bSize;
        direction = "right";

    }

    public void getPlayerImage() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/players/CarloU1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/players/CarloD1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/players/CarloL1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/players/CarloR1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/players/CarloU2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/players/CarloD2.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/players/CarloL2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/players/CarloR2.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    public void update() {
        collisionOn = false;
        gp.cChecker.checkTile(this);

        if (collisionOn) {

            speed = 0;

            if (WorldX % 80 != 0 && WorldY % 80 == 0 && direction == "left") {
                WorldX = ((WorldX / 80) + 1) * 80;
                squares += 16;
            }
            else if (WorldX % 80 != 0 && WorldY % 80 == 0 && direction == "right") {
                WorldX = (WorldX / 80) * 80;
                squares += 16;
            }

            if (WorldY % 80 != 0 && WorldX % 80 == 0 && direction == "up") {
                // Round up WorldY to the nearest multiple of 80
                WorldY = ((WorldY / 80) + 1) * 80;
                squares += 16;
            }
            else if (WorldY % 80 != 0 && WorldX % 80 == 0 && direction == "down") {
                WorldY = (WorldY / 80) * 80;
                squares += 16;
            }

            if (WorldY % 80 != 0 && WorldX % 80 != 0 && direction == "up") {
                WorldY = ((WorldY / 80) + 1) * 80;
                squares += 16;
            }
            else if (WorldY % 80 != 0 && WorldX % 80 != 0 && direction == "down") {
                WorldY = (WorldY / 80) * 80;
                squares += 16;
            }
            else if (WorldY % 80 != 0 && WorldX % 80 != 0 && direction == "right") {
                WorldX = (WorldX / 80) * 80;
                squares += 16;
            }
            else if (WorldY % 80 != 0 && WorldX % 80 != 0 && direction == "left") {
                WorldX = ((WorldX / 80) + 1) * 80;
                squares += 16;

            }



        }
        else {
            speed = 16;
        }

        if (squares == 0) {
            speed = 0;

            int tileX = (WorldX + bSize / 2) / bSize;
            int tileY = (WorldY + bSize / 2) / bSize;

            WorldX = tileX * bSize;
            WorldY = tileY * bSize;

        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {

            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;

        }

        g2.drawImage(image, WorldX, WorldY, bSize, bSize, null);

    }

}
