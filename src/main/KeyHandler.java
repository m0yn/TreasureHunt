package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static entity.Entity.*;


// KeyListener interface to retrieve keyboard events.
public class KeyHandler implements KeyListener {

    // Boolean variables to check whether buttons are pressed.
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // If-statement to tell the character what to do depending on button pressed.
        if (e.getKeyCode() == KeyEvent.VK_W) {

            upPressed = true;
            y -= square;
            direction = "up";
            spriteCounter++;

        }

        else if (e.getKeyCode() == KeyEvent.VK_S) {

            downPressed = true;
            y += square;
            direction = "down";
            spriteCounter++;

        }

        else if (e.getKeyCode() == KeyEvent.VK_A) {

            leftPressed = true;
            x -= square;
            direction = "left";
            spriteCounter++;

        }

        else if (e.getKeyCode() == KeyEvent.VK_D) {

            rightPressed = true;
            x += square;
            direction = "right";
            spriteCounter++;

        }

        // If-statement used to shuffle between right and left leg animations.
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

    @Override
    public void keyReleased(KeyEvent e) {

        // Switch-statement to suspends movement when the key is released.
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
