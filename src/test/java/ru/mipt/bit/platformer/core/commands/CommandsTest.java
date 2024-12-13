package ru.mipt.bit.platformer.core.commands;

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
        new MoveCommand.Right(tank).execute();
        assertEquals(3, tank.getDestinationCoordinates().x);
        assertEquals(0, tank.getDestinationCoordinates().y);

        new MoveCommand.Up(tank).execute();
        assertEquals(3, tank.getDestinationCoordinates().x);
        assertEquals(1, tank.getDestinationCoordinates().y);

        new MoveCommand.Down(tank).execute();
        assertEquals(3, tank.getDestinationCoordinates().x);
        assertEquals(0, tank.getDestinationCoordinates().y);

        new MoveCommand.Left(tank).execute();
        assertEquals(2, tank.getDestinationCoordinates().x);
        assertEquals(0, tank.getDestinationCoordinates().y);
    }
}
