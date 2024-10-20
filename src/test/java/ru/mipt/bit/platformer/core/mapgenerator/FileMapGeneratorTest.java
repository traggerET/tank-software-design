package ru.mipt.bit.platformer.core.mapgenerator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileMapGeneratorTest {
    @Test
    public void mapgenerator() {
        var mapg = new FileMapGenerator("src/test/resources/testlevel.txt");
        assertEquals(1, mapg.getNpcTanks().size());
        assertNotNull(mapg.getTank());
        assertEquals(3, mapg.getTrees().size());
    }
}
