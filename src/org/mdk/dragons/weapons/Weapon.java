package org.mdk.dragons.weapons;

public abstract class Weapon {
    public enum Type {
        SHIELD,
        DAGGER,
        ONE_HAND_SWORD,
        OTHER_SWORD,
        STAFF,
        AXE,
        CLUB,
        SPEAR,
        FLAIL,
        ROD,
        BOW,
        SLING,
        CROSSBOW,
        THROWING,
        LASSO,
        WHIP,
        BOLAS
    }

    public enum AttackType {
        RANGE,
        CLOSE
    }

    private int mReach;

    public Weapon(Type type, AttackType attackType, int reach) {
        mType = type;
        mAttackType = attackType;
        mReach = reach;
    }

    private Type mType;
    private AttackType mAttackType;

    public Type getType() { return mType; }
    public AttackType getAttackType() {
        return mAttackType;
    }

    public int getReach() {
        return mReach;
    }

    public abstract int getDamage();
    public abstract int getPain();
    public abstract int getFumbleLimit();
}
