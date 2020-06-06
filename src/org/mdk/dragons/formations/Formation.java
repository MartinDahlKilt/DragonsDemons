package org.mdk.dragons.formations;

import org.mdk.dragons.actors.Actor;

import java.util.ArrayList;
import java.util.List;

public abstract class Formation {
    private List<Actor> mActors = new ArrayList<>();

    public List<Actor> getActors() {
        return mActors;
    }

    public void addActor(Actor actor) {
        mActors.add(actor);
    }

    public boolean isActive() {
        for(Actor a : mActors) {
            if(a.getStatus() == Actor.Status.CONSCIOUS) {
                return true;
            }
        }
        return false;
    }
}
