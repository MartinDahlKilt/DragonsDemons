package org.mdk.dragons.world;

import org.mdk.dragons.actors.Actor;

public class SimpleBattlefield extends Battlefield {
    private static Position mPosition = new Position(0,0);

    @Override
    public double getDistance(Actor first, Actor second) {
        return 0; // Always close combat
    }

    @Override
    public Position getPositionOf(Actor a) {
        return mPosition;
    }
}
