package main;

import entity.Entity;

import static entity.Entity.speed;
import static tile.TileManager.bSize;

// CollisionChecker class is responsible for detecting collisions between entities and obstacles on the map.
public class CollisionChecker {

    // Static reference to GamePanel to access the tile and obstacle configurations.
    public static GamePanel gp;

    // Constructor that assigns the GamePanel instance.
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    // checkTile method checks for collisions between the specified entity and tiles that are marked as obstacles.
    public static void checkTile(Entity entity) {

        // Calculate the entity's boundaries within the game world, adjusted for a solid area.
        int entityLeftWorldX = entity.WorldX + entity.solidArea.x + 10;
        int entityRightWorldX = entity.WorldX + entity.solidArea.x + entity.solidArea.width - 10;
        int entityTopWorldY = entity.WorldY + entity.solidArea.y + 10;
        int entityBottomWorldY = entity.WorldY + entity.solidArea.y + entity.solidArea.height - 10;

        // Calculate the tile column and row numbers based on the entity's boundaries.
        int entityLeftCol = entityLeftWorldX / bSize;
        int entityRightCol = entityRightWorldX / bSize;
        int entityTopRow = entityTopWorldY / bSize;
        int entityBottomRow = entityBottomWorldY / bSize;

        int tileNum1, tileNum2;

        // Check for collision based on the entity's current direction of movement.
        switch (entity.direction) {

            case "up":
                // Adjust the top row based on the entity's speed to predict collision with tiles above.
                entityTopRow = (entityTopWorldY - speed) / bSize;
                // Determine the tile numbers for the left and right boundaries of the entity.
                tileNum1 = gp.tileM.obsTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.obsTileNum[entityRightCol][entityTopRow];

                // Set the collision flag if either of the tiles is marked as an obstacle.
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                // Similar to 'up', but checks for tiles below the entity.
                entityBottomRow = (entityBottomWorldY + speed) / bSize;
                tileNum1 = gp.tileM.obsTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.obsTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                // Adjust the left column to check for potential collisions to the left of the entity.
                entityLeftCol = (entityLeftWorldX - speed) / bSize;
                tileNum1 = gp.tileM.obsTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.obsTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                // Checks for collisions to the right of the entity.
                entityRightCol = (entityRightWorldX + speed) / bSize;
                tileNum1 = gp.tileM.obsTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.obsTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
