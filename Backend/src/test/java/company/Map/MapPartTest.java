package company.Map;

import company.Tiles.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MapPartTest {

    private MapPart mp;
    private GameMap gm;

    @Before
    public void setUp() throws Exception {
        gm = new GameMap(100);

    }

    @Test
    public void getMap() {
        mp= new MapPart(12,12,12,12,1);
        Tile[][][] map = mp.getMap();
        assertNotNull(map);
    }

    @Test
    public void genHeightMap() {
    }
}
