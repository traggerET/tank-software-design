package ru.mipt.bit.platformer.core.objects;

import ru.mipt.bit.platformer.core.events.Events;
import ru.mipt.bit.platformer.core.events.IListener;

import java.util.List;

import static ru.mipt.bit.platformer.core.events.Events.BULLET_STOPPED;
import static ru.mipt.bit.platformer.core.events.Events.TANK_BROKEN;

public class CollisionManager implements IListener {
    List<Collidable> collidables;

    public CollisionManager(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    public void manageCollisions() {
        for (int i = 0; i < collidables.size(); i++) {
            for (Collidable collidable : collidables) {
                if (!collidables.get(i).equals(collidable) &&
                        collidables.get(i).getCoordinates().equals(collidable.getCoordinates())) {
                    collidable.doCollide(collidables.get(i));
                    collidables.get(i).doCollide(collidable);
                }
            }
        }
    }

    @Override
    public void handle(Events event, Object object) {
        if (event.equals(Events.SHOOT)) {
            collidables.add((Bullet) object);
        }
        if (event.equals(BULLET_STOPPED)) {
            collidables.remove((Bullet) object);
        }
        if (event.equals(TANK_BROKEN)) {
            collidables.remove((Tank) object);
        }
    }
}
