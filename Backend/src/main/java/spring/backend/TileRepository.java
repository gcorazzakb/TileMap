package spring.backend;

import company.Tiles.Tile;
import company.Tiles.TileSet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@Repository
@Transactional
public class TileRepository extends JdbcDaoSupport {


    public TileRepository(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    public int insertTile(Tile tile) throws IOException {

        BufferedImage img = tile.getImg();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  tile (img) VALUES(?);", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setBytes(1, imageInByte);
                return preparedStatement;
            }
        }, generatedKeyHolder);
        return (int) generatedKeyHolder.getKeyList().get(0).get("id");
    }

    public Tile loadTile(int id) {
        return getJdbcTemplate().queryForObject("SELECT img FROM Tile WHERE id=" + id+ ";", (resultSet, i) -> {
            byte[] img = resultSet.getBytes(1);
            ByteArrayInputStream bis = new ByteArrayInputStream(img);
            try {
                BufferedImage bImage = ImageIO.read(bis);
                return new Tile(bImage, id);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }


}
