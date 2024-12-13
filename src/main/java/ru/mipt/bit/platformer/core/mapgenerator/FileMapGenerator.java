package ru.mipt.bit.platformer.core.mapgenerator;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.Direction;
import ru.mipt.bit.platformer.core.commands.GameObjectsManager;
import ru.mipt.bit.platformer.core.commands.IGameObject;
import ru.mipt.bit.platformer.core.objects.MapNavigator;
import ru.mipt.bit.platformer.core.objects.Tank;
import ru.mipt.bit.platformer.core.objects.Tree;

import java.io.FileReader;
import java.util.*;

public class FileMapGenerator implements IMapGenerator {
    private final String file;
    private static final String TankChar = "X";
    private static final String TreeChar = "T";
    private Tank tank;
    private List<Tank> tanks;
    private List<Tree> trees;
    private List<IGameObject> gameObjects;
    private GameObjectsManager mgr;

    public FileMapGenerator(String file) {
        this.file = file;
    }

    @Override
    public void generate() {
        List<List<String>> charmap = readMapFromFile(file);
        gameObjects = new ArrayList<>();

        Collections.reverse(charmap);
        trees = new ArrayList<>();
        ArrayList<Tank> loctanks = new ArrayList<>();

        int height = charmap.size();
        int width = charmap.get(0).size();

        mgr = new GameObjectsManager(gameObjects);

        for (int i = 0; i < charmap.size(); i++) {
            for (int j = 0; j < charmap.get(i).size(); j++) {
                var currPos = new GridPoint2(j, i);
                if (charmap.get(i).get(j).equals(TankChar)) {
                    var loctank = new Tank(currPos, Direction.UP, 100);
                    loctanks.add(loctank);
                    gameObjects.add(loctank);
                    loctank.setMapNavigator(new MapNavigator(width, height, mgr, loctank));
                } else if (charmap.get(i).get(j).equals(TreeChar)) {
                    var tree = new Tree(currPos, 0);
                    gameObjects.add(tree);
                    trees.add(tree);
                }
            }
        }
        tanks = new ArrayList<>(loctanks);
        tank = tanks.remove(tanks.size() - 1);
    }

    private List<List<String>> readMapFromFile(String file) {
        List<List<String>> charmap = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new FileReader(file));
            while (scanner.hasNextLine()) {
                charmap.add(Arrays.asList(scanner.nextLine().split("")));
            }
        } catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }
        return charmap;
    }

    @Override
    public Tank getPlayerTank() {
        return tank;
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
}