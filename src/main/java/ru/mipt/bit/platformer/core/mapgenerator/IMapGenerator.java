package ru.mipt.bit.platformer.core.mapgenerator;

import ru.mipt.bit.platformer.core.objects.Tank;
import ru.mipt.bit.platformer.core.objects.Tree;

import java.util.List;

public interface IMapGenerator {
    Tank getTank();
    
    List<Tree> getTrees();
}
