package org.mdk.dragons;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dice {
    public static int roll(int cnt, int eyes, int modifier) {
        Random rnd = new Random();
        int val = 0;
        for(int idx = 0; idx < cnt; idx++) {
            val += rnd.nextInt(eyes) + 1 + modifier;
        }
        return val;
    }

    public static int roll(int eyes, int modifier) {
        return roll(1, eyes, modifier);
    }

    public static int roll(int eyes) {
        return roll(1, eyes, 0);
    }

    public static int roll(String dice) {
        Pattern pat = Pattern.compile("([0-9]+)d([0-9]+)([+-][0-9]+)?");
        Matcher m = pat.matcher(dice);
        if(m.matches()) {
            int cnt = Integer.parseInt(m.group(1));
            int eyes = Integer.parseInt(m.group(2));
            int mod = 0;
            if (m.group(3) != null) {
                mod = Integer.parseInt(m.group(3));
            }
            return roll(cnt, eyes, mod);
        } else {
            return 0;
        }
    }
}
