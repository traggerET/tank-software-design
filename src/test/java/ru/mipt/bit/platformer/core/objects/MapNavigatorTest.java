package ru.mipt.bit.platformer.core.objects;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.Test;
import ru.mipt.bit.platformer.core.ai.TankCommands;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MapNavigatorTest {
    @Test
    public void mapNavigator() {
        ArrayList<Tree> trees = new ArrayList<>();
        trees.add(new Tree(new GridPoint2(2, 3), 0));

        ArrayList<Tank> tanks = new ArrayList<>();
        tanks.add(new Tank(new GridPoint2(2, 3), new GridPoint2(3, 3), null));

        var mapn = new MapNavigator(7, 7, trees, tanks, 0);
        assertTrue(mapn.isFreeTile(new GridPoint2(5, 5)));
        assertFalse(mapn.isFreeTile(new GridPoint2(2, 3)));
    }
}
