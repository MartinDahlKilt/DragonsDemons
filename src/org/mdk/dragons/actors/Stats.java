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

    private int mStrength;
    private int mSize;
    private int mPhysics;
    private int mAgility;
    private int mIntelligence;
    private int mPsyche;
    private int mCharisma;


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
}
