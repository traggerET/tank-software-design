package ru.mipt.bit.platformer.core.mapgenerator;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.objects.MapNavigator;
import ru.mipt.bit.platformer.core.objects.Tank;
import ru.mipt.bit.platformer.core.objects.Tree;

import java.io.FileReader;
import java.util.*;

import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedY;

public class FileMapGenerator implements IMapGenerator {
    private static final String TankChar = "X";
    private static final String TreeChar = "T";
    private final Tank tank;
    private final List<Tank> tanks;
    private final List<Tree> trees;

    public FileMapGenerator(String file) {
        List<List<String>> charmap = readMapFromFile(file);

        Collections.reverse(charmap);
        trees = new ArrayList<>();
        ArrayList<Tank> loctanks = new ArrayList<>();

        int height = charmap.size();
        int width = charmap.get(0).size();

        for (int i = 0; i < charmap.size(); i++) {
            for (int j = 0; j < charmap.get(i).size(); j++) {
                var currPos = new GridPoint2(j, i);
                if (charmap.get(i).get(j).equals(TankChar)) {
                    loctanks.add(new Tank(currPos, incrementedY(currPos), new MapNavigator(width, height, trees, loctanks, loctanks.size())));
                } else if (charmap.get(i).get(j).equals(TreeChar)) {
                    trees.add(new Tree(currPos, 0));
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
    public Tank getTank() {
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
}