package org.mdk.dragons.actors;

import org.mdk.dragons.Dice;
import org.mdk.dragons.skills.Equipment;
import org.mdk.dragons.skills.Skills;
import org.mdk.dragons.weapons.Weapon;
import org.mdk.dragons.world.Battlefield;

import java.util.List;

public abstract class Actor {
    public enum Status {
        DEAD,
        UNCONSCIOUS,
        CONSCIOUS
    }

    protected String mName;

    protected Status mStatus;
    protected Stats mStats;
    protected Body mBody;
    protected Skills mSkills;
    protected Equipment mEquipment;

    public Actor(Body body) {
        setStatus(Status.CONSCIOUS);
        mBody = body;
    }

    public void setStats(Stats stats) {
        mStats = stats;
        mBody.setTotalHealth((mStats.get(Stats.Type.PHYSIQUE) + mStats.get(Stats.Type.SIZE))/2);
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public int getInitiative() {
        return  Dice.roll(10) + mStats.get(Stats.Type.AGILITY);
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


    public abstract List<Action> getActions(Battlefield bf);
    protected abstract int getNumberOfActions();

    protected boolean hasActionCapability(Action.Type type) {
        return mSkills.hasSkillType(type) && mEquipment.hasSkillTypeItem(type);
    }

}
