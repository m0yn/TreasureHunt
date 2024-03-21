package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    public static final int GRID = 12;
    GamePanel gp;
    public static Tile[] tile; // Array for tile assets.
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
        tile = new Tile[50];
        obsTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadObs();

    }

    // Assets for game objects.
    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/blank.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall2.png"));
            tile[1].collision = true;


            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick2.png"));
            tile[2].collision = true;


            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/carpet1.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt1.png"));


            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));

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
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick1.png"));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water1.png"));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water2.png"));

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/items/chestC.png"));

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/items/shop1.png"));

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/items/castle3.png"));

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/items/trap1.png"));

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/items/trap2.png"));

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/items/trap3.png"));

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/items/shop2.png"));

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/items/shop3.png"));

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/items/castle2.png"));

            tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/items/castle.png"));

            tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));

            tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass3.png"));

            tile[32] = new Tile();
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("/items/chestC2.png"));

            tile[33] = new Tile();
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("/items/chestC3.png"));

            tile[34] = new Tile();
            tile[34].image = ImageIO.read(getClass().getResourceAsStream("/items/chestC4.png"));

            tile[35] = new Tile();
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("/items/house1.png"));

            tile[36] = new Tile();
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("/items/house2.png"));

            tile[37] = new Tile();
            tile[37].image = ImageIO.read(getClass().getResourceAsStream("/items/house3.png"));

            tile[38] = new Tile();
            tile[38].image = ImageIO.read(getClass().getResourceAsStream("/items/house4.png"));

            tile[39] = new Tile();
            tile[39].image = ImageIO.read(getClass().getResourceAsStream("/items/house5.png"));

            tile[40] = new Tile();
            tile[40].image = ImageIO.read(getClass().getResourceAsStream("/items/house6.png"));

            tile[41] = new Tile();
            tile[41].image = ImageIO.read(getClass().getResourceAsStream("/players/CarloR1.png"));

            tile[42] = new Tile();
            tile[42].image = ImageIO.read(getClass().getResourceAsStream("/players/CarlosL1.png"));

            tile[43] = new Tile();
            tile[43].image = ImageIO.read(getClass().getResourceAsStream("/bars/powerBar.png"));

            tile[44] = new Tile();
            tile[44].image = ImageIO.read(getClass().getResourceAsStream("/bars/healthBar.png"));


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

                while(col < GRID) {

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

        for (xT = 1; xT <=9; xT += 2) {
            for ( yT = 0; yT <= 10; yT += 2) {
                // Grass tiles
                g2.drawImage(tile[31].image, bSize*xT, bSize*yT, bSize, bSize, null);
            }

        }

        for (xT = 0; xT <=10; xT += 2) {
            for ( yT = 1; yT <= 9; yT += 2) {
                // Grass tiles
                g2.drawImage(tile[31].image, bSize*xT, bSize*yT, bSize, bSize, null);
            }

        }

        for (xT = 0; xT <=10; xT += 2) {
            for ( yT = 0; yT <= 10; yT += 2) {
                // Grass tiles
                g2.drawImage(tile[30].image, bSize*xT, bSize*yT, bSize, bSize, null);
            }

        }

        for (xT = 1; xT <=11; xT += 2) {
            for ( yT = 1; yT <= 11; yT += 2) {
                // Grass tiles
                g2.drawImage(tile[30].image, bSize*xT, bSize*yT, bSize, bSize, null);
            }

        }


        // Loop to draw tile elements on screen.
        for ( xT = 0; xT <= 11; xT++) {
            for ( yT = 0; yT <= 11; yT++) {

                // Outside of bounds tiles
                g2.drawImage(tile[7].image, bSize*(xT+11), bSize*yT, bSize, bSize, null);
                g2.drawImage(tile[7].image, bSize*xT, bSize*(yT+11), bSize, bSize, null);
                g2.drawImage(tile[7].image, bSize*(xT+11), bSize*(yT+11), bSize, bSize, null);

            }
        }


        // Treasures
        g2.drawImage(tile[32].image, bSize*3+iAdj, bSize*2+iAdj, iSize, iSize, null);
        g2.drawImage(tile[33].image, bSize*8+iAdj, bSize*2+iAdj, iSize, iSize, null);
        g2.drawImage(tile[34].image, bSize*6+iAdj, bSize*4+iAdj, iSize, iSize, null);
        g2.drawImage(tile[20].image, bSize*9+iAdj, bSize*5+iAdj, iSize, iSize, null);
        g2.drawImage(tile[20].image, bSize*2+iAdj, bSize*6+iAdj, iSize, iSize, null);
        g2.drawImage(tile[34].image, bSize*6+iAdj, bSize*7+iAdj, iSize, iSize, null);
        g2.drawImage(tile[33].image, bSize*3+iAdj, bSize*9+iAdj, iSize, iSize, null);
        g2.drawImage(tile[32].image, bSize*9+iAdj, bSize*9+iAdj, iSize, iSize, null);

        // Markets
        g2.drawImage(tile[26].image, bSize*4+iAdj, bSize+iAdj, iSize, iSize, null);
        g2.drawImage(tile[27].image, bSize*6+iAdj, bSize*3+iAdj, iSize, iSize, null);
        g2.drawImage(tile[21].image, bSize+iAdj, bSize*7+iAdj, iSize, iSize, null);
        g2.drawImage(tile[27].image, bSize*8+iAdj, bSize*8+iAdj, iSize, iSize, null);
        g2.drawImage(tile[26].image, bSize*4+iAdj, bSize*10+iAdj, iSize, iSize, null);

        // Castle
        g2.drawImage(tile[29].image, bSize*6-20, bSize*5-20, bSize+20, bSize+30, null);

        // Traps
        g2.drawImage(tile[24].image, bSize*10+twAdj, bSize+twAdj, tSize, tSize, null);
        g2.drawImage(tile[25].image, bSize+twAdj, bSize*3+twAdj, tSize, tSize, null);
        g2.drawImage(tile[24].image, bSize*4+twAdj, bSize*5+twAdj, tSize, tSize, null);
        g2.drawImage(tile[25].image, bSize*5+twAdj, bSize*8+twAdj, tSize, tSize, null);
        g2.drawImage(tile[24].image, bSize*10+twAdj, bSize*10+twAdj, tSize, tSize, null);

        // Starting House
        g2.drawImage(tile[3].image, 0, bSize, bSize, bSize, null);
        g2.drawImage(tile[3].image, bSize, bSize, bSize, bSize, null);
        g2.drawImage(tile[28].image, 0, bSize-20, bSize, bSize+20, null);

        // Lost Items
        g2.drawImage(tile[38].image, bSize, bSize, bSize, bSize, null);
        g2.drawImage(tile[36].image, bSize*7, bSize, bSize, bSize, null);
        g2.drawImage(tile[37].image, bSize*9, bSize*2, bSize, bSize, null);
        g2.drawImage(tile[35].image, bSize*3, bSize*3, bSize, bSize, null);
        g2.drawImage(tile[39].image, bSize, bSize*4, bSize, bSize, null);
        g2.drawImage(tile[40].image, bSize*8, bSize*4, bSize, bSize, null);
        g2.drawImage(tile[39].image, bSize*5, bSize*6, bSize, bSize, null);
        g2.drawImage(tile[40].image, bSize*10, bSize*6, bSize, bSize, null);
        g2.drawImage(tile[39].image, bSize*8, bSize*7, bSize, bSize, null);
        g2.drawImage(tile[35].image, bSize*2, bSize*8, bSize, bSize, null);
        g2.drawImage(tile[37].image, bSize*2, bSize*9, bSize, bSize, null);
        g2.drawImage(tile[36].image, bSize*7, bSize*9, bSize, bSize, null);
        g2.drawImage(tile[38].image, bSize*5, bSize*10, bSize, bSize, null);

        while (col < GRID && row < GRID) {

            int obsNum = obsTileNum[col][row];

            g2.drawImage(tile[obsNum].image, x, y, bSize, bSize, null);

            col++;
            x += bSize;

            if (col == GRID) {

                col = 0;
                x = 0;
                row++;
                y += bSize;

            }

        }

    }

}
