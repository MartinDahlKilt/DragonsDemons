package org.mdk.dragons.simulation;

import org.mdk.dragons.actors.Actor;
import org.mdk.dragons.formations.Formation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Runner {
    private List<Formation> mFormations = new ArrayList<>();

    public Runner() {

    }

    private void doRound() {
        PriorityQueue<Actor> allActors = new PriorityQueue<>(new ActorInitiativeComparator());
        for(Formation f : mFormations) {
            List<Actor> actors = f.getActors();
            for(Actor a : actors) {
                a.getNextInitiative();
            }
            allActors.addAll(actors);
        }

        while(!allActors.isEmpty()) {
            Actor a = allActors.poll();
            if(a.getStatus() == Actor.Status.ACTIVE) {
                System.out.println("Initiative: " + a.getInitiative());
                // Find enemy
                // Choose action
                // Execute action
            }
        }
    }

    public void run() {
        boolean active = true;
        int round = 0;
        while(active) {
            round++;
            System.out.println("Round: " + round);
            active = false;
            doRound();
            for(Formation f: mFormations) {
                active |= f.isActive();
            }
        }
    }

    public void addFormation(Formation f) {
        mFormations.add(f);
    }

    private class ActorInitiativeComparator implements Comparator<Actor> {
        @Override
        public int compare(Actor o1, Actor o2) {
            int i1 = o1.getInitiative();
            int i2 = o2.getInitiative();
            return (i1 < i2) ? 1 : ((i1 == i2) ? 0 : -1);
        }
    }

}
