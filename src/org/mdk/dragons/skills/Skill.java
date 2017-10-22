package org.mdk.dragons.skills;

import org.mdk.dragons.Dice;

import java.util.ArrayList;
import java.util.List;

public abstract class Skill {
    public enum Result {
        SUCCESS,
        FAILURE,
        SPECIAL,
        PERFECT,
        FUMBLE
    }

    private int mSkillValue;
    private String mName;

    public Result perform(int modifier) {
        int chance_value = Dice.roll(20);
        int second_chance_value = Dice.roll(20);

        int skill_value = mSkillValue + modifier;

        if (skill_value >= 20) {
            if(chance_value == 1 || (chance_value == 2 && second_chance_value <= (skill_value - 20))) {
                return Result.PERFECT;
            }
            int special_max = 5 + Math.floorMod((skill_value - 20), 3);
            if(chance_value >= 2 && chance_value <= special_max) {
                return Result.SPECIAL;
            } else if(chance_value == 20 && second_chance_value == 20) {
                return Result.FUMBLE;
            } else if(chance_value <= skill_value) {
                return Result.SUCCESS;
            } else {
                return Result.FAILURE;
            }
        } else if(skill_value >= 1 && skill_value <= 19) {
            if(chance_value == 1 && second_chance_value <= skill_value) {
                return Result.PERFECT;
            } else if(chance_value <= 5 && second_chance_value <= skill_value) {
                return Result.SPECIAL;
            } else if(chance_value == 20 && second_chance_value > skill_value) {
                return Result.FUMBLE;
            } else if(chance_value <= skill_value) {
                return Result.SUCCESS;
            } else {
                return Result.FAILURE;
            }
        }
        return Result.FAILURE;
    }

    public List<String> requiredItems() {
        return new ArrayList<>();
    }
}
