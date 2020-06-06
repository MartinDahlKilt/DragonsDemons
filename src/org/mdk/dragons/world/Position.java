package org.mdk.dragons.world;

public class Position {
    private int mPosX;
    private int mPosY;

    public Position(int x, int y) {
        mPosX = x;
        mPosY = y;
    }

    public int getX() { return mPosX; }
    public int getY() { return mPosY; }


    public double distanceTo(Position to) {
        return Math.sqrt(Math.pow(getX()-to.getX(),2)+Math.pow(getY()-to.getY(),2));
    }
}
