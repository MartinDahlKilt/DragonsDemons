package org.mdk.dragons.actors;

import org.mdk.dragons.weapons.Weapon;

public abstract class Body {
    public enum BodyPart {
        HEAD,
        CHEST,
        STOMACH,
        LEFT_LEG,
        RIGHT_LEG,
        LEFT_ARM,
        RIGHT_ARM,
    }

    public abstract BodyPart getTargetBodyPart(Weapon.AttackType type);
}
