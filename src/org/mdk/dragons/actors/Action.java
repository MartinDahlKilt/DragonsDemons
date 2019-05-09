package org.mdk.dragons.actors;

public class Action {
    public enum Type {
        NOTHING,
        MOVE,
        MAGICK_ATTACK,
        RANGE_ATTACK,
        CLOSE_COMBAT_ATTACK
    }

    private Type mType;
    private Actor mSource;
    private Actor mTarget;
    private int mCount;


    public Type getType() {
        return mType;
    }

    public Actor getSourceActor() {
        return  mSource;
    }

    public Actor getTargetActor() {
        return mTarget;
    }

    public int getActionCount() {
        return mCount;
    }
}
