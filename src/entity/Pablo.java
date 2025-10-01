package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static entity.Carlo.dice;
import static entity.Carlo.squares;
import static tile.TileManager.bSize;

// Class Player2 extends Entity, representing a player character in the game with its unique attributes and methods
public class Pablo extends Entity {

    GamePanel gp;
    KeyHandler KeyH;

    // Static Dice instance and square tracker for player 2
    public static Dice dice2 = new Dice();
    public static int squares2 = 0;

    // Constructor for Player2 with game panel, key handler, and initial stats
    public Pablo(GamePanel gp, KeyHandler KeyH, int points, double money, double power) {
        this.gp = gp;
        this.KeyH = KeyH;
        this.points = points;
        this.money = money;
        this.power = power;
        setDefaultValues2();
        getPlayerImage2();
        solidArea = new Rectangle(0, 0, bSize, bSize);
    }

    // Sets default starting values for player 2
    public void setDefaultValues2() {
        WorldX2 = bSize*11;
        WorldY2 = bSize*10;
        direction2 = "left";
    }

    // Loads the image assets for player 2's animations
    public void getPlayerImage2() {
        try {
            xup1 = ImageIO.read(getClass().getResourceAsStream("/players/PabloU1.png"));
            xdown1 = ImageIO.read(getClass().getResourceAsStream("/players/PabloD1.png"));
            xleft1 = ImageIO.read(getClass().getResourceAsStream("/players/PabloL1.png"));
            xright1 = ImageIO.read(getClass().getResourceAsStream("/players/PabloR1.png"));
            xup2 = ImageIO.read(getClass().getResourceAsStream("/players/PabloU2.png"));
            xdown2 = ImageIO.read(getClass().getResourceAsStream("/players/PabloD2.png"));
            xleft2 = ImageIO.read(getClass().getResourceAsStream("/players/PabloL2.png"));
            xright2 = ImageIO.read(getClass().getResourceAsStream("/players/PabloR2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Updates player 2's state for each game tick
    public void update2() {
        collisionOn2 = false;
        gp.cChecker2.checkTile2(this);

        if (turn2 == true) {

            // Adjusts player 2's position and speed after collision detection
            if (collisionOn2 == true || WorldX2 > 880 || WorldX2 < 0) {  // If collision detected
                turn1 = false;
                speed2 = 0;

                // Adjust player position based on collision and direction
                if (WorldX2 > 880 && direction2 == "right") {
                    WorldX2 = 880;
                    squares2 += 16;
                } else if (WorldX2 < 0 && direction2 == "left") {
                    WorldX2 = 0;
                    squares2 += 16;
                } else if (WorldX2 % 80 != 0 && WorldY2 % 80 == 0 && direction2 == "left") {
                    WorldX2 = ((WorldX2 / 80) + 1) * 80;
                    squares2 += 16;
                } else if (WorldX2 % 80 != 0 && WorldY2 % 80 == 0 && direction2 == "right") {
                    WorldX2 = (WorldX2 / 80) * 80;
                    squares2 += 16;
                }
                if (WorldY2 % 80 != 0 && WorldX2 % 80 == 0 && direction2 == "up") {
                    // Round up WorldY to the nearest multiple of 80
                    WorldY2 = ((WorldY2 / 80) + 1) * 80;
                    squares2 += 16;
                } else if (WorldY2 % 80 != 0 && WorldX2 % 80 == 0 && direction2 == "down") {
                    WorldY2 = (WorldY2 / 80) * 80;
                    squares2 += 16;
                }

                if (WorldY2 % 80 != 0 && WorldX2 % 80 != 0 && direction2 == "up") {
                    WorldY2 = ((WorldY2 / 80) + 1) * 80;
                    squares2 += 16;
                }
                else if (WorldY2 % 80 != 0 && WorldX2 % 80 != 0 && direction2 == "down") {
                    WorldY2 = (WorldY2 / 80) * 80;
                    squares2 += 16;
                }
                else if (WorldY2 % 80 != 0 && WorldX2 % 80 != 0 && direction2 == "right") {
                    WorldX2 = (WorldX2 / 80) * 80;
                    squares2 += 16;
                }
                else if (WorldY2 % 80 != 0 && WorldX2 % 80 != 0 && direction2 == "left") {
                    WorldX2 = ((WorldX2 / 80) + 1) * 80;
                    squares2 += 16;
                }

            } else {
                turn1 = false;
                speed2 = 16;
            }

            // Resets player position and speed based on dice roll if squares2 reaches 0
            if (squares2 <= 0) {

                squares2 = 0;
                speed2 = 0;
                int tileX = (WorldX2 + bSize / 2) / bSize;
                int tileY = (WorldY2 + bSize / 2) / bSize;
                WorldX2 = tileX * bSize;
                WorldY2 = tileY * bSize;
                turn1 = true;
                squares = dice.roll() * 80;

            } else if (squares2 == (dice2.result*80)-16) {
                gp.pabloCastle = false;
                gp.pabloMarket = false;

                if (gp.ptre1 && !gp.tre1c) {
                    gp.ptre1 = false;
                } else if (gp.ptre2 && !gp.tre2c) {
                    gp.ptre2 = false;
                } else if (gp.ptre3 && !gp.tre3c) {
                    gp.ptre3 = false;
                } else if (gp.ptre4 && !gp.tre4c) {
                    gp.ptre4 = false;
                } else if (gp.ptre5 && !gp.tre5c) {
                    gp.ptre5 = false;
                } else if (gp.ptre6 && !gp.tre6c) {
                    gp.ptre6 = false;
                } else if (gp.ptre7 && !gp.tre7c) {
                    gp.ptre7 = false;
                } else if (gp.ptre8 && !gp.tre8c) {
                    gp.ptre8 = false;
                }

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
