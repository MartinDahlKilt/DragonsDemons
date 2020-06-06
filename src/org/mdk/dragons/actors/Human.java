package org.mdk.dragons.actors;

import org.mdk.dragons.world.Battlefield;

import java.util.List;

public class Human extends Actor {

    public Human() { super(new HumanBody());}

    @Override
    protected int getNumberOfActions() {
        int count = 3;
        if(mStats.getGroup(Stats.Type.AGILITY) > 5) {
            count++;
        }
        if(mStats.getSwordHand()== Stats.SWORD_HAND.AMBIDEXTROUS) {
            count++;
        }
        return count;
    }

    @Override
    public List<Action> getActions(Battlefield bf) {
        return null;
    }
}
