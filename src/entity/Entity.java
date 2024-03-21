package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

// Base class for game entities
public class Entity {

    public static int WorldX, WorldY; // Position coordinates
    public static int WorldX2, WorldY2; // Position coordinates
    public static int speed; // Size of the entity
    public static int speed2; // Size of the entity
    public static BufferedImage up1, up2, down1, down2, right1, right2, left1, left2; // Images for different directions and animations
    public static BufferedImage xup1, xup2, xdown1, xdown2, xright1, xright2, xleft1, xleft2; // Images for different directions and animations
    public static String direction; // Current facing direction
    public static String direction2; // Current facing direction
    public static int spriteCounter = speed; // Counter to manage sprite animations
    public static int spriteCounter2 = speed2; // Counter to manage sprite animations
    public static int spriteNum = 1; // Current sprite frame4
    public static int spriteNum2 = 1; // Current sprite frame
    public Rectangle solidArea;
    public static boolean collisionOn;
    public static boolean collisionOn2;
    public static boolean turn2;
    public static boolean turn1 = true;
    public int health, power, money;

}
