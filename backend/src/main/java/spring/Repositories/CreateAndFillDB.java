package spring.Repositories;

import company.Map.GameMap;
import company.Tiles.Tile;
import company.Tiles.TileSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.ArrayList;

@SpringBootApplication
public class CreateAndFillDB implements CommandLineRunner {

    @Autowired
    private TileSetRepository tileSetRepository;

    @Autowired
    private TileRepository tileRepository;

    @Autowired
    private ApplicationContext appContext;

    public static void main(String[] args) {
        SpringApplication.run(CreateAndFillDB.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        DataSource ds = (DataSource) appContext.getBean("dataSource");
        Connection c = ds.getConnection();
        ScriptUtils.executeSqlScript(c, new EncodedResource(new ClassPathResource("./createDB.sql"), StandardCharsets.UTF_8));
        GameMap gameMap = new GameMap(0);
        ArrayList<TileSet> tileSets = GameMap.tileSets;
        for (TileSet tileSet : tileSets) {
            insertTileSet(tileSet);
        }
    }


    private void insertTileSet(TileSet tileSet) {
        Integer[] foreignIDs = new Integer[48];
        for (int i = 0; i < 48; i++) {
            Tile tile = tileSet.getTile(i);
            if (tile != null) {
                try {
                    foreignIDs[i] = tileRepository.insertTile(tile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        tileSetRepository.addTileSet(foreignIDs);
    }
}
