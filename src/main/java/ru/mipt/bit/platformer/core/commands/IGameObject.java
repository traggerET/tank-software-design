package ru.mipt.bit.platformer.core.commands;

import com.badlogic.gdx.math.GridPoint2;

public interface IGameObject {
    boolean isTakesTile(GridPoint2 point);
    GridPoint2 getCoordinates();

    void processProgress(float delta);
}
