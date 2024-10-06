package ru.mipt.bit.platformer.core;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.GdxGameUtils;

import java.util.function.Function;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank {
    private final float PROGRESS_ENABLED = 1f;
    private final float PROGRESS_DISABLED = 0f;

    private final float movementSpeed = 0.4f;

    private final GridPoint2 coordinates = new GridPoint2(1, 0);
    private final GridPoint2 playerDestinationCoordinates = new GridPoint2(1, 0);
    private float playerMovementProgress = PROGRESS_ENABLED;
    private float playerRotation;

    public boolean canMoveInThisTick() {
        return isEqual(playerMovementProgress, PROGRESS_ENABLED);
    }

    public void move(GridPoint2 objectCoordinate, Direction direction) {
        if (direction == Direction.UP) {
            moveRelative(GdxGameUtils::incrementedY, objectCoordinate, direction);
        } else if (direction == Direction.DOWN) {
            moveRelative(GdxGameUtils::decrementedY, objectCoordinate, direction);
        } else if (direction == Direction.LEFT) {
            moveRelative(GdxGameUtils::decrementedX, objectCoordinate, direction);
        } else {
            moveRelative(GdxGameUtils::incrementedX, objectCoordinate, direction);
        }
    }

    public void processMovementProgress(float deltaTime) {
        playerMovementProgress = continueProgress(playerMovementProgress, deltaTime, movementSpeed);
        if (isEqual(playerMovementProgress, PROGRESS_ENABLED)) {
            coordinates.set(playerDestinationCoordinates);
        }
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public GridPoint2 getPlayerDestinationCoordinates() {
        return playerDestinationCoordinates;
    }

    public float getPlayerMovementProgress() {
        return playerMovementProgress;
    }

    public float getPlayerRotation() {
        return playerRotation;
    }

    private void setDestinationCoordinates(GridPoint2 vec) {
        playerDestinationCoordinates.x += vec.x;
        playerDestinationCoordinates.y += vec.y;
    }

    private void resetMovementProgress() {
        playerMovementProgress = PROGRESS_DISABLED;
    }

    private void moveRelative(Function<GridPoint2, GridPoint2> moveFunc, GridPoint2 objectCoordinate, Direction direction) {
        if (!objectCoordinate.equals(moveFunc.apply(coordinates))) {
            setDestinationCoordinates(direction.getVector());
            resetMovementProgress();
        }
        playerRotation = direction.getRotation();
    }
}
