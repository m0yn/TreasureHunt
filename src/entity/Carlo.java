package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static entity.Pablo.dice2;
import static entity.Pablo.squares2;
import static main.UI.randT;
import static tile.TileManager.bSize;

public class Carlo extends Entity {

    // Player class for handling player actions and attributes

    GamePanel gp;  // Game panel reference
    KeyHandler keyH;  // Key handler reference
    public static Dice dice = new Dice();  // Dice object for rolling
    public static int squares = dice.roll() * 80;  // Squares for player movement

    // Player constructor
    public Carlo(GamePanel gp, KeyHandler keyH, int points, double money, double power) {
        this.gp = gp;
        this.keyH = keyH;
        this.points = points;
        this.money = money;
        this.power = power;
        setDefaultValues();  // Set default values for player
        getPlayerImage();  // Get player images
        solidArea = new Rectangle(0, 0, bSize, bSize);  // Set solid area for collision
    }

    // Set default values for player
    public void setDefaultValues() {
        WorldX = 0;
        WorldY = bSize;
        direction = "right";
    }

    // Get player images
    public void getPlayerImage() {
        try {
            // Load player images for different directions
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

    // Update player state
    public void update() {
        collisionOn = false;  // Set collision flag
        gp.cChecker.checkTile(this);  // Check collision with tiles

        if (turn1 == true) {  // If it's player's turn

            if (collisionOn) {  // If collision detected
                turn2 = false;
                speed = 0;

                // Adjust player position based on collision and direction
                if (WorldX % 80 != 0 && WorldY % 80 == 0 && direction == "left") {
                    WorldX = ((WorldX / 80) + 1) * 80;
                    squares += 16;
                } else if (WorldX % 80 != 0 && WorldY % 80 == 0 && direction == "right") {
                    WorldX = (WorldX / 80) * 80;
                    squares += 16;
                }

                if (WorldY % 80 != 0 && WorldX % 80 == 0 && direction == "up") {
                    // Round up WorldY to the nearest multiple of 80
                    WorldY = ((WorldY / 80) + 1) * 80;
                    squares += 16;
                } else if (WorldY % 80 != 0 && WorldX % 80 == 0 && direction == "down") {
                    WorldY = (WorldY / 80) * 80;
                    squares += 16;
                }

                if (WorldY % 80 != 0 && WorldX % 80 != 0 && direction == "up") {
                    WorldY = ((WorldY / 80) + 1) * 80;
                    squares += 16;
                } else if (WorldY % 80 != 0 && WorldX % 80 != 0 && direction == "down") {
                    WorldY = (WorldY / 80) * 80;
                    squares += 16;
                } else if (WorldY % 80 != 0 && WorldX % 80 != 0 && direction == "right") {
                    WorldX = (WorldX / 80) * 80;
                    squares += 16;
                } else if (WorldY % 80 != 0 && WorldX % 80 != 0 && direction == "left") {
                    WorldX = ((WorldX / 80) + 1) * 80;
                    squares += 16;
                }

            } else {
                turn2 = false;
                speed = 16;  // Set movement speed
            }

            if (squares == 0) {  // If movement is completed
                speed = 0;  // Stop movement

                // Get current tile position
                int tileX = (WorldX + bSize / 2) / bSize;
                int tileY = (WorldY + bSize / 2) / bSize;

                // Set player position to center of the current tile
                WorldX = tileX * bSize;
                WorldY = tileY * bSize;

                turn2 = true;  // Set next player's turn
                squares2 = dice2.roll() * 80;  // Roll dice for next player movement

            } else if (squares == (dice.result*80)-16) {
                System.out.println("works!!");
                gp.carloCastle = false;
                gp.carloMarket = false;

                if (gp.tre1 && !gp.tre1c) {
                    gp.tre1 = false;
                } else if (gp.tre2 && !gp.tre2c) {
                    gp.tre2 = false;
                } else if (gp.tre3 && !gp.tre3c) {
                    gp.tre3 = false;
                } else if (gp.tre4 && !gp.tre4c) {
                    gp.tre4 = false;
                } else if (gp.tre5 && !gp.tre5c) {
                    gp.tre5 = false;
                } else if (gp.tre6 && !gp.tre6c) {
                    gp.tre6 = false;
                } else if (gp.tre7 && !gp.tre7c) {
                    gp.tre7 = false;
                } else if (gp.tre8 && !gp.tre8c) {
                    gp.tre8 = false;
                }

            }
        }


    }

    // Draw player on screen
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // Set player image based on direction and sprite number
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

        g2.drawImage(image, WorldX, WorldY, bSize, bSize, null);  // Draw player on screen
    }
}
