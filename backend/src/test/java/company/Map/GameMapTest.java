package company.Map;

import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class GameMapTest {

    private GameMap gm;

    @Before
    public void setUp() throws Exception {
        gm = new GameMap(100);
    }

    @Test
    public void getMapPart() {
        MapPart mapPart = gm.getMapPart();
        assertNotNull(mapPart);
        assertEquals(mapPart.SEED,100);
    }

    @Test
    public void drawMap() {
        BufferedImage bufferedImage = gm.drawMap();
        assertNotNull(bufferedImage);
        assertTrue(bufferedImage.getWidth()>0);
        assertTrue(bufferedImage.getHeight()>0);
    }

}
