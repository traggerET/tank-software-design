package ru.mipt.bit.platformer.core.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.commands.GameObjectsManager;
import ru.mipt.bit.platformer.core.commands.IGameObject;

public class MapNavigator {
    private final int height;
    private final int width;
    private final GameObjectsManager gameObjectsManager;
    private final IGameObject ownerObj;

    public MapNavigator(int width, int height, GameObjectsManager gameObjectsManager, IGameObject ownerObj) {
        this.height = height;
        this.width = width;
        this.gameObjectsManager = gameObjectsManager;
        this.ownerObj = ownerObj;
    }

    public boolean isFreeTile(GridPoint2 pos) {
        if (pos.x < 0 || pos.x >= width) {
            return false;
        }
        if (pos.y < 0 || pos.y >= height) {
            return false;
        }
        for (var obj: gameObjectsManager.getGameObjects()) {
            if (ownerObj == obj) {
                continue;
            }
            if (obj.isTakesTile(pos)) {
                return false;
            }
        }
        return true;
    }
}
