package main;

import entity.Entity;
import entity.Pablo;

import static entity.Entity.turn1;
import static entity.Entity.turn2;
import static main.GamePanel.battleState;
import static main.UI.text2;
import static tile.TileManager.bSize;

public class BattleSystem {

    // Class for managing battles between entities

    public static String winner;  // Winner of the battle
    static int i; // To only do the action once.
    public static boolean battleStarted, battleOccurred, CarloWon, PabloWon;

    // Method for handling battles between two entities
    public static void battle(Entity player, Entity player2) {

        battleStarted = true;

        // If both players have equal power
        if (player.power == player2.power) {

            // Check whose turn it is and declare winner accordingly
            if (turn2) {
                PabloWon = true;
                CarloWon = false;
                System.out.println("Pablo wins!");
                if (!battleOccurred) {
                    System.out.println("Pablo wins!!");  // Print winner
                    player2.money += ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                    player.money -= ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                    System.out.println(player.money);
                    System.out.println(player2.money);
                    player.WorldX = 0;
                    player.WorldY = bSize;
                    battleOccurred = true;
                }


            } else if (turn1) {
                PabloWon = false;
                CarloWon = true;
                System.out.println("Carlo wins!");
                if (!battleOccurred) {
                    System.out.println("Carlo wins!!");  // Print winner
                    player.money += ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                    player2.money -= ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                    System.out.println(player.money);
                    System.out.println(player2.money);
                    player2.WorldX2 = 0;
                    player2.WorldY2 = bSize;
                    battleOccurred = true;
                }
            }

        }

        // If player 1 has higher power
        else if (player.power > player2.power) {

            //text2 = "Carlo wins!";  // Set battle result text

            if (!battleOccurred) {
                PabloWon = false;
                CarloWon = true;
                System.out.println("Carlo wins!!");  // Print winner
                player.money += ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                player2.money -= ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                System.out.println(player.money);
                System.out.println(player2.money);
                player2.WorldX2 = 0;
                player2.WorldY2 = bSize;
                battleOccurred = true;
            }
        }

        // If player 2 has higher power
        else if (player.power < player2.power) {
            PabloWon = true;
            CarloWon = false;
            if (!battleOccurred) {
                System.out.println("Pablo wins!!");  // Print winner
                player2.money += ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                player.money -= ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                System.out.println(player.money);
                System.out.println(player2.money);
                player.WorldX = 0;
                player.WorldY = bSize;
                battleOccurred = true;
            }

        }

    }
}
