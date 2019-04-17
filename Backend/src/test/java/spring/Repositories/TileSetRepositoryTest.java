package spring.Repositories;

import company.Tiles.TileSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TileSetRepositoryTest {

    @LocalServerPort
    private int port;

    @Autowired
    TileSetRepository tileSetRepository;

    @Test
    public void addTileSet() {
    }

    @Test
    public void getTileSet() {
    }

    @Test
    public void getTileSetIds() {
    }

    @Test
    public void getAllTileSets() {
    }

    @Test
    public void updateTile() {
        tileSetRepository.updateTile(7,1,99);
        TileSet tileSet = tileSetRepository.getTileSet(7);
//        tileSet.getTiles()
//        assertEquals();
    }
}
