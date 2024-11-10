package ru.mipt.bit.platformer.core.objects;

import com.badlogic.gdx.math.GridPoint2;

public class Tree implements Collidable {
    private final GridPoint2 treeObstacleCoordinates;
    private final float rotation;

    public Tree(GridPoint2 treeObstacleCoordinates, float rotation) {
        this.treeObstacleCoordinates = treeObstacleCoordinates;
        this.rotation = rotation;
    }

    @Override
    public boolean sufferCollide(Collidable c) {
        return true;
    }

    @Override
    public boolean doCollide(Collidable collidable) {
        return true;
    }

    @Override
    public GridPoint2 getCoordinates() {
        return treeObstacleCoordinates;
    }

    @Override
    public int getDamageCollision() {
        return 0;
    }

    public float getRotation() {
        return rotation;
    }
}
