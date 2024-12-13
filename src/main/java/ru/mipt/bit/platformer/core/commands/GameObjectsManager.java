package ru.mipt.bit.platformer.core.commands;

import ru.mipt.bit.platformer.core.events.Events;
import ru.mipt.bit.platformer.core.events.IListener;
import ru.mipt.bit.platformer.core.objects.Bullet;

import java.util.List;

public class GameObjectsManager implements IListener {
    private final List<IGameObject> gameObjects;

    public GameObjectsManager(List<IGameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    @Override
    public void handle(Events event, Object object) {
        if (event.equals(Events.TANK_BROKEN) || event.equals(Events.BULLET_STOPPED)) {
            gameObjects.removeIf(obj -> obj == object);
        }

        if (event.equals(Events.SHOOT)) {
            gameObjects.add((Bullet)object);
        }
    }

    public List<IGameObject> getGameObjects() {
        return gameObjects;
    }
}
