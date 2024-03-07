package entity;

import java.awt.image.BufferedImage;

// Base class for game entities
public class Entity {

    public static int x,y; // Position coordinates
    public static int square; // Size of the entity
    public static BufferedImage up1, up2, down1, down2, right1, right2, left1, left2; // Images for different directions and animations
    public static String direction; // Current facing direction
    public static int spriteCounter = 0; // Counter to manage sprite animations
    public static int spriteNum = 1; // Current sprite frame

}
