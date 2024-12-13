package ru.mipt.bit.platformer.core.mapgenerator;

import ru.mipt.bit.platformer.core.commands.GameObjectsManager;
import ru.mipt.bit.platformer.core.objects.Tank;
import ru.mipt.bit.platformer.core.objects.Tree;

import java.util.List;

public interface IMapGenerator {
    void generate();
    Tank getPlayerTank();
    
    List<Tree> getTrees();

    List<Tank> getNpcTanks();

    GameObjectsManager getGameObjectsManager();
}
