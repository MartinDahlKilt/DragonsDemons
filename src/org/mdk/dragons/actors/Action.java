package org.mdk.dragons.actors;

public class Action {
    public enum Type {
        NOTHING,
        MOVE,
        MAGIC_PREPARE,
        MAGIC_ATTACK,
        CHANGE_WEAPON,
        RANGE_ATTACK,
        MELEE_ATTACK,
        MELEE_PARRY,
        USE_ITEM,
    }

    private Type mType;
    private Actor mSource;
    private Actor mTarget;
    //private int mCount;

    public Action(Actor source, Actor target, Type type) {
        mSource = source;
        mTarget = target;
        mType = type;
    }

    public Type getType() {
        return mType;
    }

    public Actor getSourceActor() {
        return  mSource;
    }

    public Actor getTargetActor() {
        return mTarget;
    }

    /*public int getActionCount() {
        return mCount;
    }*/
}
