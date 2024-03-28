package Objects;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public boolean collision = false;
    public String name;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle (0, 0,64,64);
    public int solidAreaDX=0;
    public int solidAreaDY=0;


    public void draw (Graphics2D g2, GamePanel gp) {
        int screenX = worldX;
        int screenY = worldY;
        // Draw the object at its position
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
