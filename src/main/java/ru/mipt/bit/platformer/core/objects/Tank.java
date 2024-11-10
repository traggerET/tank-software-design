package ru.mipt.bit.platformer.core.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.Direction;
import ru.mipt.bit.platformer.util.GdxGameUtils;

import java.util.Date;
import java.util.function.Function;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.core.events.Events.SHOOT;
import static ru.mipt.bit.platformer.core.events.Events.TANK_BROKEN;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank implements Collidable {
    private final float PROGRESS_ENABLED = 1f;
    private final float PROGRESS_DISABLED = 0f;
    private final float RELOAD_TIME = 500;

    private final float movementSpeed = 0.4f;

    private final MapNavigator mapNavigator;

    private final GridPoint2 coordinates;
    private final GridPoint2 playerDestinationCoordinates;
    private final EPublisher em;
    private float playerMovementProgress = PROGRESS_ENABLED;
    private float playerRotation;
    private int hp;
    private long shotTime = new Date().getTime();
    private Direction direction;

    public Tank(GridPoint2 coordinates, GridPoint2 dstCoordinates, MapNavigator mapNavigator, Direction direction, EPublisher em) {
        this.coordinates = coordinates;
        this.playerDestinationCoordinates = dstCoordinates;
        this.mapNavigator = mapNavigator;
        this.em = em;
        this.direction = direction;
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

    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    @Override
    public int getDamageCollision() {
        return 0;
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
        this.direction = direction;
    }

    public void shoot() {
        long nowDate = new Date().getTime();
        if (nowDate - shotTime > RELOAD_TIME) {
            shotTime = nowDate;
            return;
        }

        em.fireEvent(SHOOT, new Bullet(this, direction, em));
    }

    @Override
    public boolean sufferCollide(Collidable c) {
        hp -= c.getDamageCollision();

        if (hp <= 0) {
            em.fireEvent(TANK_BROKEN, this);
        }
        return true;
    }

    @Override
    public boolean doCollide(Collidable collidable) {
        return true;
    }
}
