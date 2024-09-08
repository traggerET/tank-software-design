package ru.mipt.bit.platformer.core;

import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank {
    private final float movementSpeed = 0.4f;

    private final GridPoint2 playerCoordinates = new GridPoint2(1, 0);
    private final GridPoint2 playerDestinationCoordinates = new GridPoint2(1, 1);
    private float playerMovementProgress = 1f;
    private float playerRotation;

    public boolean canMoveInThisTick() {
        return isEqual(playerMovementProgress, 1f);
    }

    public void moveUp(GridPoint2 obstacleCoordinate) {
        if (!obstacleCoordinate.equals(incrementedY(playerCoordinates))) {
            playerDestinationCoordinates.y++;
            playerMovementProgress = 0f;
        }
        playerRotation = 90f;
    }

    public void moveLeft(GridPoint2 obstacleCoordinate) {
        if (!obstacleCoordinate.equals(decrementedX(playerCoordinates))) {
            playerDestinationCoordinates.x--;
            playerMovementProgress = 0f;
        }
        playerRotation = -180f;
    }

    public void moveDown(GridPoint2 obstacleCoordinate) {
        if (!obstacleCoordinate.equals(decrementedY(playerCoordinates))) {
            playerDestinationCoordinates.y--;
            playerMovementProgress = 0f;
        }
        playerRotation = -90f;
    }

    public void moveRight(GridPoint2 obstacleCoordinate) {
        if (!obstacleCoordinate.equals(incrementedX(playerCoordinates))) {
            playerDestinationCoordinates.x++;
            playerMovementProgress = 0f;
        }
        playerRotation = 0f;
    }

    public void processMovementProgress(float deltaTime) {
        playerMovementProgress = continueProgress(playerMovementProgress, deltaTime, movementSpeed);
        if (isEqual(playerMovementProgress, 1f)) {
            playerCoordinates.set(playerDestinationCoordinates);
        }
    }

    public GridPoint2 getPlayerCoordinates() {
        return playerCoordinates;
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
}
