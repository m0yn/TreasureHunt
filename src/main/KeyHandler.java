package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static entity.Entity.*;
import static entity.Player.squares;
import static main.CollisionChecker.gp;
import entity.Player;


// KeyListener interface to retrieve keyboard events.
public class KeyHandler implements KeyListener {


    // Boolean variables to check whether buttons are pressed.
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    // This method is called when a key is typed, but it is not used in this implementation.
    @Override
    public void keyTyped(KeyEvent e) {
    }

    // This method is called when a key is pressed.
    @Override
    public void keyPressed(KeyEvent e) {

        // If-statement used to shuffle between right and left leg animations.
        if (e.getKeyCode() == KeyEvent.VK_W) {

            upPressed = true;
            WorldY -= speed;
            direction = "up";
            spriteCounter++;
            squares -= speed;


        }

        else if (e.getKeyCode() == KeyEvent.VK_S) {

            downPressed = true;
            WorldY += speed;
            direction = "down";
            spriteCounter++;
            squares -= speed;


        }

        else if (e.getKeyCode() == KeyEvent.VK_A) {

            leftPressed = true;
            WorldX -= speed;
            direction = "left";
            spriteCounter++;
            squares -= speed;

        }

        else if (e.getKeyCode() == KeyEvent.VK_D) {

            rightPressed = true;
            WorldX += speed;
            direction = "right";
            spriteCounter++;
            squares -= speed;

        }


        if (spriteCounter > 0) {

            if (spriteNum ==1) {

                spriteNum = 2;

            }

            else if (spriteNum == 2) {

                spriteNum = 1;

            }

            spriteCounter = 0;

        }

    }

    // This method is called when a key is released.
    @Override
    public void keyReleased(KeyEvent e) {

        // Switch-statement to suspend movement when the key is released.
        switch (e.getKeyCode()) {

            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;

        }

    }

}
