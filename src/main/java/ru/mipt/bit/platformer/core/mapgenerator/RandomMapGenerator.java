package ru.mipt.bit.platformer.core.mapgenerator;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.Direction;
import ru.mipt.bit.platformer.core.commands.GameObjectsManager;
import ru.mipt.bit.platformer.core.commands.IGameObject;
import ru.mipt.bit.platformer.core.objects.MapNavigator;
import ru.mipt.bit.platformer.core.objects.Tank;
import ru.mipt.bit.platformer.core.objects.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomMapGenerator implements IMapGenerator {
    private final int width;
    private final int height;
    private final int treesNum;
    private final int tanksNum;

    private Tank tank;
    private List<Tree> trees = new ArrayList<>();
    private List<Tank> tanks = new ArrayList<>();
    private List<IGameObject> gameObjects = new ArrayList<>();

    private GameObjectsManager mgr;


    public RandomMapGenerator(int width, int height, int treesNum, int tanksNum) {
        this.width = width;
        this.height = height;
        this.treesNum = treesNum;
        this.tanksNum = tanksNum;
    }

    @Override
    public void generate() {
        Set<GridPoint2> generated = new HashSet<>();

        gameObjects = new ArrayList<>();
        mgr = new GameObjectsManager(gameObjects);

        var treeCoords = generateRandomCoordinates(generated, treesNum, width, height);
        createTrees(treeCoords);

        Set<GridPoint2> tankCoords = generateRandomCoordinates(generated, tanksNum, width, height);
        createTanks(tankCoords, width, height);

        tank = tanks.remove(tanks.size() - 1);
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
            var tree = new Tree(treeCoord, 0);
            trees.add(tree);
            gameObjects.add(tree);
        }
    }

    private void createTanks(Set<GridPoint2> coords, int width, int height) {
        List<Tank> loctanks = new ArrayList<>();
        for (var coord: coords) {
            var loctank = new Tank(coord, Direction.UP, 100);
            loctank.setMapNavigator(new MapNavigator(width, height, mgr, loctank));
            loctanks.add(loctank);
            gameObjects.add(loctank);
        }
        tanks = new ArrayList<>(loctanks);
    }

    @Override
    public List<Tree> getTrees() {
        return trees;
    }

    @Override
    public List<Tank> getNpcTanks() {
        return tanks;
    }

    @Override
    public GameObjectsManager getGameObjectsManager() {
        return mgr;
    }

    @Override
    public Tank getPlayerTank() {
        return tank;
    }
}