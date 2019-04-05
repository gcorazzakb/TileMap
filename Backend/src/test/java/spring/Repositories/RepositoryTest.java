package spring.Repositories;

import company.Tiles.Tile;
import company.Tiles.TileSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.Repositories.TileRepository;
import spring.Repositories.TileSetRepository;

import static org.junit.Assert.*;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class RepositoryTest {

    @Autowired
    TileRepository tileRepository;

    @Autowired
    TileSetRepository tileSetRepository;

    @Test
    public void testThatItLoadsTileWithID1Correctly() {
        Tile tile = tileRepository.loadTile(1);
        assertTrue(tile.getId()==1);
    }

    @Test
    public void testThatItLoadsTileSetWithID1Correctly(){
        TileSet tileSet = tileSetRepository.getTileSet(1);
        assertTrue(tileSet!=null);
    }
}
