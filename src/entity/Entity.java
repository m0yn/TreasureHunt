package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

// Base class for all entities in the game such as characters, enemies, items, etc.
public class Entity {

    // Static fields for global position coordinates; used for entity placement in the game world
    public static int WorldX, WorldY; // Primary entity position
    public static int WorldX2, WorldY2; // Secondary entity position for additional entities or effects

    // Static fields for entity movement speed
    public static int speed; // Speed of primary entity
    public static int speed2; // Speed of secondary entity

    // Static BufferedImage objects for animating the entity's movement in four directions
    public static BufferedImage up1, up2, down1, down2, right1, right2, left1, left2; // First set of animations
    public static BufferedImage xup1, xup2, xdown1, xdown2, xright1, xright2, xleft1, xleft2; // Extended or alternative animations

    // Static fields to keep track of the entity's current direction
    public static String direction; // Direction of primary entity
    public static String direction2; // Direction of secondary entity

    // Counter variables for managing animation frames based on speed
    public static int spriteCounter = speed; // Animation frame counter for primary entity
    public static int spriteCounter2 = speed2; // Animation frame counter for secondary entity

    // Fields for tracking the current frame of sprite animation
    public static int spriteNum = 1; // Frame counter for primary entity
    public static int spriteNum2 = 1; // Frame counter for secondary entity

    // A Rectangle object to define the physical space an entity occupies; useful for collision detection
    public Rectangle solidArea;

    // Boolean fields to track collision states
    public static boolean collisionOn; // Collision state for primary entity
    public static boolean collisionOn2; // Collision state for secondary entity

    // Boolean fields to manage entity turning or direction changes
    public static boolean turn2;
    public static boolean turn1 = true; // Initialized to true for primary entity readiness

    // Fields for entity attributes
    public double health, power, money; // Entity's health, attack power, and money or points

}
