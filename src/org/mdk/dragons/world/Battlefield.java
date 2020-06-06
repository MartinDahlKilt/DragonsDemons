package org.mdk.dragons.world;

import org.mdk.dragons.actors.Actor;

import java.util.*;

public abstract class Battlefield {

    public class Team {
        public Team(String name, Actor actor) {
            mName = name;
            mActors.add(actor);
        }
        public String mName;
        public List<Actor> mActors = new ArrayList<>();
    }

    private List<Team> mTeams = new ArrayList<>();
    private Map<Actor, Team> mActorTeams = new HashMap<>();

    /* List of all friends on the battlefield */
    public List<Actor> getFriendsOf(Actor actor) {
        Team t = mActorTeams.get(actor);
        List<Actor> l = new ArrayList<>();
        if(t != null) {
            for(Actor a : t.mActors) {
                if(a != actor) {
                    l.add(a);
                }
            }
        }
        return l;
    }

    /* List of all foes on the battlefield */
    public List<Actor> getFoesOf(Actor actor) {
        List<Actor> l = new ArrayList<>();
        Team at = mActorTeams.get(actor);
        if(at != null) {
            for(Team t : mTeams) {
                if(at != t) {
                    l.addAll(t.mActors);
                }
            }
        }
        return l;
    }

    public Actor getClosestFoe(Actor actor) {
        List<Actor> foes = getFoesOf(actor);
        if(foes.size() > 1) {
            foes.sort(new ClosestActorDistanceComparator(actor));
            return foes.get(0);
        } else {
            return null;
        }
    }

    public List<Actor> getAllActors() {
        List<Actor> l = new ArrayList<>();
        for(Team t: mTeams) {
            l.addAll(t.mActors);
        }
        return l;
    }
    /* Get the distance in meters between two actors */
    public double getDistance(Actor first, Actor second) {
        return getPositionOf(first).distanceTo(getPositionOf(second));
    }

    public abstract Position getPositionOf(Actor a);

    public boolean registerActor(String team, Actor actor) {
        boolean found = false;
        Team activeTeam = null;
        for(Team t: mTeams) {
            if(t.mName.equals(team)) {
                t.mActors.add(actor);
                found = true;
                activeTeam = t;
                break;
            }
        }
        if(!found) {
            activeTeam = new Team(team, actor);
            mTeams.add(activeTeam);
        }
        if(activeTeam != null) {
            mActorTeams.put(actor, activeTeam);
        }
        return true;
    }

    public int getTeamCount() {
        return mTeams.size();
    }

    public int getMemberCount() {
        return mActorTeams.size();
    }

    public boolean hasTeam(String name) {
        for(Team t: mTeams) {
            if(t.mName.equals(name)) {
                return true;
            }
        }
        return false;
    }


    class ClosestActorDistanceComparator implements Comparator<Actor> {
        private Actor mCenter;

        public ClosestActorDistanceComparator(Actor center) {
            mCenter = center;
        }

        @Override
        public int compare(Actor o1, Actor o2) {
            double d1 = getDistance(mCenter, o1);
            double d2 = getDistance(mCenter, o2);
            if(d1 > d2) {
                return 1;
            } else if(d1 < d2) {
                return -1;
            } else {
                return 0;
            }

        }
    }
}
