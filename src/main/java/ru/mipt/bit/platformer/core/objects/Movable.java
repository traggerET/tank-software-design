package ru.mipt.bit.platformer.core.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.Direction;

public interface Movable {
    void move(Direction direction);
    GridPoint2 getDestinationCoordinates();
}
