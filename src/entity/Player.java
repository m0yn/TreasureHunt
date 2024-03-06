package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static tile.TileManager.tSize;


public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;


    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        square = 16;
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

        g2.drawImage(image, x, y, tSize, tSize, null);

    }

}
