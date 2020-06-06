package org.mdk.dragons.simulation;

import org.mdk.dragons.world.SimpleBattlefield;

public class Duel extends Combat {

    public Duel() {
        super(new SimpleBattlefield());
    }


    @Override
    protected int getMaxMemberCount() {
        return 2;
    }

    @Override
    protected int getMaxTeamCount() {
        return 2;
    }
}
