package org.mdk.dragons.simulation;

import org.mdk.dragons.actors.Actor;
import org.mdk.dragons.world.Battlefield;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Combat {
    private Battlefield mBattlefiled;
    private List<String> mTeams = new ArrayList<>();
    private List<Member> mMembers = new ArrayList<>();

    public class Member {
        public Actor mActor;
        public String mTeam;
    }

    public Combat(Battlefield b) {
        registerBattlefield(b);
    }

    private void registerBattlefield(Battlefield bf) {
        mBattlefiled = bf;
    }

    public boolean registerActor(String team, Actor a) {
        if(mMembers.size() > getMaxMemberCount()) {
            return false;
        }

        if(!isTeamRegistered(team)) {
            if(mTeams.size() > getMaxTeamCount()) {
                return false;
            }
            mTeams.add(team);
        }
        mMembers.add(new Member() { { mActor = a; mTeam = team; } });
        return true;
    }

    private boolean isTeamRegistered(String team) {
        return  mTeams.stream().filter( s -> s.equals(team)).findAny().orElse(null) != null;
    }

    public List<Member> getActors() {
        return mMembers;
    }

    protected abstract int getMaxMemberCount();
    protected abstract int getMaxTeamCount();
}
