package org.mdk.dragons.weapons;

import org.mdk.dragons.Dice;

public class ShortSword extends Weapon {
    public ShortSword() {
        super(Type.ONE_HAND_SWORD, AttackType.CLOSE, 0);
    }

    @Override
    public int getDamage() {
        return Dice.roll(6, 1);
    }

    @Override
    public int getPain() {
        return 0;
    }

    @Override
    public int getFumbleLimit() {
        return 20;
    }
}
