package ru.mipt.bit.platformer.core.objects;

import com.badlogic.gdx.math.GridPoint2;

public interface Collidable {
    boolean sufferCollide(Collidable collidable);
    boolean doCollide(Collidable collidable);
    GridPoint2 getCoordinates();
    int getDamageCollision();
}
