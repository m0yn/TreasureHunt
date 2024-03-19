package entity;

import java.util.Random;

public class Dice {

    // Method to simulate rolling a dice and returning the result
    public int roll() {
        Random random = new Random();
        // Generate a random number between 1 and 6 (inclusive)
        int result = random.nextInt(6) + 1;
        System.out.println("" + result);
        return  result;
    }

}
