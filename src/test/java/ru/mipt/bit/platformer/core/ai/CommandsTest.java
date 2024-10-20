package ru.mipt.bit.platformer.core.ai;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.Test;
import ru.mipt.bit.platformer.core.objects.MapNavigator;
import ru.mipt.bit.platformer.core.objects.Tank;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CommandsTest {
    @Test
    public void commands() {
        var tank = new Tank(new GridPoint2(1, 0), new GridPoint2(2, 0), new MapNavigator(7,7,new ArrayList<>(), new ArrayList<>(), 0));
        new TankCommands.Right(tank).execute();
        assertEquals(3, tank.getPlayerDestinationCoordinates().x);
        assertEquals(0, tank.getPlayerDestinationCoordinates().y);

        new TankCommands.Up(tank).execute();
        assertEquals(3, tank.getPlayerDestinationCoordinates().x);
        assertEquals(1, tank.getPlayerDestinationCoordinates().y);

        new TankCommands.Down(tank).execute();
        assertEquals(3, tank.getPlayerDestinationCoordinates().x);
        assertEquals(0, tank.getPlayerDestinationCoordinates().y);

        new TankCommands.Left(tank).execute();
        assertEquals(2, tank.getPlayerDestinationCoordinates().x);
        assertEquals(0, tank.getPlayerDestinationCoordinates().y);
    }
}
