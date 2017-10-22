package org.mdk.dragons.actors;

import org.mdk.dragons.Dice;
import org.mdk.dragons.weapons.Weapon;

public class HumanBody extends Body {
    @Override
    public BodyPart getTargetBodyPart(Weapon.AttackType type) {
        int val = Dice.roll(20);
        if(type == Weapon.AttackType.RANGE) {
            if(val >= 1 && val <= 3) {
                return BodyPart.LEFT_LEG;
            } else if(val >= 4 && val <= 6) {
                return BodyPart.RIGHT_LEG;
            } else if(val >= 7 && val <= 9) {
                return BodyPart.STOMACH;
            } else if(val >= 10 && val <= 11) {
                return BodyPart.LEFT_ARM;
            } else if(val >= 12 && val <= 13) {
                return BodyPart.RIGHT_ARM;
            } else if(val >= 14 && val <= 18) {
                return BodyPart.CHEST;
            } else {
                return BodyPart.HEAD;
            }
        } else  {
            if(val >= 1 && val <= 2) {
                return BodyPart.LEFT_LEG;
            } else if(val >= 3 && val <= 4) {
                return BodyPart.RIGHT_LEG;
            } else if(val >= 5 && val <= 8) {
                return BodyPart.STOMACH;
            } else if(val >= 9 && val <= 11) {
                return BodyPart.LEFT_ARM;
            } else if(val >= 12 && val <= 14) {
                return BodyPart.RIGHT_ARM;
            } else if(val >= 15 && val <= 16) {
                return BodyPart.CHEST;
            } else {
                return BodyPart.HEAD;
            }

        }
    }

    @Override
    protected BodyPartGroup getBodyPartGroup(BodyPart part) {
        switch(part) {
            case RIGHT_LEG:
            case LEFT_LEG:
            case STOMACH:
            case HEAD:
                return BodyPartGroup.D;
            case LEFT_ARM:
            case RIGHT_ARM:
                return BodyPartGroup.C;
            case CHEST:
                return BodyPartGroup.F;
            default:
                return BodyPartGroup.NONE;

        }
    }
}
