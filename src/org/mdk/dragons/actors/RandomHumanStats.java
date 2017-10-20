package org.mdk.dragons.actors;

import org.mdk.dragons.Dice;

public class RandomHumanStats extends Stats {
    public RandomHumanStats() {
        roll();
    }


    @Override
    public void roll() {
        set(Type.STRENGTH, Dice.roll(3, 6));
        set(Type.SIZE, Dice.roll(3, 6));
        set(Type.PHYSICS, Dice.roll(3, 6));
        set(Type.AGILITY, Dice.roll(3, 6));
        set(Type.INTELLIGENCE, Dice.roll(3, 6));
        set(Type.PSYCHE, Dice.roll(3, 6));
        set(Type.CHARISMA, Dice.roll(3, 6));
    }
}
