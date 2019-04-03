package spring.Repositories;

import company.Tiles.Tile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class TileRepositoryTest {

    @Autowired
    TileRepository tileRepository;

    @Test
    public void insertTile() {
        assertTrue(true);
    }

    @Test
    public void loadTile() {
        Tile tile = tileRepository.loadTile(1);
        assertTrue(tile.getId()==1);
    }
}
