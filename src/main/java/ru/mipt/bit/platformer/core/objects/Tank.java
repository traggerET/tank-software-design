package ru.mipt.bit.platformer.core.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.Direction;
import ru.mipt.bit.platformer.util.GdxGameUtils;

import java.util.function.Function;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank {
    private final float PROGRESS_ENABLED = 1f;
    private final float PROGRESS_DISABLED = 0f;

    private final float movementSpeed = 0.4f;

    private final MapNavigator mapNavigator;

    private final GridPoint2 coordinates;
    private final GridPoint2 playerDestinationCoordinates;
    private float playerMovementProgress = PROGRESS_ENABLED;
    private float playerRotation;
    private int hp;

    public Tank(GridPoint2 coordinates, GridPoint2 dstCoordinates, MapNavigator mapNavigator) {
        this.coordinates = coordinates;
        this.playerDestinationCoordinates = dstCoordinates;
        this.mapNavigator = mapNavigator;
    }

    public boolean canMoveInThisTick() {
        return isEqual(playerMovementProgress, PROGRESS_ENABLED);
    }

    public void move(Direction direction) {
        if (direction == Direction.UP) {
            moveRelative(GdxGameUtils::incrementedY, direction);
        } else if (direction == Direction.DOWN) {
            moveRelative(GdxGameUtils::decrementedY, direction);
        } else if (direction == Direction.LEFT) {
            moveRelative(GdxGameUtils::decrementedX, direction);
        } else {
            moveRelative(GdxGameUtils::incrementedX, direction);
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

    public float getHp() {
        return hp;
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

    private void moveRelative(Function<GridPoint2, GridPoint2> moveFunc, Direction direction) {
        if (mapNavigator.isFreeTile(moveFunc.apply(playerDestinationCoordinates))) {
            setDestinationCoordinates(direction.getVector());
            resetMovementProgress();
        }
        playerRotation = direction.getRotation();
    }
}
