package entity; // Package declaration for the entity package

import java.util.Random; // Importing Random class from java.util package

public class Dice { // Declaration of the Dice class

    public static int result; // Declaration of a static integer variable named result

    public int roll() { // Declaration of the roll method

        Random random = new Random(); // Creating a new Random object
        result = random.nextInt(6) + 1; // Generating a random integer between 1 and 6 (inclusive) and assigning it to result
        System.out.println(result); // Printing the result to the console
        return result; // Returning the generated result
    }

}
