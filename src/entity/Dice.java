package entity;

import java.util.Random;

public class Dice {

    public int roll() {
        Random random = new Random();
        int result = random.nextInt(6) + 1;
        System.out.println(result);
        return result;
    }

}
