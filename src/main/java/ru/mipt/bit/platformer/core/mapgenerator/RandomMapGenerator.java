package ru.mipt.bit.platformer.core.mapgenerator;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.objects.MapNavigator;
import ru.mipt.bit.platformer.core.objects.Tank;
import ru.mipt.bit.platformer.core.objects.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedY;

public class RandomMapGenerator implements IMapGenerator{
    private final Tank tank;
    private final List<Tree> trees = new ArrayList<>();

    public RandomMapGenerator(int width, int height, int treesNum) {
        Set<GridPoint2> generated = new HashSet<>();

        var tankCoords = newRandomCoordinates(width, height);
        generated.add(tankCoords);

        var treeCoords = generateRandomCoordinates(generated, treesNum, width, height);
        createTrees(treeCoords);

        tank = new Tank(tankCoords, incrementedY(tankCoords), new MapNavigator(width, height, trees));
    }

    private Set<GridPoint2> generateRandomCoordinates(Set<GridPoint2> excludeCoords, int num, int width, int height) {
        Set<GridPoint2> randomCoordinates = new HashSet<>();

        for (int i = 0; i < num; i++) {
            var randCoords = newRandomCoordinates(width, height);
            while (excludeCoords.contains(randCoords)) {
                randCoords = newRandomCoordinates(width, height);
            }
            excludeCoords.add(randCoords);
            randomCoordinates.add(randCoords);
        }
        return randomCoordinates;
    }

    private GridPoint2 newRandomCoordinates(int width, int height) {
        return new GridPoint2((int) (Math.random() * (width - 1)), (int) (Math.random() * (height - 1)));
    }

    private void createTrees(Set<GridPoint2> treeCoords) {
        for (var treeCoord: treeCoords) {
            trees.add(new Tree(treeCoord, 0));
        }
    }

    @Override
    public List<Tree> getTrees() {
        return trees;
    }

    @Override
    public Tank getTank() {
        return tank;
    }
}