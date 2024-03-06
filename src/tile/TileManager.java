package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile; // Array for tile assets.
    public static int tSize = 80; // Size of each tile on screen.
    int xT, yT; // Multiplier to assign tile locations on screen.

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[30];
        getTileImage();

    }

    // Assets for game tiles.
    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/box1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick1.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick2.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/carpet1.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt1.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground1.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground2.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ice1.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/lava1.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/metal1.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/metal2.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/metal3.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/metal4.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand1.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/volcanic1.png"));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall1.png"));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall2.png"));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water1.png"));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water2.png"));

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/items/chestC.png"));

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/items/shop.png"));

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/items/castle3.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void draw(Graphics2D g2) {

        // Loop to draw tile elements on screen.
        for ( xT = 0; xT <= 11; xT++) {
            for ( yT = 0; yT <= 11; yT++) {

                // Grass tiles
                g2.drawImage(tile[5].image, (tSize*xT), (tSize*yT), tSize, tSize, null);
                // Wall tiles
                g2.drawImage(tile[16].image, (tSize*xT), 0, tSize, tSize, null);
                g2.drawImage(tile[2].image, 0, (tSize*yT), tSize, tSize, null);
                g2.drawImage(tile[16].image, tSize*11, (tSize*yT), tSize, tSize, null);
                g2.drawImage(tile[2].image, (tSize*xT), tSize*11, tSize, tSize, null);
                // Outside of bounds tiles
                g2.drawImage(tile[7].image, tSize*(xT+11), tSize*yT, tSize, tSize, null);
                g2.drawImage(tile[7].image, tSize*xT, tSize*(yT+11), tSize, tSize, null);
                g2.drawImage(tile[7].image, tSize*(xT+11), tSize*(yT+11), tSize, tSize, null);



            }
        }

        // Walls
        g2.drawImage(tile[16].image, tSize*2, tSize, tSize, tSize, null);
        g2.drawImage(tile[2].image, tSize, tSize*5, tSize, tSize, null);
        g2.drawImage(tile[2].image, tSize, tSize*9, tSize, tSize, null);
        g2.drawImage(tile[16].image, tSize*9, tSize, tSize, tSize, null);
        g2.drawImage(tile[16].image, tSize*10, tSize*7, tSize, tSize, null);


        // Treasures
        g2.drawImage(tile[20].image, tSize*3, tSize*2, tSize, tSize, null);
        g2.drawImage(tile[20].image, tSize*8, tSize*2, tSize, tSize, null);
        g2.drawImage(tile[20].image, tSize*6, tSize*4, tSize, tSize, null);
        g2.drawImage(tile[20].image, tSize*9, tSize*5, tSize, tSize, null);
        g2.drawImage(tile[20].image, tSize*2, tSize*6, tSize, tSize, null);
        g2.drawImage(tile[20].image, tSize*6, tSize*7, tSize, tSize, null);
        g2.drawImage(tile[20].image, tSize*3, tSize*9, tSize, tSize, null);
        g2.drawImage(tile[20].image, tSize*9, tSize*9, tSize, tSize, null);

        // Markets
        g2.drawImage(tile[21].image, tSize*4, tSize, tSize, tSize, null);
        g2.drawImage(tile[21].image, tSize*6, tSize*3, tSize, tSize, null);
        g2.drawImage(tile[21].image, tSize, tSize*7, tSize, tSize, null);
        g2.drawImage(tile[21].image, tSize*8, tSize*8, tSize, tSize, null);
        g2.drawImage(tile[21].image, tSize*4, tSize*10, tSize, tSize, null);

        // Castle
        g2.drawImage(tile[22].image, tSize*6, tSize*5, tSize, tSize, null);







    }

}
