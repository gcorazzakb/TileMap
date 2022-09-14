package spring.Repositories;

import company.Tiles.Tile;
import company.Tiles.TileSet;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Repository
public class TileSetRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TileRepository tileRepository;

    public int addTileSet(Integer[] foreignIDs) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO tileset (tiles) VALUES(?);", new String[]{"id"});
                Array integer = connection.createArrayOf("INTEGER", foreignIDs);
                ps.setArray(1, integer);
                return ps;
            }
        }, generatedKeyHolder);
        return (int) generatedKeyHolder.getKeys().get("id");
    }


    public TileSet getTileSet(int id) {
        return jdbcTemplate.queryForObject("SELECT tiles FROM tileset WHERE id=" + id + ";", (resultSet, i) -> {
            Integer[] fk = (Integer[]) resultSet.getArray(1).getArray();
            Tile[] tiles = new Tile[48];

            for (int j = 0; j < fk.length; j++) {
                if (fk[j] != null)
                    tiles[j] = tileRepository.loadTile(fk[j]);
            }
            return new TileSet(tiles, id);
        });
    }

    public Integer[] getTileSetIds() {
        return jdbcTemplate.queryForObject("SELECT id FROM tileset;", (resultSet, i) -> {
            ArrayList<Integer> ids = new ArrayList<>();
            do {
                ids.add(resultSet.getInt(1));
            } while ((resultSet.next()));
            return ids.toArray(new Integer[0]);
        });
    }

    public ArrayList<TileSet> getAllTileSets() {
        ArrayList<TileSet> tileSets = new ArrayList<>();
        Integer[] tileSetIds = getTileSetIds();
        for (int i = 0; i < tileSetIds.length; i++) {
            Integer tileSetId = tileSetIds[i];
            TileSet tileSet = getTileSet(tileSetId);
            tileSets.add(tileSet);
        }
        return tileSets;
    }

    public void updateTile(int tileSetID, int tileIndex, int generatedTileID) {
        TileSet tileSet = getTileSet(tileSetID);
        Tile[] tiles = tileSet.getTiles();
        Integer[] tileSetIds = tilesIds(tiles);
        tileSetIds[tileIndex] = generatedTileID;
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.tileset SET tiles = (?) WHERE tileset.id = (?)");
                Array sqlTileSetIds = connection.createArrayOf("INTEGER", tileSetIds);
                preparedStatement.setArray(1, sqlTileSetIds);
                preparedStatement.setInt(2, tileSetID);
                return preparedStatement;
            }
        });
    }

    private Integer[] tilesIds(Tile[] tiles){
        return Arrays.stream(tiles).map(tile -> Optional.ofNullable(tile).map(t->t.getId()).orElse(null)).toArray(Integer[]::new);
    }
}
