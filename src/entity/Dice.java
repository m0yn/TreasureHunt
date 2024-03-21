package entity;

import java.util.Random;

public class Dice {

    public static int result;

    public int roll() {
        Random random = new Random();
        result = random.nextInt(6) + 1;
        System.out.println(result);
        return result;
    }

}
