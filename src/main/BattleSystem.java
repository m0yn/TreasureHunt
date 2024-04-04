package main;

import entity.Entity;
import entity.Pablo;

import static entity.Carlo.squares;
import static entity.Entity.*;
import static entity.Entity.WorldY;
import static entity.Pablo.squares2;
import static main.GamePanel.battleState;
import static main.KeyHandler.startBattle;
import static main.UI.text2;
import static tile.TileManager.bSize;

public class BattleSystem {

    // Class for managing battles between entities

    public static String winner;  // Winner of the battle
    static int i; // To only do the action once.
    public static boolean battleStarted, battleOccurred, CarloWon, PabloWon, wait;
    public static int carloPowerB, pabloPowerB, carloPowerA, pabloPowerA;
    public static int carloMoneyB, pabloMoneyB, carloMoneyA, pabloMoneyA;
    public static int z = 0;

    // Method for handling battles between two entities
    public static void battle(Entity player, Entity player2) {

        battleStarted = true;
        carloPowerB = (int) player.power;
        pabloPowerB = (int) player2.power;
        carloMoneyB = (int) player.money;
        pabloMoneyB = (int) player2.money;

        while (z==0) {
            wait = true;
            System.out.println(pabloMoneyB);
            System.out.println(carloMoneyB);
            System.out.println(pabloPowerB);
            System.out.println(carloPowerB);
            z++;
        }

        // If both players have equal power
        if (player.power == player2.power) {
            // Check whose turn it is and declare winner accordingly
            if (turn2) {
                PabloWon = true;
                CarloWon = false;
                if (!battleOccurred) {
                    player.WorldX = 0;
                    player.WorldY = bSize;
                    if (startBattle) {
                        wait = false;
                        System.out.println("Pablo wins!!");  // Print winner
                        player2.money += ((player2.power - player.power) / (player.power + player2.power)) * player.money;
                        player.money -= ((player2.power - player.power) / (player.power + player2.power)) * player.money;
                        player2.power -= player.power;
                        player.power = 0;
                        carloPowerA = (int) player.power;
                        pabloPowerA = (int) player2.power;
                        carloMoneyA = (int) player.money;
                        pabloMoneyA = (int) player2.money;
                        System.out.println(pabloMoneyA);
                        System.out.println(carloMoneyA);
                        System.out.println(pabloPowerA);
                        System.out.println(carloPowerA);
                        battleOccurred = true;
                    }
                }


            } else if (turn1) {
                PabloWon = false;
                CarloWon = true;
                if (!battleOccurred) {
                    player2.WorldX2 = 0;
                    player2.WorldY2 = bSize;
                    if (startBattle) {
                        wait = false;
                        System.out.println("Carlo wins!!");  // Print winner
                        player.money += ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                        player2.money -= ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                        player.power -= player2.power;
                        player2.power = 0;
                        carloPowerA = (int) player.power;
                        pabloPowerA = (int) player2.power;
                        carloMoneyA = (int) player.money;
                        pabloMoneyA = (int) player2.money;
                        System.out.println(pabloMoneyA);
                        System.out.println(carloMoneyA);
                        System.out.println(pabloPowerA);
                        System.out.println(carloPowerA);
                        battleOccurred = true;
                    }
                }
            }

        }

        // If player 1 has higher power
        else if (player.power > player2.power) {

            //text2 = "Carlo wins!";  // Set battle result text

            if (!battleOccurred) {
                player2.WorldX2 = 0;
                player2.WorldY2 = bSize;
                if (startBattle) {
                    wait = false;
                    PabloWon = false;
                    CarloWon = true;
                    System.out.println("Carlo wins!!");  // Print winner
                    player.money += ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                    player2.money -= ((player.power - player2.power) / (player.power + player2.power)) * player2.money;
                    player.power -= player2.power;
                    player2.power = 0;
                    carloPowerA = (int) player.power;
                    pabloPowerA = (int) player2.power;
                    carloMoneyA = (int) player.money;
                    pabloMoneyA = (int) player2.money;
                    System.out.println(pabloMoneyA);
                    System.out.println(carloMoneyA);
                    System.out.println(pabloPowerA);
                    System.out.println(carloPowerA);
                    battleOccurred = true;
                }
            }
        }

        // If player 2 has higher power
        else if (player.power < player2.power) {
            PabloWon = true;
            CarloWon = false;
            if (!battleOccurred) {
                player.WorldX = 0;
                player.WorldY = bSize;
                if (startBattle) {
                    wait = false;
                    System.out.println("Pablo wins!!");  // Print winner
                    player2.money += ((player2.power - player.power) / (player.power + player2.power)) * player.money;
                    player.money -= ((player2.power - player.power) / (player.power + player2.power)) * player.money;
                    player2.power -= player.power;
                    player.power = 0;
                    carloPowerA = (int) player.power;
                    pabloPowerA = (int) player2.power;
                    carloMoneyA = (int) player.money;
                    pabloMoneyA = (int) player2.money;
                    System.out.println(pabloMoneyA);
                    System.out.println(carloMoneyA);
                    System.out.println(pabloPowerA);
                    System.out.println(carloPowerA);
                    battleOccurred = true;
                }
            }

        }

    }
}
