package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create a new JFrame (window) for the game
        JFrame window = new JFrame();

        // Set the default close operation to exit the program when the window is closed
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Disable window resizing
        window.setResizable(false);

        // Set the title of the window
        window.setTitle("TreasureHunt");

        // Create a new GamePanel, which extends JPanel and contains the game logic
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // Pack the components within the window to their preferred sizes
        window.pack();

        // Center the window on the screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);



        // Start the game thread within the GamePanel to handle game updates
        gamePanel.startGameThread();
    }
}


