package org.mdk.dragons.actors;

import org.mdk.dragons.Dice;
import org.mdk.dragons.weapons.Weapon;

public class Actor {
    public enum Status {
        DEAD,
        UNCONSIOUS,
        ACTIVE
    }

    private Status mStatus;
    private Stats mStats;
    private Body mBody;

    private int mCurrentInitiative = 0;

    public Actor(Stats stats, Body body) {
        setStatus(Status.ACTIVE);
        mStats = stats;
        mBody = body;
    }

    public int getNextInitiative() {
        mCurrentInitiative = Dice.roll(10) + mStats.get(Stats.Type.AGILITY);
        return mCurrentInitiative;
    }
    public int getInitiative() {
        return mCurrentInitiative;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }
    public Status getStatus() {
        return mStatus;
    }

    public Body.BodyPart getTargetBodypart(Weapon.AttackType type) {
        return mBody.getTargetBodyPart(type);
    }
}
