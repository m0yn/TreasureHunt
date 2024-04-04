package main; // Package declaration for the entity package

import java.util.HashSet;
import java.util.Random; // Importing Random class from java.util package
import java.util.Set;

public class Randomizer { // Declaration of the Dice class

    public static int result; // Declaration of a static integer variable named result
    public static int a = 8;
    private static Set<Integer> generatedNumbers = new HashSet<>();
    private static Set<Integer> generatedNumbers2 = new HashSet<>();

    public static int randomize() { // Declaration of the roll method

        Random random = new Random(); // Creating a new Random object
        result = random.nextInt(4) + 1; // Generating a random integer between 1 and 6 (inclusive) and assigning it to result
        return result; // Returning the generated result

    }

    public static int treasureSelect() {

        Random random = new Random();
        int result;

        do {
            result = random.nextInt(8) + 1;
        } while (generatedNumbers2.contains(result));

        generatedNumbers2.add(result);
        return result;

    }

    public static int lostSelect() { // Declaration of the roll method

        Random random = new Random(); // Creating a new Random object
        result = random.nextInt(2) + 1; // Generating a random integer between 1 and 6 (inclusive) and assigning it to result
        return result; // Returning the generated result

    }

    public static int treasureFind() {
        Random random = new Random();
        int result;

        do {
            result = random.nextInt(8) + 1;
        } while (generatedNumbers.contains(result));

        generatedNumbers.add(result);
        return result;
    }

    public static int generateNumberExcludingConditionally(boolean[] conditions) {
        Random random = new Random();
        int result;

        do {
            result = random.nextInt(8) + 1;
        } while (isExcluded(result, conditions));

        return result;
    }

    private static boolean isExcluded(int number, boolean[] conditions) {
        for (int i = 0; i < conditions.length; i++) {
            if (conditions[i] && (number == (i + 1))) {
                return true;
            }
        }
        return false;
    }

}