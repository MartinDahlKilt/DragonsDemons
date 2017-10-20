package org.mdk.dragons;

import java.util.Random;

public class Dice {
    public static int roll(int cnt, int eyes, int modifier) {
        Random rnd = new Random();
        int val = 0;
        for(int idx = 0; idx < cnt; idx++) {
            val = rnd.nextInt(eyes) + 1 + modifier;
        }
        return val;
    }

    public static int roll(int eyes, int modifier) {
        return roll(1, eyes, modifier);
    }

    public static int roll(int eyes) {
        return roll(1, eyes, 0);
    }
}
