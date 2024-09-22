package ru.mipt.bit.platformer.core;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.core.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TankTest {
    @Test
    public void tankMoveUp() {
        Tank tank = new Tank();
        tank.move(new GridPoint2(3, 3), Direction.UP);

        GridPoint2 expectedCoord = new GridPoint2(1, 1);
        float expectedRotation = 90;

        assertEquals(expectedCoord, tank.getPlayerDestinationCoordinates());
        assertEquals(expectedRotation, tank.getPlayerRotation(), 0.01f);
    }

    @Test
    public void tankMoveDown() {
        Tank tank = new Tank();
        tank.move(new GridPoint2(3, 3), Direction.DOWN);

        GridPoint2 expectedCoord = new GridPoint2(1, -1);
        float expectedRotation = -90;

        assertEquals(expectedCoord, tank.getPlayerDestinationCoordinates());
        assertEquals(expectedRotation, tank.getPlayerRotation(), 0.01f);
    }

    @Test
    public void tankMoveRight() {
        Tank tank = new Tank();
        tank.move(new GridPoint2(3, 3), Direction.RIGHT);

        GridPoint2 expectedCoord = new GridPoint2(2, 0);
        float expectedRotation = 0;

        assertEquals(expectedCoord, tank.getPlayerDestinationCoordinates());
        assertEquals(expectedRotation, tank.getPlayerRotation(), 0.01f);
    }

    @Test
    public void tankMoveLeft() {
        Tank tank = new Tank();
        tank.move(new GridPoint2(3, 3), Direction.LEFT);

        GridPoint2 expectedCoord = new GridPoint2(0, 0);
        float expectedRotation = -180;

        assertEquals(expectedCoord, tank.getPlayerDestinationCoordinates());
        assertEquals(expectedRotation, tank.getPlayerRotation(), 0.01f);
    }

    @Test
    public void tankMoveUpWithObstacle() {
        Tree tree = new Tree(new GridPoint2(1, 1), 0f);
        Tank tank = new Tank();
        tank.move(tree.getCoordinates(), Direction.UP);

        GridPoint2 expectedCoord = new GridPoint2(1, 0);
        float expectedRotation = 90;

        assertEquals(expectedCoord, tank.getPlayerDestinationCoordinates());
        assertEquals(expectedRotation, tank.getPlayerRotation(), 0.01f);
    }


    @Test
    public void tankMoveRightWithObstacle() {
        Tree tree = new Tree(new GridPoint2(2, 0), 0f);
        Tank tank = new Tank();
        tank.move(tree.getCoordinates(), Direction.RIGHT);

        GridPoint2 expectedCoord = new GridPoint2(1, 0);
        float expectedRotation = 0;

        assertEquals(expectedCoord, tank.getPlayerDestinationCoordinates());
        assertEquals(expectedRotation, tank.getPlayerRotation(), 0.01f);
    }

    @Test
    public void tankMoveDownWithObstacle() {
        Tree tree = new Tree(new GridPoint2(1, -1), 0f);
        Tank tank = new Tank();
        tank.move(tree.getCoordinates(), Direction.DOWN);

        GridPoint2 expectedCoord = new GridPoint2(1, 0);
        float expectedRotation = -90;

        assertEquals(expectedCoord, tank.getPlayerDestinationCoordinates());
        assertEquals(expectedRotation, tank.getPlayerRotation(), 0.01f);
    }

    @Test
    public void tankMoveLeftWithObstacle() {
        Tree tree = new Tree(new GridPoint2(0, 0), 0f);
        Tank tank = new Tank();
        tank.move(tree.getCoordinates(), Direction.LEFT);

        GridPoint2 expectedCoord = new GridPoint2(1, 0);
        float expectedRotation = -180;

        assertEquals(expectedCoord, tank.getPlayerDestinationCoordinates());
        assertEquals(expectedRotation, tank.getPlayerRotation(), 0.01f);
    }
}