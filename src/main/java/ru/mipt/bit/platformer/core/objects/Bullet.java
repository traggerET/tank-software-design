package ru.mipt.bit.platformer.core.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.Direction;
import ru.mipt.bit.platformer.core.events.Events;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Bullet implements Collidable {
    private static int BULLET_RANGE = 5;
    private static int BULLET_DAMAGE = 10;

    private final float rotation;
    private float movementProgress = 0f;
    private float movementProgressCnt = 0f;

    private final Tank tank;
    private final Direction direction;
    private final GridPoint2 coordinates;
    private final GridPoint2 destinationCoordinates;
    private final EPublisher em;

    public Bullet(Tank sourceTank, Direction direction, EPublisher em) {
        this.tank = sourceTank;
        this.rotation = sourceTank.getPlayerRotation();
        this.direction = direction;

        this.coordinates = new GridPoint2(sourceTank.getCoordinates());
        this.coordinates.add(this.direction.getVector());

        this.destinationCoordinates = new GridPoint2(this.coordinates);
        this.destinationCoordinates.x += BULLET_RANGE * this.direction.getVector().x;
        this.destinationCoordinates.y += BULLET_RANGE * this.direction.getVector().y;
        this.em = em;
    }

    @Override
    public boolean sufferCollide(Collidable collidable) {
        em.fireEvent(Events.BULLET_STOPPED, this);
        return true;
    }

    @Override
    public boolean doCollide(Collidable c) {
        if (c != tank) {
            c.sufferCollide(this);
        }

        em.fireEvent(Events.BULLET_STOPPED, this);
        return true;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    @Override
    public int getDamageCollision() {
        return BULLET_DAMAGE;
    }

    public void processMovementProgress(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, 1f);

        if (movementProgress - movementProgressCnt > 0.3f) {
            movementProgressCnt += .2f;
            coordinates.add(direction.getVector());
        }
    }

    public float getRotation() {
        return rotation;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }
}
