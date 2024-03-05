package main;

import javax.swing.*;

public class Main {


    // Main Function
    public static void main(String[] args) {

        // Initiating the program window.
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Declaration to start the game loop method.
        gamePanel.startGameThread();

    }

}
