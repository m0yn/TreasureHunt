package tile;

import main.GamePanel;

import javax.swing.ImageIcon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static entity.Carlo.dice;

public class TileManager {

    public static final int GRID = 12;
    GamePanel gp;
    public static Tile[] tile; // Array for tile assets.
    public static BufferedImage[] gifImages;
    public int obsTileNum[][];
    public static int bSize = 80; // Size of each block on screen.
    public static int iSize = 70; // Size of each item on screen.
    public static int tSize = 50; // Size of each trap and weapon on screen.
    public static int iAdj = (bSize - iSize) / 2; // Center each item on its block.
    public static int twAdj = (bSize - tSize) / 2; // Center each trap or weapon on its block.
    public static int xT, yT; // Multiplier to assign tile locations on screen.

    // Constructor fot TileManager to get and assign object images to screen tiles.
    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[100];
        gifImages = new BufferedImage[4];
        obsTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadObs();

    }

    // Assets for game objects.
    public void getTileImage() {

        try {

            tile[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/blank.png")), false);
            tile[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/wall2.png")), true);
            tile[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/brick2.png")), true);
            tile[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/carpet1.png")), false);
            tile[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/dirt1.png")), false);
            tile[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png")), false);
            tile[6] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/ground1.png")), false);
            tile[7] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/ground2.png")), false);
            tile[8] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/ice1.png")), false);
            tile[9] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/lava1.png")), false);
            tile[10] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/metal1.png")), false);
            tile[11] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/metal2.png")), false);
            tile[12] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/metal3.png")), false);
            tile[13] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/metal4.png")), false);
            tile[14] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/sand1.png")), false);
            tile[15] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/volcanic1.png")), false);
            tile[16] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/wall1.png")), false);
            tile[17] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/brick1.png")), false);
            tile[18] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/water1.png")), false);
            tile[19] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/water2.png")), false);
            tile[20] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/chestC.png")), false);
            tile[21] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/shop1.png")), false);
            tile[22] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/castle3.png")), false);
            tile[23] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/trap1.png")), false);
            tile[24] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/trap2.png")), false);
            tile[25] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/trap3.png")), false);
            tile[26] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/shop2.png")), false);
            tile[27] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/shop3.png")), false);
            tile[28] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/castle2.png")), false);
            tile[29] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/castle.png")), false);
            tile[30] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png")), false);
            tile[31] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/grass3.png")), false);
            tile[32] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/chestC2.png")), false);
            tile[33] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/chestC3.png")), false);
            tile[34] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/chestC4.png")), false);
            tile[35] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/house1.png")), false);
            tile[36] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/house2.png")), false);
            tile[37] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/house3.png")), false);
            tile[38] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/house4.png")), false);
            tile[39] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/house5.png")), false);
            tile[40] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/house6.png")), false);
            tile[41] = new Tile(ImageIO.read(getClass().getResourceAsStream("/players/Carlo.png")), false);
            tile[42] = new Tile(ImageIO.read(getClass().getResourceAsStream("/players/Pablo.png")), false);
            tile[43] = new Tile(ImageIO.read(getClass().getResourceAsStream("/bars/powerBar.png")), false);
            tile[44] = new Tile(ImageIO.read(getClass().getResourceAsStream("/bars/healthBar.png")), false);
            tile[45] = new Tile(ImageIO.read(getClass().getResourceAsStream("/objects/Dice1.png")), false);
            tile[46] = new Tile(ImageIO.read(getClass().getResourceAsStream("/objects/Dice2.png")), false);
            tile[47] = new Tile(ImageIO.read(getClass().getResourceAsStream("/objects/Dice3.png")), false);
            tile[48] = new Tile(ImageIO.read(getClass().getResourceAsStream("/objects/Dice4.png")), false);
            tile[49] = new Tile(ImageIO.read(getClass().getResourceAsStream("/objects/Dice5.png")), false);
            tile[50] = new Tile(ImageIO.read(getClass().getResourceAsStream("/objects/Dice6.png")), false);
            tile[51] = new Tile(ImageIO.read(getClass().getResourceAsStream("/text/Light.png")), false);
            tile[52] = new Tile(ImageIO.read(getClass().getResourceAsStream("/backgrounds/StartScreen.png")), false);
            tile[53] = new Tile(ImageIO.read(getClass().getResourceAsStream("/backgrounds/Colorful.png")), false);
            tile[54] = new Tile(ImageIO.read(getClass().getResourceAsStream("/text/WhiteS.png")), false);
            tile[55] = new Tile(ImageIO.read(getClass().getResourceAsStream("/text/wait.png")), false);


            for (int i = 0; i < gifImages.length; i++) { // this is to draw the rain effect on screen.
                    // Load image from file
                    gifImages[i] = ImageIO.read(getClass().getResourceAsStream("/backgrounds/rain" + (i+1) + ".png"));
            }




        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void loadObs() {

        try {

            InputStream is = getClass().getResourceAsStream("/maps/obstacles.txt");
            BufferedReader br = new BufferedReader((new InputStreamReader(is)));

            int col = 0;
            int row = 0;

            while (col < GRID && row < GRID) {

                String line = br.readLine();

                while (col < GRID) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    obsTileNum[col][row] = num;
                    col++;

                }

                if (col == GRID) {

                    col = 0;
                    row++;

                }

            }

            br.close();

        } catch (Exception e) {

        }

    }

    // Function to draw elements on the screen.
    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        for (xT = 1; xT <= 9; xT += 2) {
            for (yT = 0; yT <= 10; yT += 2) {
                // Grass tiles
                g2.drawImage(tile[31].image, bSize * xT, bSize * yT, bSize, bSize, null);
            }

        }

        for (xT = 0; xT <= 10; xT += 2) {
            for (yT = 1; yT <= 9; yT += 2) {
                // Grass tiles
                g2.drawImage(tile[31].image, bSize * xT, bSize * yT, bSize, bSize, null);
            }

        }

        for (xT = 0; xT <= 10; xT += 2) {
            for (yT = 0; yT <= 10; yT += 2) {
                // Grass tiles
                g2.drawImage(tile[30].image, bSize * xT, bSize * yT, bSize, bSize, null);
            }

        }

        for (xT = 1; xT <= 11; xT += 2) {
            for (yT = 1; yT <= 11; yT += 2) {
                // Grass tiles
                g2.drawImage(tile[30].image, bSize * xT, bSize * yT, bSize, bSize, null);
            }

        }


        // Loop to draw tile elements on screen.
        for (xT = 0; xT <= 11; xT++) {
            for (yT = 0; yT <= 11; yT++) {

                // Outside of bounds tiles
                g2.drawImage(tile[7].image, bSize * (xT + 11), bSize * yT, bSize, bSize, null);
                g2.drawImage(tile[7].image, bSize * xT, bSize * (yT + 11), bSize, bSize, null);
                g2.drawImage(tile[7].image, bSize * (xT + 11), bSize * (yT + 11), bSize, bSize, null);

            }
        }


        // Treasures
        g2.drawImage(tile[32].image, bSize * 3 + iAdj, bSize * 2 + iAdj, iSize, iSize, null);
        g2.drawImage(tile[33].image, bSize * 8 + iAdj, bSize * 2 + iAdj, iSize, iSize, null);
        g2.drawImage(tile[34].image, bSize * 6 + iAdj, bSize * 4 + iAdj, iSize, iSize, null);
        g2.drawImage(tile[20].image, bSize * 9 + iAdj, bSize * 5 + iAdj, iSize, iSize, null);
        g2.drawImage(tile[20].image, bSize * 2 + iAdj, bSize * 6 + iAdj, iSize, iSize, null);
        g2.drawImage(tile[34].image, bSize * 6 + iAdj, bSize * 7 + iAdj, iSize, iSize, null);
        g2.drawImage(tile[33].image, bSize * 3 + iAdj, bSize * 9 + iAdj, iSize, iSize, null);
        g2.drawImage(tile[32].image, bSize * 9 + iAdj, bSize * 9 + iAdj, iSize, iSize, null);

        // Markets
        g2.drawImage(tile[26].image, bSize * 4 + iAdj, bSize + iAdj, iSize, iSize, null);
        g2.drawImage(tile[27].image, bSize * 6 + iAdj, bSize * 3 + iAdj, iSize, iSize, null);
        g2.drawImage(tile[21].image, bSize + iAdj, bSize * 7 + iAdj, iSize, iSize, null);
        g2.drawImage(tile[27].image, bSize * 8 + iAdj, bSize * 8 + iAdj, iSize, iSize, null);
        g2.drawImage(tile[26].image, bSize * 4 + iAdj, bSize * 10 + iAdj, iSize, iSize, null);

        // Castle
        g2.drawImage(tile[29].image, bSize * 6 - 20, bSize * 5 - 20, bSize + 20, bSize + 30, null);

        // Traps
        g2.drawImage(tile[24].image, bSize * 10 + twAdj, bSize + twAdj, tSize, tSize, null);
        g2.drawImage(tile[25].image, bSize + twAdj, bSize * 3 + twAdj, tSize, tSize, null);
        g2.drawImage(tile[24].image, bSize * 4 + twAdj, bSize * 5 + twAdj, tSize, tSize, null);
        g2.drawImage(tile[25].image, bSize * 5 + twAdj, bSize * 8 + twAdj, tSize, tSize, null);
        g2.drawImage(tile[24].image, bSize * 10 + twAdj, bSize * 10 + twAdj, tSize, tSize, null);

        // Starting House
        g2.drawImage(tile[3].image, 0, bSize, bSize, bSize, null);
        g2.drawImage(tile[3].image, bSize, bSize, bSize, bSize, null);
        g2.drawImage(tile[28].image, 0, bSize - 20, bSize, bSize + 20, null);

        // Lost Items
        g2.drawImage(tile[38].image, bSize, bSize, bSize, bSize, null);
        g2.drawImage(tile[36].image, bSize * 7, bSize, bSize, bSize, null);
        g2.drawImage(tile[37].image, bSize * 9, bSize * 2, bSize, bSize, null);
        g2.drawImage(tile[35].image, bSize * 3, bSize * 3, bSize, bSize, null);
        g2.drawImage(tile[39].image, bSize, bSize * 4, bSize, bSize, null);
        g2.drawImage(tile[40].image, bSize * 8, bSize * 4, bSize, bSize, null);
        g2.drawImage(tile[39].image, bSize * 5, bSize * 6, bSize, bSize, null);
        g2.drawImage(tile[40].image, bSize * 10, bSize * 6, bSize, bSize, null);
        g2.drawImage(tile[39].image, bSize * 8, bSize * 7, bSize, bSize, null);
        g2.drawImage(tile[35].image, bSize * 2, bSize * 8, bSize, bSize, null);
        g2.drawImage(tile[37].image, bSize * 2, bSize * 9, bSize, bSize, null);
        g2.drawImage(tile[36].image, bSize * 7, bSize * 9, bSize, bSize, null);
        g2.drawImage(tile[38].image, bSize * 5, bSize * 10, bSize, bSize, null);

        if (dice.result == 1) {
            g2.drawImage(tile[45].image, bSize * 13, 0, bSize, bSize, null);
        } else if (dice.result == 2) {
            g2.drawImage(tile[46].image, bSize * 13, 0, bSize, bSize, null);
        } else if (dice.result == 3) {
            g2.drawImage(tile[47].image, bSize * 13, 0, bSize, bSize, null);
        } else if (dice.result == 4) {
            g2.drawImage(tile[48].image, bSize * 13, 0, bSize, bSize, null);
        } else if (dice.result == 5) {
            g2.drawImage(tile[49].image, bSize * 13, 0, bSize, bSize, null);
        } else if (dice.result == 6) {
            g2.drawImage(tile[50].image, bSize * 13, 0, bSize, bSize, null);
        }

        while (col < GRID && row < GRID) {// Looping through the obstacle tile numbers array


            int obsNum = obsTileNum[col][row];// Getting the obstacle tile number

            g2.drawImage(tile[obsNum].image, x, y, bSize, bSize, null);

            col++;
            x += bSize;

            if (col == GRID) {    // Checking if the end of the row is reached


                col = 0;
                x = 0;
                row++;
                y += bSize;

            }

        }

    }
}