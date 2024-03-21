package main;

import entity.Entity;

import static entity.Entity.turn1;
import static entity.Entity.turn2;
import static main.GamePanel.battleState;
import static main.UI.text2;


public class BattleSystem {

    public static String winner;

    public static void battle(Entity player, Entity player2) {

        if (player.power == player2.power) {

            if (turn1) {
                System.out.println("Carlos wins!");
            }
            else if (turn2) {
                System.out.println("Carlo wins!");
            }

        }

        else if (player.power > player2.power) {
            text2 = "Carlo wins!";
            System.out.println("Carlo wins!!");
            player.money += ((player.power - player2.power)/(player.power + player2.power))*player2.money;
            player2.money -= ((player.power - player2.power)/(player.power + player2.power))*player2.money;
            System.out.println(player.money);
            System.out.println(player2.money);
        }

        else if (player.power < player2.power) {
            player2.money += ((player.power - player2.power)/(player.power + player2.power))*player2.money;
            player.money -= ((player.power - player2.power)/(player.power + player2.power))*player2.money;
            System.out.println(player.money);
            System.out.println(player2.money);
        }

    }

}
