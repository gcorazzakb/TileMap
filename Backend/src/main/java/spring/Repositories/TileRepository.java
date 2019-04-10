package spring.Repositories;

import company.Tiles.Tile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TileRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertTile(Tile tile) throws IOException {
        BufferedImage img = tile.getImg();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
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
        List<Tile> query = jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT img FROM Tile WHERE id=(?);");
                preparedStatement.setInt(1, id);
                return preparedStatement;
            }
        }, (rs, rowNum) -> {
            byte[] img = rs.getBytes(1);
            ByteArrayInputStream bis = new ByteArrayInputStream(img);
            try {
                BufferedImage bImage = ImageIO.read(bis);
                return new Tile(bImage, id);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });

        if (query.isEmpty()){
            return null;
        }else{
            return query.get(0);
        }

    }
}
