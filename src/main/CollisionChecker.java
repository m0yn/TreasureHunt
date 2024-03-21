package main;

import entity.Entity;

import static entity.Entity.speed;
import static tile.TileManager.bSize;

public class CollisionChecker {

    public static GamePanel gp;

    public CollisionChecker(GamePanel gp) {

        this.gp = gp;

    }

    public static void checkTile(Entity entity) {

        int entityLeftWorldX = entity.WorldX + entity.solidArea.x + 10;
        int entityRightWorldX = entity.WorldX + entity.solidArea.x + entity.solidArea.width - 10;
        int entityTopWorldY = entity.WorldY + entity.solidArea.y + 10;
        int entityBottomWorldY = entity.WorldY + entity.solidArea.y + entity.solidArea.height - 10;

        int entityLeftCol = entityLeftWorldX/bSize;
        int entityRightCol = entityRightWorldX/bSize;
        int entityTopRow = entityTopWorldY/bSize;
        int entityBottomRow = entityBottomWorldY/bSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {

            case "up":
                entityTopRow = (entityTopWorldY - speed)/bSize;
                tileNum1 = gp.tileM.obsTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.obsTileNum[entityRightCol][entityTopRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {

                    entity.collisionOn = true;

                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + speed)/bSize;
                tileNum1 = gp.tileM.obsTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.obsTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {

                    entity.collisionOn = true;

                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - speed)/bSize;
                tileNum1 = gp.tileM.obsTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.obsTileNum[entityLeftCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {

                    entity.collisionOn = true;

                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + speed)/bSize;
                tileNum1 = gp.tileM.obsTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.obsTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {

                    entity.collisionOn = true;

                }
                break;

        }


    }

}
