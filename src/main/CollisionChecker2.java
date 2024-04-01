package main;

import entity.Entity;

import static entity.Entity.speed2;
import static tile.TileManager.bSize;

public class CollisionChecker2 {

    // Class for checking collisions for the second entity

    public static GamePanel gp;  // Game panel reference

    // Constructor for CollisionChecker2
    public CollisionChecker2(GamePanel gp) {
        this.gp = gp;  // Initialize game panel reference
    }

    // Method for checking collisions with tiles for the second entity
    public static void checkTile2(Entity entity) {

        // Calculate the boundaries of the entity
        int entityLeftWorldX2 = entity.WorldX2 + entity.solidArea.x + 10;
        int entityRightWorldX2 = entity.WorldX2 + entity.solidArea.x + entity.solidArea.width - 10;
        int entityTopWorldY2 = entity.WorldY2 + entity.solidArea.y + 10;
        int entityBottomWorldY2 = entity.WorldY2 + entity.solidArea.y + entity.solidArea.height - 10;

        // Calculate the columns and rows occupied by the entity
        int entityLeftCol2 = entityLeftWorldX2 / bSize;
        int entityRightCol2 = entityRightWorldX2 / bSize;
        int entityTopRow2 = entityTopWorldY2 / bSize;
        int entityBottomRow2 = entityBottomWorldY2 / bSize;

        int tileNum1, tileNum2;

        // Check collision based on the direction of the entity
        switch (entity.direction2) {

            case "up":
                entityTopRow2 = (entityTopWorldY2 - speed2) / bSize;
                tileNum1 = gp.tileM.obsTileNum[entityLeftCol2][entityTopRow2];
                tileNum2 = gp.tileM.obsTileNum[entityRightCol2][entityTopRow2];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn2 = true;
                }
                break;
            case "down":
                entityBottomRow2 = (entityBottomWorldY2 + speed2) / bSize;
                tileNum1 = gp.tileM.obsTileNum[entityLeftCol2][entityBottomRow2];
                tileNum2 = gp.tileM.obsTileNum[entityRightCol2][entityBottomRow2];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn2 = true;
                }
                break;
            case "left":
                entityLeftCol2 = (entityLeftWorldX2 - speed2) / bSize;
                tileNum1 = gp.tileM.obsTileNum[entityLeftCol2][entityTopRow2];
                tileNum2 = gp.tileM.obsTileNum[entityLeftCol2][entityBottomRow2];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn2 = true;
                }
                break;
            case "right":
                entityRightCol2 = (entityRightWorldX2 + speed2) / bSize;
                tileNum1 = gp.tileM.obsTileNum[entityRightCol2][entityTopRow2];
                tileNum2 = gp.tileM.obsTileNum[entityRightCol2][entityBottomRow2];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn2 = true;
                }
                break;

        }

    }

}
