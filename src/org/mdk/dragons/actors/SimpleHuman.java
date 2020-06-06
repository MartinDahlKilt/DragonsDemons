package org.mdk.dragons.actors;

import org.mdk.dragons.world.Battlefield;

import java.util.ArrayList;
import java.util.List;

public class SimpleHuman extends Human {
    private Actor mCurrentTarget = null;
    private Action.Type mCurrentActionType = Action.Type.NOTHING;

    public SimpleHuman() {
        setStats(new RandomHumanStats());
    }

    @Override
    public List<Action> getActions(Battlefield bf) {
        List<Action> list = new ArrayList<>();
        int actionCount = getNumberOfActions();

        if(mCurrentTarget == null || mCurrentTarget.getStatus() != Status.CONSCIOUS) {
            mCurrentTarget = bf.getClosestFoe(this);
        }

        Action.Type prevActionType = mCurrentActionType;
        double range = bf.getDistance(this, mCurrentTarget);


        if(hasActionCapability(Action.Type.MELEE_ATTACK) && (range <= 2)) {
            mCurrentActionType = Action.Type.MELEE_ATTACK;
        } else if(hasActionCapability(Action.Type.RANGE_ATTACK) && (range >= 10)){
            mCurrentActionType = Action.Type.RANGE_ATTACK;
            actionCount = 1;
        }

        if(prevActionType!=mCurrentActionType) {
            Action a = new Action(this, null, Action.Type.CHANGE_WEAPON);
            list.add(a);
        } else {
            for (int idx = 0; idx < actionCount; idx++) {
                Action a = new Action(this, mCurrentTarget, mCurrentActionType);
                list.add(a);
            }
        }
        return list;
    }
}
