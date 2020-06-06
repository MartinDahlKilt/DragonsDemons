package org.mdk.dragons.simulation;

import org.mdk.dragons.actors.Action;
import org.mdk.dragons.actors.Actor;
import org.mdk.dragons.world.Battlefield;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public abstract class Combat {
    private Battlefield mBattlefield;
    private int mRound;

    private class Member {
        public Member(Actor actor) {
            mActor = actor;
            mInitiative = actor.getInitiative();
        }

        Actor mActor;
        int mInitiative;
    }

    public Combat(Battlefield b) {
        registerBattlefield(b);
        mRound = 0;
    }

    private void registerBattlefield(Battlefield bf) {
        mBattlefield = bf;
    }

    public boolean registerActor(String team, Actor a) {
        //boolean newTeam = !mBattlefield.hasTeam(team);
        if(mBattlefield.getMemberCount() > getMaxMemberCount()) {
            return false;
        }
        // TODO : Handle team size and member size correctly
        mBattlefield.registerActor(team, a);
        return true;
    }

    protected abstract int getMaxMemberCount();
    protected abstract int getMaxTeamCount();


    public void run() {
        boolean active = true;
        while(active) {
            nextRound();
            System.out.println("Round: " + mRound);
            active = false;

        }
    }

    private void nextRound() {
        mRound++;
        PriorityQueue<Member> allActors = new PriorityQueue<>(new ActorInitiativeComparator());
        for(Actor a : mBattlefield.getAllActors()) {
            allActors.add(new Member(a));
        }

        while(!allActors.isEmpty()) {
            Member m = allActors.poll();
            if(m.mActor.getStatus() == Actor.Status.CONSCIOUS) {
                List<Action> list = m.mActor.getActions(mBattlefield);
                for(Action a : list) {
                    execute(a);
                }
            }
        }
    }


    private class ActorInitiativeComparator implements Comparator<Member> {
        public ActorInitiativeComparator() {
        }

        @Override
        public int compare(Member o1, Member o2) {
            return Integer.compare(o1.mInitiative, o2.mInitiative);
        }
    }

    public void execute(Action action) {
        if(action.getType() == Action.Type.MOVE) {
            // Do move action
        } else if(action.getType() == Action.Type.NOTHING) {
            // Do nothing
        } else if(action.getType() == Action.Type.MAGIC_PREPARE) {
            // Do magic attack
        } else if(action.getType() == Action.Type.MAGIC_ATTACK) {
            // Do magic attack
        } else if(action.getType() == Action.Type.MELEE_ATTACK) {
            // Do combat attack
        } else if(action.getType() == Action.Type.RANGE_ATTACK) {
            // Do range attack
        } else if(action.getType() == Action.Type.USE_ITEM) {
            // Do range attack
        } else if(action.getType() == Action.Type.CHANGE_WEAPON) {
            // Do range attack
        }
    }

}
