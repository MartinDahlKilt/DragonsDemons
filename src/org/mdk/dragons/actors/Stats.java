package org.mdk.dragons.actors;

public abstract class Stats {
    public enum Type {
        STRENGTH,
        SIZE,
        PHYSICS,
        AGILITY,
        INTELLIGENCE,
        PSYCHE,
        CHARISMA
    }

    public enum SWORD_HAND {
        RIGHT,
        LEFT,
        BOTH,
        AMBIDEXTROUS
    }

    private int mStrength;
    private int mSize;
    private int mPhysics;
    private int mAgility;
    private int mIntelligence;
    private int mPsyche;
    private int mCharisma;
    protected SWORD_HAND mSwordHand;


    public abstract void roll();

    protected void set(Type type, int val) {
        switch (type) {
            case STRENGTH:
                mStrength = val;
            case SIZE:
                mSize = val;
            case PHYSICS:
                mPhysics = val;
            case AGILITY:
                mAgility = val;
            case INTELLIGENCE:
                mIntelligence = val;
            case PSYCHE:
                mPsyche = val;
            case CHARISMA:
                mCharisma = val;
        }
    }

    public int get(Type type) {
        switch (type) {
            case STRENGTH:
                return mStrength;
            case SIZE:
                return mSize;
            case PHYSICS:
                return mPhysics;
            case AGILITY:
                return mAgility;
            case INTELLIGENCE:
                return mIntelligence;
            case PSYCHE:
                return mPsyche;
            case CHARISMA:
                return mCharisma;
        }
        return -1;
    }

    public int getGroup(Type type) {
        int val = get(type);
        if(val >= 0 && val <= 3) {
            return 0;
        } else if(val >= 4 && val <= 8) {
            return 1;
        } else if(val >= 9 && val <= 12) {
            return 2;
        } else if(val >= 13 && val <= 16) {
            return 3;
        } else if(val >= 17 && val <= 20) {
            return 4;
        } else if(val >= 21 && val <= 25) {
            return 5;
        } else if(val >= 26 && val <= 30) {
            return 6;
        } else if(val >= 31 && val <= 40) {
            return 7;
        } else if(val >=41 && val <= 50) {
            return 8;
        } else {
            return 9; // TODO Handle values larger then 50
        }
    }
}
