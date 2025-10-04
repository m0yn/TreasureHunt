# TreasureHunt
_A fun, competitive, and nostalgic local multiplayer adventure! Two players take turns to explore the map, avoid traps, trade at markets for gear, and duel each other in exciting battles all in the pursuit of the 8 hidden treasures!_

<img width="975" height="734" alt="image" src="https://github.com/user-attachments/assets/a70a3c0d-19c8-4d8e-b9fa-e0f509f37482" />

## Features
- 🎮 Local 2-player turn-based gameplay.
- ⚔️ Endless strategies. You can race to treasures, focus on defence, or go after your opponent!
- 🎵 Awesome nostalgic music and art.

## Controls
| Action  | Key |
|---------|-----|
| Move (P1) | WSAD Keys |
| Move (P2) | Arrow Keys |
| Start/Continue | Enter |
| Select  | Space |
| Status Board  | P |
| Fight (In Battle Screen)  | B |

- The Status Board is available to the players at any moment with the press of a button.
- Whenever you are prompted in the game, you must select an option with Space and *then* press Enter to continue the game.
- When you are in the battle screen, you must initiate the battle with the B button in order to be allowed to continue the game with Enter.
- When you land in a trap, you must select an option with Space before you are allowed to continue the game with Enter. If you have no money to pay then you must select the "Take the hit" option in order to continue.

## Objective
Players take turns based on rolling the dice to explore the houses in the map, each of which includes either a stash of money, a trap, or a treasure. Your goal is to find the treasure currently sought after by the castle, which is written in the status board. You do this by going through and exploring the houses in the map. Once you find the sought after treasure you will head to the castle to hand the treasure to the guard and be on your way to find the next treasure. The player who collects more treasures at the end of the game wins! If the game ends with players having the same number of treasures found each then the game will use the power points as a tiebreaker, followed by money and then finally if all attributes are equal, the winner will be the player who delivered their 4 treasures first.
## Map
The map is comprised of 102 squares (including each character’s starting square). The map includes 32 locations (26 Houses, 5 Markets, and the Castle), 4 protruding wall blocks, and the 2 main characters (Carlo and Pablo).
 The 26 houses contain:
-	5 Traps (Which will prompt you to part with either $50 or 1 power point).
-	13 Lost Items (Which will give you either $150 or $100)
-	8 Hidden Treasures

The Market is where you go to gear up on power points, which can be used as both defence and offence. You can also, should you have the money for it, purchase a treasure map which will show you the location of the sought after treasure on the map. Be careful however, as the location will be shown for both players. So you must strategize before committing to the purchase.
## Map Mechanics
Each location, wall block, and character occupies one square, and, in order for a player to explore or interact with a location, their character must be fully in that square, regardless of whether their turn is over or not. This means a player can freely explore as many locations as the dice allow in a single turn, including none at all by simply avoiding fully occupying the squares of the locations, like seen in the figure below. Finally, should a player move their character to fully occupy the square the opposing player’s character is in, a battle will ensue.

<img width="967" height="705" alt="image" src="https://github.com/user-attachments/assets/3a870768-0fe4-46b4-8b5e-1c299bbe1c4f" />

*Player 1 (Carlo) avoids engaging in battle with Player 2 (Pablo) by not fully occuping his square.*

## Movement Mechanics
Players can move in any direction up to a maximum number of squares decided by the dice. Therefore, players have a high level of freedom in how they can move within that boundary. To demonstrate how this works, let’s say Carlo got four on the dice. This means Carlo can move up to a maximum of four squares, and he can take whatever path the player wants. Now let’s say the player wants to explore the house marked on the image below. Since as you can see the house is four squares away, the player can take any path desired to reach that destination. Be it the conventional way of moving square by square (shown in yellow arrows) or simply moving directly towards it (shown in a purple arrow). This freedom comes in handy if a player wants to avoid entering the market, exploring certain houses, or starting a battle with the opposing player along their journey. For example, taking the yellow path means you will explore the destination house and another house along the way, while taking the purple path will have you only explore the destination house.

<img width="2941" height="2287" alt="image" src="https://github.com/user-attachments/assets/8aee398d-b318-4253-bf7c-a43def859424" />

*Player 1 (Carlo) has numerous path options to reach his destination.*

In technical terms, each point on the dice corresponds to 5 movement charges, which can be spent however way the player desires. For example, if the player wants his character to remain in the same square for the next turn, then they can waste the movement charges by simply going back and forth. Finally, movement into an obstacle is not counted towards your movement charges, and character will always be snapped into the square of which most of their character occupies. So you need not worry about accidentally wasting your charges.
## Player Mechanics
Each character has three attributes: money, power, and treasures found; all of which you can check at any time using the status board. Each player starts out with $100 and one power point to begin their journey.
###	Money
Primarily used to purchase items at the market, but you can also pay to avoid taking damage should you fall in a trap during your exploration. However, be careful as you will lose money should you be defeated in battle by an opponent with more power, and the amount lost depends on the power delta.
###	Power
Your sole line of defense against the opposing player during a battle. Therefore, it is important to build it up by purchasing weapons and armor from the market. You can lose power points in three ways:
- You will lose one power point if you fall in a trap and do not have money to pay (or simply choose to take the hit regardless).
- You will lose all your power points should you be defeated in battle.
- You will lose your opponent’s number of power points if you defeat them in battle.
    
Finally, should a player lose all their power points, their turn will end, and they will be sent back to their respective starting square.
###	Treasure
This is ultimately most important, as it will decide the winner of the game. Each time you find a treasure and successfully deliver it to the castle. Your score will increase by one. The player who is first to collect 5 treasures or has delivered more treasures when all are found wins the game! Be extra cautious however, as once a player finds a treasure, the game will announce it on the screen and if your opponent catches you and defeats you in battle then they will steal the treasure from you and send you back to your starting square!

## Battle System
The moment that the two characters fully occupy the same square, a battle will occur, and the game will take you to the battle screen, where the outcome of the battle will be decided based on your power points, with the player who has more power points defeating their opponent, and should both players have the same amount of power points then the player who initiated the battle will be the victor. The result of the battle is as follows:
### Power
The defeated player will lose all their power points and be sent to their starting square as a result, with the same number of power points being consumed by the victor. If both players have the same number of power points the same thing will apply, which means both players will lose all their power points, like seen below, as a result; however, in this special case the victor will not be sent to their starting square despite having zero power points.

<img width="2684" height="2010" alt="image" src="https://github.com/user-attachments/assets/abf3c1a4-2b96-4785-9b03-00e967bfdb89" />

*After this battle with equal power, both players lose their power points but only Player 1 (Carlo) is sent to his starting square.*

### Money
The victor of the battle will take a certain amount of money from their defeated foe, with the amount taken increasing with bigger power differences based on the equation below. This means that if both players have the same number of power points then there will be no change in money for both players after the battle.

<img width="2856" height="334" alt="image" src="https://github.com/user-attachments/assets/594d97b3-f23f-4297-ada1-f28be79ddb69" />

*The amount calculated by this formula will go to the winner and be deducted from the loser*


### Treasure
The winner of the battle will take their defeated opponent's treasure and send them back to their starting square, regardless of the power points' difference.

## Setup
1. Make sure you have **Java 21 or higher** installed. You can download the latest Java SE directly from [Oracle's website](https://www.oracle.com/java/technologies/downloads/).
3. Download the game from the [Releases](https://github.com/m0yn/TreasureHunt/releases) page.
4. Run the .jar file and have fun!





