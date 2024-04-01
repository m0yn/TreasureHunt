package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static entity.Entity.*;
import static entity.Carlo.squares;
import static entity.Pablo.squares2;
import static main.GamePanel.*;
import static main.UI.yX;

// KeyListener interface to retrieve keyboard events.
public class KeyHandler implements KeyListener {

    // Boolean variables to check whether buttons are pressed.
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean upPressed2, downPressed2, leftPressed2, rightPressed2;
    public boolean startGame, startBoard;
    public boolean purchase;

    // This method is called when a key is typed, but it is not used in this implementation.
    @Override
    public void keyTyped(KeyEvent e) {
    }

    // This method is called when a key is pressed.
    @Override
    public void keyPressed(KeyEvent e) {

        if (gameState == playState) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    upPressed = true;
                    WorldY -= speed;
                    direction = "up";
                    spriteCounter++;
                    squares -= speed;
                    break;
                case KeyEvent.VK_S:
                    downPressed = true;
                    WorldY += speed;
                    direction = "down";
                    spriteCounter++;
                    squares -= speed;
                    break;
                case KeyEvent.VK_A:
                    leftPressed = true;
                    WorldX -= speed;
                    direction = "left";
                    spriteCounter++;
                    squares -= speed;
                    break;
                case KeyEvent.VK_D:
                    rightPressed = true;
                    WorldX += speed;
                    direction = "right";
                    spriteCounter++;
                    squares -= speed;
                    break;
                case KeyEvent.VK_UP:
                    upPressed2 = true;
                    WorldY2 -= speed2;
                    direction2 = "up";
                    spriteCounter2++;
                    squares2 -= speed2;
                    break;
                case KeyEvent.VK_DOWN:
                    downPressed2 = true;
                    WorldY2 += speed2;
                    direction2 = "down";
                    spriteCounter2++;
                    squares2 -= speed2;
                    break;
                case KeyEvent.VK_LEFT:
                    leftPressed2 = true;
                    WorldX2 -= speed2;
                    direction2 = "left";
                    spriteCounter2++;
                    squares2 -= speed2;
                    break;
                case KeyEvent.VK_RIGHT:
                    rightPressed2 = true;
                    WorldX2 += speed2;
                    direction2 = "right";
                    spriteCounter2++;
                    squares2 -= speed2;
                    break;
                case KeyEvent.VK_P:
                    startBoard = true;
                    break;
            }
        } else if (gameState == marketState) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    yX--;
                    break;
                case KeyEvent.VK_DOWN:
                    yX++;
                    break;
                case KeyEvent.VK_SPACE:
                    purchase = true;
                    break;
                case KeyEvent.VK_ENTER:
                    startGame = true;
                    break;
            }

        } else {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                startGame = true;
        }

        // Change sprite numbers based on counter
        if (spriteCounter > 0) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (spriteCounter2 > 0) {
            if (spriteNum2 == 1) {
                spriteNum2 = 2;
            } else if (spriteNum2 == 2) {
                spriteNum2 = 1;
            }
            spriteCounter2 = 0;
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
            case KeyEvent.VK_UP:
                upPressed2 = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed2 = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed2 = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed2 = false;
                break;
            case KeyEvent.VK_ENTER:
                startGame = false;
                break;
            case KeyEvent.VK_P:
                startBoard = false;
                break;
            case KeyEvent.VK_SPACE:
                purchase = false;
                break;
        }
    }
}
