package ru.mipt.bit.platformer.core.mapgenerator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RandomMapGeneratorTest {
    @Test
    public void mapgenerator() {
        var mapg = new RandomMapGenerator(7, 5, 3, 4);
        assertEquals(3, mapg.getNpcTanks().size());
        assertNotNull(mapg.getPlayerTank());
        assertEquals(3, mapg.getTrees().size());
    }
}
