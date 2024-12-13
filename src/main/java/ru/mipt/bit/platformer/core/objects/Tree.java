package ru.mipt.bit.platformer.core.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.commands.IGameObject;

public class Tree implements Collidable, IGameObject {
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
    public boolean isTakesTile(GridPoint2 point) {
        return treeObstacleCoordinates.equals(point);
    }

    @Override
    public GridPoint2 getCoordinates() {
        return treeObstacleCoordinates;
    }

    @Override
    public void processProgress(float delta) {
    }

    @Override
    public int getDamageCollision() {
        return 0;
    }

    public float getRotation() {
        return rotation;
    }
}
