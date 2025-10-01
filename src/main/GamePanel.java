package main;

import entity.Carlo;
import entity.Pablo;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

import static entity.Carlo.dice;
import static entity.Pablo.dice2;
import static entity.Carlo.squares;
import static entity.Entity.*;

import static entity.Pablo.squares2;
import static main.UI.ySX;
import static main.UI.yX;
import static tile.TileManager.bSize;
import static main.BattleSystem.battle;


public class    GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 32;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public UI ui = new UI(this); // User interface manager
    int FPS = 35; // Game Framerate

    // Game state management
    public static int gameState = 4; // Current game state
    public static final int playState = 1;
    public static final int battleState = 2;
    public static final int boardState = 3;

    public static final int startState = 4;
    public static final int marketState = 5;
    public static final int castleState = 6;
    public static final int trapState = 7;
    public static final int lostState = 8;
    public static final int treasureState = 9;
    public static final int inventoryState = 10;
    public static final int endState = 11;
    public static boolean carloWonGame, pabloWonGame;

    //
    public boolean carloMarket, pabloMarket, carloCastle, pabloCastle, treasureFound, numsGenerated, chT, phT;
    public boolean tr1, tr2, tr3, tr4, tr5, li1, li2, li3, li4, li5, li6, li7, li8 ,li9 ,li10, li11 ,li12, li13,
    tre1, tre2, tre3, tre4, tre5, tre6, tre7, tre8, ptre1, ptre2, ptre3, ptre4, ptre5, ptre6, ptre7, ptre8,
            tre1c, tre2c, tre3c, tre4c, tre5c, tre6c, tre7c, tre8c;
    public int pabloMoney, carloMoney, pabloPower, carloPower, pabloPoints, carloPoints, remainingTreasures;
    public static int treasureIndex1, treasureIndex2, treasureIndex3, treasureIndex4, treasureIndex5, treasureIndex6, treasureIndex7, treasureIndex8;
    public static int cPoints = 0, pPoints = 0;
    public boolean t1, t2, t3, t4, t5, t6, t7, t8;
    boolean[] conditions = {t1, t2, t3, t4, t5, t6, t7, t8};
    public static int randomNumber;

    TileManager tileM = new TileManager(this); // Manager for tiles and map drawing
    KeyHandler keyH = new KeyHandler(); // Handler for keyboard input

    // Game thread for the main game loop
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this); // Collision checker for the main player
    public CollisionChecker2 cChecker2 = new CollisionChecker2(this); // Collision checker for the second player
    Carlo carlo; // Main player object
    Pablo pablo; // Second player object
    TimedEventManager timedEventManager = new TimedEventManager(this);
    Sound sound = new Sound();

    // Constructor initializes the game panel and player objects
    public GamePanel() {
        // Initialization of player objects with their starting values
        carlo = new Carlo(this, keyH, 0, 500, 0);
        carlo.setDefaultValues();
        carlo.getPlayerImage();

        pablo = new Pablo(this, keyH, 0, 100, 1);
        carlo = new Carlo(this, keyH, 0, 100, 1);

        carlo.setDefaultValues();
        carlo.getPlayerImage();

        pablo.setDefaultValues2();
        pablo.getPlayerImage2();

        // Set the size, background color, and buffering for the game panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    // Start the game thread
    // Starts the main game loop thread
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Function that repaints the screen 60 times a second.
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {// Main game loop, controlling game updates and rendering

            update();
            repaint();
            timedEventManager.handleTimedEvents();

            // Checks for an error then throws an exception.
            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // Convert to milliseconds

                if (remainingTime < 0) {

                    remainingTime = 0;

                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {

                throw new RuntimeException(e);

            }

        }

    }

    public void update() {

        if (!numsGenerated) {
            treasureIndex1 = Randomizer.treasureFind();
            treasureIndex2 = Randomizer.treasureFind();
            treasureIndex3 = Randomizer.treasureFind();
            treasureIndex4 = Randomizer.treasureFind();
            treasureIndex5 = Randomizer.treasureFind();
            treasureIndex6 = Randomizer.treasureFind();
            treasureIndex7 = Randomizer.treasureFind();
            treasureIndex8 = Randomizer.treasureFind();
            numsGenerated = true;
        }

        carloMoney = (int) carlo.money;
        carloPower = (int) carlo.power;
        pabloMoney = (int) pablo.money;
        pabloPower = (int) pablo.power;
        carloPoints = carlo.points;
        pabloPoints = pablo.points;
        remainingTreasures = 8 - (carlo.points + pablo.points);

        // Checks for player collision to trigger battle state
        if (keyH.startGame) {
            gameState = playState;
        }

        if (WorldX == WorldX2 && WorldY == WorldY2 && WorldX != 0) {
            if ((WorldX == bSize * 4 && WorldY == bSize) ||
                    (WorldX == bSize * 6 && WorldY == bSize * 3) ||
                    (WorldX == bSize && WorldY == bSize * 7) ||
                    (WorldX == bSize * 4 && WorldY == bSize * 10) ||
                    (WorldX == bSize * 8 && WorldY == bSize * 8) || (WorldX == bSize * 6 && WorldY == bSize * 5)) {
            } else gameState = battleState;
        }

        if (keyH.startBoard) {
            gameState = boardState;
        }

        if (keyH.startEnd) {
            gameState = endState;
        }

        if ((WorldX == bSize * 4 && WorldY == bSize || WorldX == bSize * 6 && WorldY == bSize * 3 || WorldX == bSize && WorldY == bSize * 7
                || WorldX == bSize * 4 && WorldY == bSize * 10 || WorldX == bSize * 8 && WorldY == bSize * 8) && !carloMarket) {
            gameState = marketState;

            if (keyH.startGame) carloMarket = true;

            if (turn1 && keyH.select) {
                carloMarket = true;
                if (yX == -1 || yX == 0 || yX == 3 || yX == 6) {
                    if (carlo.money >= 50) {   // only allow if enough money
                        carlo.money -= 50;
                        carlo.power++;
                    }
                } else if (yX == 1 || yX == 4 || yX == 7) {
                    if (carlo.money >= 100) {
                        carlo.money -= 100;
                        carlo.power += 2;
                    }
                } else if (yX == 2 || yX == 5 || yX == 8) {
                    if (carlo.money >= 150) {
                        carlo.money -= 150;
                        carlo.power += 3;
                    }
                } else if (yX == 9) {
                    if (carlo.money >= 800) {
                        randomNumber = ui.randT;
                        carlo.money -= 800;
                    }
                }
            }
        }

        if ((WorldX2 == bSize * 4 && WorldY2 == bSize || WorldX2 == bSize * 6 && WorldY2 == bSize * 3 || WorldX2 == bSize && WorldY2 == bSize * 7
                || WorldX2 == bSize * 4 && WorldY2 == bSize * 10 || WorldX2 == bSize * 8 && WorldY2 == bSize * 8) && !pabloMarket) {
            gameState = marketState;

            if (keyH.startGame) pabloMarket = true;

            if (turn2 && keyH.select) {
                pabloMarket = true;
                if (yX == -1 || yX == 0 || yX == 3 || yX == 6) {
                    if (pablo.money >= 50) {
                        pablo.money -= 50;
                        pablo.power++;
                    }
                } else if (yX == 1 || yX == 4 || yX == 7) {
                    if (pablo.money >= 100) {
                        pablo.money -= 100;
                        pablo.power += 2;
                    }
                } else if (yX == 2 || yX == 5 || yX == 8) {
                    if (pablo.money >= 150) {
                        pablo.money -= 150;
                        pablo.power += 3;
                    }
                } else if (yX == 9) {
                    if (pablo.money >= 800) {
                        randomNumber = ui.randT;
                        pablo.money -= 800;
                    }
                }
            }
        }

        if (WorldX == bSize * 6 && WorldY == bSize * 5 && !carloCastle && squares != dice.result*80) {
            gameState = castleState;
            carloCastle = true;
                if (cPoints != 0) {
                carlo.points += cPoints;
                cPoints = 0;
                chT = true;
                ui.treasureSought = false;
                }
        }

        // If Pablo lands at the castle tile, switch to Castle State.
        if (WorldX2 == bSize * 6 && WorldY2 == bSize * 5 && !pabloCastle && squares2 != dice2.result*80) { // "!pabloCastle" condition to only do the action once. "squares2 != dice2.result" condition so that player isn't stuck in the state if his turn ends on a castle tile.
            gameState = castleState; // switches to Castle State
            pabloCastle = true;
                if (pPoints != 0) {
                    pablo.points += pPoints;
                    pPoints = 0;
                    phT = true;
                    ui.treasureSought = false;
                }
        }

        // For Traps, for Carlo.
        if (WorldX == bSize*8 && WorldY == bSize*10 && !tr1) {
                gameState = trapState;

                if (keyH.select) {
                    if (carlo.money >= 50 && (ySX == 0 || ySX == -1)) {
                        carlo.money -= 50;
                        tr1 = true;
                    } else if (ySX == 1 || ySX == 2) {
                        carlo.power--;
                        tr1 = true;
                        if (carlo.power <= 0) {
                            carlo.power = 0;
                            WorldX = 0;
                            WorldY = bSize;
                            squares = 0;
                        }
                    }
                }
            } else if (WorldX == bSize*5 && WorldY == bSize*8 && !tr2) {
            gameState = trapState;

            if (keyH.select) {
                if (carlo.money >= 50 && (ySX == 0 || ySX == -1)) {
                    carlo.money -= 50;
                    tr2 = true;
                } else if (ySX == 1 || ySX == 2) {
                    carlo.power--;
                    tr2 = true;
                    if (carlo.power <= 0) {
                        carlo.power = 0;
                        WorldX = 0;
                        WorldY = bSize;
                        squares = 0;
                    }
                }
            }
        } else if (WorldX == bSize*4 && WorldY == bSize*5 && !tr3) {
            gameState = trapState;

            if (keyH.select) {
                if (carlo.money >= 50 && (ySX == 0 || ySX == -1)) {
                    carlo.money -= 50;
                    tr3 = true;
                } else if (ySX == 1 || ySX == 2) {
                    carlo.power--;
                    tr3 = true;
                    if (carlo.power <= 0) {
                        carlo.power = 0;
                        WorldX = 0;
                        WorldY = bSize;
                        squares = 0;
                    }
                }
            }
        } else if (WorldX == bSize && WorldY == bSize*3 && !tr4) {
            gameState = trapState;

            if (keyH.select) {
                if (carlo.money >= 50 && (ySX == 0 || ySX == -1)) {
                    carlo.money -= 50;
                    tr4 = true;
                } else if (ySX == 1 || ySX == 2) {
                    carlo.power--;
                    tr4 = true;
                    if (carlo.power <= 0) {
                        carlo.power = 0;
                        WorldX = 0;
                        WorldY = bSize;
                        squares = 0;
                    }
                }
            }
        } else if (WorldX == bSize*10 && WorldY == bSize && !tr5) {
            gameState = trapState;

            if (keyH.select) {
                if (carlo.money >= 50 && (ySX == 0 || ySX == -1)) {
                    carlo.money -= 50;
                    tr5 = true;
                } else if (ySX == 1 || ySX == 2) {
                    carlo.power--;
                    tr5 = true;
                    if (carlo.power <= 0) {
                        carlo.power = 0;
                        WorldX = 0;
                        WorldY = bSize;
                        squares = 0;
                    }
                }
            }
        }

        // For Traps, for Pablo.
        if (WorldX2 == bSize*8 && WorldY2 == bSize*10 && !tr1) {
            gameState = trapState;

            if (keyH.select) {
                if (pablo.money >= 50 && (ySX == 0 || ySX == -1)) {
                    pablo.money -= 50;
                    tr1 = true;
                } else if (ySX == 1 || ySX == 2) {
                    pablo.power--;
                    tr1 = true;
                    if (pablo.power <= 0) {
                        pablo.power = 0;
                        WorldX2 = bSize*11;
                        WorldY2 = bSize*10;
                        squares2 = 0;
                    }
                }
            }
        } else if (WorldX2 == bSize*5 && WorldY2 == bSize*8 && !tr2) {
            gameState = trapState;

            if (keyH.select) {
                if (pablo.money >= 50 && (ySX == 0 || ySX == -1)) {
                    pablo.money -= 50;
                    tr2 = true;
                } else if (ySX == 1 || ySX == 2) {
                    pablo.power--;
                    tr2 = true;
                    if (pablo.power <= 0) {
                        pablo.power = 0;
                        WorldX2 = bSize*11;
                        WorldY2 = bSize*10;
                        squares2 = 0;
                    }
                }
            }
        } else if (WorldX2 == bSize*4 && WorldY2 == bSize*5 && !tr3) {
            gameState = trapState;

            if (keyH.select) {
                if (pablo.money >= 50 && (ySX == 0 || ySX == -1)) {
                    pablo.money -= 50;
                    tr3 = true;
                } else if (ySX == 1 || ySX == 2) {
                    pablo.power--;
                    tr3 = true;
                    if (pablo.power <= 0) {
                        pablo.power = 0;
                        WorldX2 = bSize*11;
                        WorldY2 = bSize*10;
                        squares2 = 0;
                    }
                }
            }
        } else if (WorldX2 == bSize && WorldY2 == bSize*3 && !tr4) {
            gameState = trapState;

            if (keyH.select) {
                if (pablo.money >= 50 && (ySX == 0 || ySX == -1)) {
                    pablo.money -= 50;
                    tr4 = true;
                } else if (ySX == 1 || ySX == 2) {
                    pablo.power--;
                    tr4 = true;
                    if (pablo.power <= 0) {
                        pablo.power = 0;
                        WorldX2 = bSize*11;
                        WorldY2 = bSize*10;
                        squares2 = 0;
                    }
                }
            }
        } else if (WorldX2 == bSize*10 && WorldY2 == bSize && !tr5) {
            gameState = trapState;

            if (keyH.select) {
                if (pablo.money >= 50 && (ySX == 0 || ySX == -1)) {
                    pablo.money -= 50;
                    tr5 = true;
                } else if (ySX == 1 || ySX == 2) {
                    pablo.power--;
                    tr5 = true;
                    if (pablo.power <= 0) {
                        pablo.power = 0;
                        WorldX2 = bSize*11;
                        WorldY2 = bSize*10;
                        squares2 = 0;
                    }
                }
            }
        }



        // For Lost Items, for Carlo.
        if (WorldX == bSize*2 && WorldY == bSize && !li1) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li1 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li1 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX == bSize*7 && WorldY == bSize && !li2) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li2 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li2 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX == bSize*9 && WorldY == bSize*2 && !li3) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li3 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li3 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX == bSize*3 && WorldY == bSize*3 && !li4) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li4 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li4 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX == bSize && WorldY == bSize*4 && !li5) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li5 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li5 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX == bSize*8 && WorldY == bSize*4 && !li6) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li6 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li6 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX == bSize*5 && WorldY == bSize*6 && !li7) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li7 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li7 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX == bSize*10 && WorldY == bSize*6 && !li8) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li8 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li8 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX == bSize*8 && WorldY == bSize*7 && !li9) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li9 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li9 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX == bSize*3 && WorldY == bSize*7 && !li10) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li10 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li10 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX == bSize*2 && WorldY == bSize*9 && !li11) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li11 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li11 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX == bSize*7 && WorldY == bSize*9 && !li12) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li12 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li12 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX == bSize*5 && WorldY == bSize*10 && !li13) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    carlo.money += 100;
                    ui.q = 110;
                    li13 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    carlo.money += 150;
                    ui.q = 111;
                    li13 = true;
                    ui.lostSought = false;
                    break;
            }
        }



        // For Lost Items, for Pablo.
        if (WorldX2 == bSize*2 && WorldY2 == bSize && !li1) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li1 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li1 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX2 == bSize*7 && WorldY2 == bSize && !li2) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li2 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li2 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX2 == bSize*9 && WorldY2 == bSize*2 && !li3) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li3 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li3 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX2 == bSize*3 && WorldY2 == bSize*3 && !li4) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li4 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li4 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX2 == bSize && WorldY2 == bSize*4 && !li5) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li5 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li5 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX2 == bSize*8 && WorldY2 == bSize*4 && !li6) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li6 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li6 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX2 == bSize*5 && WorldY2 == bSize*6 && !li7) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li7 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li7 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX2 == bSize*10 && WorldY2 == bSize*6 && !li8) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li8 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li8 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX2 == bSize*8 && WorldY2 == bSize*7 && !li9) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li9 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li9 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX2 == bSize*3 && WorldY2 == bSize*7 && !li10) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li10 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li10 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX2 == bSize*2 && WorldY2 == bSize*9 && !li11) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li11 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li11 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX2 == bSize*7 && WorldY2 == bSize*9 && !li12) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li12 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li12 = true;
                    ui.lostSought = false;
                    break;
            }
        } else if (WorldX2 == bSize*5 && WorldY2 == bSize*10 && !li13) {
            gameState = lostState;

            switch (ui.li) {
                case 1:
                    pablo.money += 100;
                    ui.q = 110;
                    li13 = true;
                    ui.lostSought = false;
                    break;
                case 2:
                    pablo.money += 150;
                    ui.q = 111;
                    li13 = true;
                    ui.lostSought = false;
                    break;
            }
        }

            // For treasures, for Carlo.
            if (WorldX == bSize * 3 && WorldY == bSize * 2 && !tre1) {

                gameState = treasureState;

                switch (treasureIndex1) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }

                if (treasureIndex1 == ui.randT) {
                    cPoints++;
                    tre1c = true;
                    ptre1 = true;
                }
                tre1 = true;

            } else if (WorldX == bSize * 8 && WorldY == bSize * 2 && !tre2) {
                gameState = treasureState;

                switch (treasureIndex2) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex2 == ui.randT) {
                    cPoints++;
                    tre2c = true;
                    ptre2 = true;
                }
                tre2 = true;
            } else if (WorldX == bSize * 6 && WorldY == bSize * 4 && !tre3) {
                gameState = treasureState;

                switch (treasureIndex3) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex3 == ui.randT) {
                    cPoints++;
                    tre3c = true;
                    ptre3 = true;
                }
                tre3 = true;
            } else if (WorldX == bSize * 9 && WorldY == bSize * 5 && !tre4) {
                gameState = treasureState;

                switch (treasureIndex4) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex4 == ui.randT) {
                    cPoints++;
                    tre4c = true;
                    ptre4 = true;
                }
                tre4 = true;
            } else if (WorldX == bSize * 2 && WorldY == bSize * 6 && !tre5) {
                gameState = treasureState;

                switch (treasureIndex5) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex5 == ui.randT) {
                    cPoints++;
                    tre5c = true;
                    ptre5 = true;
                }
                tre5 = true;
            } else if (WorldX == bSize * 6 && WorldY == bSize * 7 && !tre6) {
                gameState = treasureState;

                switch (treasureIndex6) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex6 == ui.randT) {
                    cPoints++;
                    tre6c = true;
                    ptre6 = true;
                }
                tre6 = true;
            } else if (WorldX == bSize * 3 && WorldY == bSize * 9 && !tre7) {
                gameState = treasureState;

                switch (treasureIndex7) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex7 == ui.randT) {
                    cPoints++;
                    tre7c = true;
                    ptre7 = true;
                }
                tre7 = true;
            } else if (WorldX == bSize * 9 && WorldY == bSize * 9 && !tre8) {

                gameState = treasureState;

                switch (treasureIndex8) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex8 == ui.randT) {
                    cPoints++;
                    tre8c = true;
                    ptre8 = true;
                }
                tre8 = true;
            }


            // For treasures, for Pablo.
            if (WorldX2 == bSize * 3 && WorldY2 == bSize * 2 && !ptre1) {

                gameState = treasureState;

                switch (treasureIndex1) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex1 == ui.randT) {
                    pPoints++;
                    tre1c = true;
                    tre1 = true;
                }
                ptre1 = true;

            } else if (WorldX2 == bSize * 8 && WorldY2 == bSize * 2 && !ptre2) {
                gameState = treasureState;

                switch (treasureIndex2) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex2 == ui.randT) {
                    pPoints++;
                    tre2c = true;
                    tre2 = true;
                }
                ptre2 = true;

            } else if (WorldX2 == bSize * 6 && WorldY2 == bSize * 4 && !ptre3) {
                gameState = treasureState;


                switch (treasureIndex3) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex3 == ui.randT) {
                    pPoints++;
                    tre3c = true;
                    tre3 = true;
                }
                ptre3 = true;

            } else if (WorldX2 == bSize * 9 && WorldY2 == bSize * 5 && !ptre4) {
                gameState = treasureState;

                switch (treasureIndex4) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex4 == ui.randT) {
                    pPoints++;
                    tre4c = true;
                    tre4 = true;
                }
                ptre4 = true;

            } else if (WorldX2 == bSize * 2 && WorldY2 == bSize * 6 && !ptre5) {
                gameState = treasureState;

                switch (treasureIndex5) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex5 == ui.randT) {
                    pPoints++;
                    tre5c = true;
                    tre5 = true;
                }
                ptre5 = true;

            } else if (WorldX2 == bSize * 6 && WorldY2 == bSize * 7 && !ptre6) {
                gameState = treasureState;

                switch (treasureIndex6) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex6 == ui.randT) {
                    pPoints++;
                    tre6c = true;
                    tre6 = true;
                }
                ptre6 = true;

            } else if (WorldX2 == bSize * 3 && WorldY2 == bSize * 9 && !ptre7) {
                gameState = treasureState;

                switch (treasureIndex7) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex7 == ui.randT) {
                    pPoints++;
                    tre7c = true;
                    tre7 = true;
                }
                ptre7 = true;

            } else if (WorldX2 == bSize * 9 && WorldY2 == bSize * 9 && !ptre8) {

                gameState = treasureState;

                switch (treasureIndex8) {

                    case 1:
                        ui.qq = 101;
                        t1 = true;
                        break;
                    case 2:
                        ui.qq = 102;
                        t2 = true;
                        break;
                    case 3:
                        ui.qq = 103;
                        t3 = true;
                        break;
                    case 4:
                        ui.qq = 104;
                        t4 = true;
                        break;
                    case 5:
                        ui.qq = 105;
                        t5 = true;
                        break;
                    case 6:
                        ui.qq = 106;
                        t6 = true;
                        break;
                    case 7:
                        ui.qq = 107;
                        t7 = true;
                        break;
                    case 8:
                        ui.qq = 108;
                        t8 = true;
                        break;
                }
                if (treasureIndex8 == ui.randT) {
                    pPoints++;
                    tre8c = true;
                    tre8 = true;
                }
                ptre8 = true;
            }


        if (WorldX == WorldX2 && WorldY == WorldY2 && WorldX != 0) {
            if ((WorldX == bSize * 4 && WorldY == bSize) ||
            (WorldX == bSize * 6 && WorldY == bSize * 3) ||
                    (WorldX == bSize && WorldY == bSize * 7) ||
                    (WorldX == bSize * 4 && WorldY == bSize * 10) ||
                    (WorldX == bSize * 8 && WorldY == bSize * 8) || (WorldX == bSize * 6 && WorldY == bSize * 5)) {
            } else {
                gameState = battleState;
                keyH.startGame = false;
            }
        }

        if (carlo.points >= 5 || pablo.points >= 5 || remainingTreasures == 0) {
            gameState = endState;
            if (carlo.points > pablo.points) {
                carloWonGame = true;
            } else if (carlo.points < pablo.points) {
                pabloWonGame = true;
            } else if (carlo.points == pablo.points) {

                if (carlo.power > pablo.power) {
                    carloWonGame = true;
                } else if (carlo.power < pablo.power) {
                    pabloWonGame = true;
                } else if (carlo.power == pablo.power) {

                    if (carlo.money > pablo.money) {
                        carloWonGame = true;
                    } else if (carlo.money < pablo.money) {
                        pabloWonGame = true;
                    } else if (carlo.money == pablo.money) {

                        if (turn1) {
                            pabloWonGame = true;
                        } else if (turn2) {
                            carloWonGame = true;
                        }

                    }

                }

            }
        }

        if (gameState == playState) {
            // Update players and game state based on the current game state
            carlo.update();
            pablo.update2();
        }

        if (gameState == battleState) {
            // Engage battle mode
            battle(carlo, pablo);
        }

    }





    // Paint Function
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        carlo.draw(g2);
        pablo.draw2(g2);

        ui.draw(g2);

        g2.dispose();

    }

    public void playMusic(int i) {

        sound.setFIle(i);
        sound.play();
        sound.loop();

    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFIle(i);
        sound.play();
    }

}
