package ru.mipt.bit.platformer.core.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.Direction;
import ru.mipt.bit.platformer.core.commands.IGameObject;
import ru.mipt.bit.platformer.core.events.Events;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Bullet implements Collidable, IGameObject {
    private static int BULLET_RANGE = 10;
    private static int BULLET_DAMAGE = 10;

    private static float DELTA = 0.4f;
    private static float DELTA_PROGRESS = 0.2f;
    private static float SPEED = 1.0f;


    private final float rotation;
    private final MapNavigator mapNavigator;
    private float movementProgress = 0f;
    private float counterProgress = 0f;

    private final Tank tank;
    private final Direction direction;
    private final GridPoint2 coordinates;
    private final GridPoint2 destinationCoordinates;
    private final EPublisher ePublisher;

    public Bullet(Tank sourceTank, Direction direction, EPublisher ePublisher, MapNavigator mapNavigator) {
        this.tank = sourceTank;
        this.rotation = sourceTank.getPlayerRotation();
        this.direction = direction;

        this.coordinates = new GridPoint2(sourceTank.getCoordinates());
        this.coordinates.add(this.direction.getVector());

        this.destinationCoordinates = new GridPoint2(this.coordinates);
        this.destinationCoordinates.x += BULLET_RANGE * this.direction.getVector().x;
        this.destinationCoordinates.y += BULLET_RANGE * this.direction.getVector().y;
        this.ePublisher = ePublisher;
        this.mapNavigator =mapNavigator;
    }

    @Override
    public boolean sufferCollide(Collidable collidable) {
        ePublisher.fireEvent(Events.BULLET_STOPPED, this);
        return true;
    }

    @Override
    public boolean doCollide(Collidable c) {
        if (c != tank) {
            c.sufferCollide(this);
        }

        ePublisher.fireEvent(Events.BULLET_STOPPED, this);
        return true;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    @Override
    public int getDamageCollision() {
        return BULLET_DAMAGE;
    }

    @Override
    public void processProgress(float deltaTime) {
        if (!mapNavigator.isFreeTile(coordinates)) {
            ePublisher.fireEvent(Events.BULLET_STOPPED, this);
            return;
        }

        movementProgress = continueProgress(movementProgress, deltaTime, SPEED);

        if (movementProgress - counterProgress > DELTA) {
            counterProgress += DELTA_PROGRESS;
            coordinates.add(direction.getVector());
        }
    }

    public float getRotation() {
        return rotation;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    @Override
    public boolean isTakesTile(GridPoint2 point) {
        return false;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }
}
