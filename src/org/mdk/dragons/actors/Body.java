package org.mdk.dragons.actors;

import org.mdk.dragons.weapons.Weapon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class Body {
    private static HashMap<BodyPartGroup, List<Integer>> PARTS_HEALTH_MAP = new HashMap<>();

    static {
        Integer line[] = {0, 0, 1, 2, 3, 4};
        for(BodyPartGroup g : BodyPartGroup.values()) {
            if(g == BodyPartGroup.NONE) {
                continue;
            }
            for(int idx = 0; idx < line.length; idx++) {
                line[idx]++;
            }
            PARTS_HEALTH_MAP.put(g, Arrays.asList(line.clone()));

            if(g == BodyPartGroup.A) {
                line[1]++;
            } else if(g == BodyPartGroup.M || g == BodyPartGroup.N || g == BodyPartGroup.P || g == BodyPartGroup.R) {
                line[0] = 11;
            }
        }
    }

    public enum BodyPart {
        HEAD,
        CHEST,
        STOMACH,
        LEFT_LEG,
        RIGHT_LEG,
        LEFT_ARM,
        RIGHT_ARM,
    }

    public enum BodyPartGroup {
        NONE, A, B, C, D, E, F, G, H, I, J, K, L, M, N, P, R
    }

    private int mMaxHealthPoints = -1;
    private int mCurrentHealthPoints;
    private HashMap<BodyPart,Integer> mBodyPartHealth = new HashMap<>();

    private int mMaxPainPoints = -1;
    private int mCurrentPainPoints;
    private HashMap<BodyPart,Integer> mBodyPartPaint = new HashMap<>();

    public abstract BodyPart getTargetBodyPart(Weapon.AttackType type);

    public void setTotalHealth(int maxHealth) {
        if(mMaxHealthPoints == -1) {
            mMaxHealthPoints = maxHealth;
            mCurrentHealthPoints = mMaxHealthPoints;
            for (BodyPart b : BodyPart.values()) {
                BodyPartGroup g = getBodyPartGroup(b);
                if (g != BodyPartGroup.NONE) {
                    int value = getBodyPartHealth(g);
                    mBodyPartHealth.put(b, value);
                }
            }
        }
    }

    public void setTotalPaintPoints(int maxPainPoints) {
        if(mMaxPainPoints == -1) {
            mMaxPainPoints = maxPainPoints;
            mCurrentPainPoints = mMaxPainPoints;
            for (BodyPart b : BodyPart.values()) {
                BodyPartGroup g = getBodyPartGroup(b);
                if (g != BodyPartGroup.NONE) {
                    int value = getBodyPartHealth(g);
                    mBodyPartPaint.put(b, value);
                }
            }
        }
    }


    protected abstract BodyPartGroup getBodyPartGroup(BodyPart part);
    private int getBodyPartHealth(BodyPartGroup group) {
        int pos = 0;
        int mod = 0;
        if (mMaxHealthPoints <= 11) {
            pos = 0;
        } else if (mMaxHealthPoints <= 15) {
            pos = 1;
        } else if (mMaxHealthPoints <= 20) {
            pos = 2;
        } else if (mMaxHealthPoints <= 25) {
            pos = 3;
        } else if (mMaxHealthPoints <= 30) {
            pos = 4;
        } else if (mMaxHealthPoints <= 35) {
            pos = 5;
        } else {
            int val = Math.floorMod((mMaxHealthPoints - 35), 5);
            pos = 5;
            mod = val;
        }
        int health = PARTS_HEALTH_MAP.get(group).get(pos) + mod;
        return Math.min(health, mMaxHealthPoints);
    }

    public void applyDamage(BodyPart part, int value) {
        mCurrentHealthPoints += value;
        int val = mBodyPartHealth.get(part);
        val += value;
        mBodyPartHealth.put(part, val);
    }
}
