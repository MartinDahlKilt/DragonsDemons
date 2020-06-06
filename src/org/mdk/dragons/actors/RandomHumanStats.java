package org.mdk.dragons.actors;

import org.mdk.dragons.Dice;

public class RandomHumanStats extends Stats {
    public RandomHumanStats() {
        roll();
    }


    public void roll() {
        set(Type.STRENGTH, Dice.roll(3, 6, 0));
        set(Type.SIZE, Dice.roll(3, 6, 0));
        set(Type.PHYSIQUE, Dice.roll(3, 6, 0));
        set(Type.AGILITY, Dice.roll(3, 6, 0));
        set(Type.INTELLIGENCE, Dice.roll(3, 6, 0));
        set(Type.PSYCHE, Dice.roll(3, 6, 0));
        set(Type.CHARISMA, Dice.roll(3, 6, 0));
        rollSwordHand();
    }

    private void rollSwordHand() {
        int val = Dice.roll(2, 10);
        if(val >= 2 && val <= 14) {
            mSwordHand = SWORD_HAND.RIGHT;
        } else if(val >= 15 && val <= 18) {
            mSwordHand = SWORD_HAND.LEFT;
        } else if(val == 19) {
            mSwordHand = SWORD_HAND.BOTH;
        } else if(val == 20) {
            mSwordHand = SWORD_HAND.AMBIDEXTROUS;
        }
    }
}
