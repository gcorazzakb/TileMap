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
    public void test_that_it_loads_tile_with_id10_correctly() {
        Tile tile = tileRepository.loadTile(1);
        assertTrue(tile.getId()==1);
    }

    @Test
    public void test_that_it_loads_tile_set_with_id10_correctly(){
        TileSet tileSet = tileSetRepository.getTileSet(1);
        assertTrue(tileSet!=null);
    }
}
