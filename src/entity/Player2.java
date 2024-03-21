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

// Class Player2 extends Entity, representing a player character in the game with its unique attributes and methods
public class Player2 extends Entity {

    GamePanel gp;
    KeyHandler KeyH;

    // Static Dice instance and square tracker for player 2
    public static Dice dice2 = new Dice();
    public static int squares2 = 0;

    // Constructor for Player2 with game panel, key handler, and initial stats
    public Player2(GamePanel gp, KeyHandler KeyH, double health, double money, double power) {
        this.gp = gp;
        this.KeyH = KeyH;
        this.health = health;
        this.money = money;
        this.power = power;
        setDefaultValues2();
        getPlayerImage2();
        solidArea = new Rectangle(0, 0, bSize, bSize);
    }

    // Sets default starting values for player 2
    public void setDefaultValues2() {
        WorldX2 = 0;
        WorldY2 = bSize;
        direction2 = "right";
    }

    // Loads the image assets for player 2's animations
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

    // Updates player 2's state for each game tick
    public void update2() {
        if (turn2 == true) {
            collisionOn2 = false;
            gp.cChecker2.checkTile2(this);

            // Adjusts player 2's position and speed after collision detection
            if (collisionOn2) {
                turn1 = false;
                speed2 = 0;
                // Various conditional blocks to adjust player position and progress after collisions
            } else {
                turn1 = false;
                speed2 = 16;
            }

            // Resets player position and speed based on dice roll if squares2 reaches 0
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

    // Renders player 2 on the game panel
    public void draw2(Graphics2D g2) {
        BufferedImage image = null;
        // Selects the correct animation frame based on direction and sprite number
        switch(direction2) {
            case "up":
                image = spriteNum2 == 1 ? xup1 : xup2;
                break;
            case "down":
                image = spriteNum2 == 1 ? xdown1 : xdown2;
                break;
            case "left":
                image = spriteNum2 == 1 ? xleft1 : xleft2;
                break;
            case "right":
                image = spriteNum2 == 1 ? xright1 : xright2;
                break;
        }
        // Draws the selected image on the game panel
        g2.drawImage(image, WorldX2, WorldY2, bSize, bSize, null);
    }

}
