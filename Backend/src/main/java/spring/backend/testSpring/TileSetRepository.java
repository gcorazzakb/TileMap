package spring.backend.testSpring;

import company.Tiles.TileSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
public class TileSetRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTileSet(Integer[] foreignIDs){
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

}
