package org.mdk.dragons.skills;

import org.mdk.dragons.actors.Action;

import java.util.HashMap;
import java.util.Map;

public class Skills {
    private Map<String, Skill> mSkills = new HashMap<>();
    private Map<Action.Type, Boolean> mSkillTypeMap = new HashMap<>();

    public boolean hasSkill(String name) {
        return mSkills.containsKey(name);
    }

    public Skill getSkill(String name) {
        return mSkills.get(name);
    }

    public boolean hasSkillType(Action.Type type) {
        return mSkillTypeMap.containsKey(type);
    }

}
