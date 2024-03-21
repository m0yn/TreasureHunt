package main;

import entity.Entity;

import static entity.Entity.turn1;
import static entity.Entity.turn2;
import static main.GamePanel.battleState;
import static main.UI.text2;

public class BattleSystem {

    // Class for managing battles between entities

    public static String winner;  // Winner of the battle
    static int i; // To only do the action once.

    // Method for handling battles between two entities
    public static void battle(Entity player, Entity player2) {

        // If both players have equal power
        if (player.power == player2.power) {

            // Check whose turn it is and declare winner accordingly
            if (turn1) {
                System.out.println("Carlos wins!");
            }
            else if (turn2) {
                System.out.println("Carlo wins!");
            }

        }

        // If player 1 has higher power
        else if (player.power > player2.power) {

            while(i == 0) {
                text2 = "Carlo wins!";  // Set battle result text
                System.out.println("Carlo wins!!");  // Print winner
                // Calculate money transfer based on power difference
                player.money += ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                player2.money -= ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                System.out.println(player.money);  // Print player 1 money
                System.out.println(player2.money);  // Print player 2 money
                i++;
            }
        }

        // If player 2 has higher power
        else if (player.power < player2.power) {
            // Calculate money transfer based on power difference
            player2.money += ((player.power - player2.power)/(player.power + player2.power))*player2.money;
            player.money -= ((player.power - player2.power)/(player.power + player2.power))*player2.money;
            System.out.println(player.money);  // Print player 1 money
            System.out.println(player2.money);  // Print player 2 money
        }

    }

}
