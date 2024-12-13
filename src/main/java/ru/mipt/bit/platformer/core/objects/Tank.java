package ru.mipt.bit.platformer.core.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.Direction;
import ru.mipt.bit.platformer.core.IShooting;
import ru.mipt.bit.platformer.core.commands.IGameObject;

import java.util.Date;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.core.events.Events.SHOOT;
import static ru.mipt.bit.platformer.core.events.Events.TANK_BROKEN;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank implements Collidable, Movable, IGameObject, IShooting {
    private final float PROGRESS_ENABLED = 1f;
    private final float PROGRESS_DISABLED = 0f;
    private final float RELOAD_TIME = 500;

    private final float movementSpeed = 0.4f;

    private MapNavigator mapNavigator;

    private final GridPoint2 coordinates;
    private final GridPoint2 destinationCoordinates;
    private EPublisher ePublisher;
    private float playerMovementProgress = PROGRESS_ENABLED;
    private float playerRotation;
    private int hp;
    private long shotTime = new Date().getTime();
    private Direction direction;

    public Tank(GridPoint2 coordinates, Direction direction, int hp) {
        this.coordinates = coordinates;
        this.destinationCoordinates = coordinates.cpy().add(Direction.UP.getVector());
        this.direction = direction;
        this.hp = hp;
    }

    public void setMapNavigator(MapNavigator mapNavigator) {
        this.mapNavigator = mapNavigator;
    }

    public void setPublisher(EPublisher publ) {
        this.ePublisher = publ;
    }

    public boolean canMoveInThisTick() {
        return isEqual(playerMovementProgress, PROGRESS_ENABLED);
    }

    @Override
    public void move(Direction direction) {
        if (!canMoveInThisTick()) {
            return;
        }
        var futureTile = destinationCoordinates.cpy().add(direction.getVector());
        if (mapNavigator.isFreeTile(futureTile)) {
            destinationCoordinates.add(direction.getVector());
            resetMovementProgress();
        }
        playerRotation = direction.getRotation();
        this.direction = direction;
    }

    @Override
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    @Override
    public void processProgress(float deltaTime) {
        playerMovementProgress = continueProgress(playerMovementProgress, deltaTime, movementSpeed);
        if (isEqual(playerMovementProgress, PROGRESS_ENABLED)) {
            coordinates.set(destinationCoordinates);
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

    @Override
    public boolean isTakesTile(GridPoint2 point) {
        return coordinates.equals(point) || destinationCoordinates.equals(point);
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

    private void resetMovementProgress() {
        playerMovementProgress = PROGRESS_DISABLED;
    }


    @Override
    public void shoot() {
        long nowDate = new Date().getTime();
        if (nowDate - shotTime > RELOAD_TIME) {
            shotTime = nowDate;
            return;
        }

        ePublisher.fireEvent(SHOOT, new Bullet(this, direction, ePublisher, mapNavigator));
    }

    @Override
    public boolean sufferCollide(Collidable c) {
        hp -= c.getDamageCollision();

        if (hp <= 0) {
            ePublisher.fireEvent(TANK_BROKEN, this);
        }
        return true;
    }

    @Override
    public boolean doCollide(Collidable collidable) {
        return true;
    }
}
