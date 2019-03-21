package spring.backend;

import company.Tiles.Tile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;

@Repository
@Transactional
public class TileRepository extends JdbcDaoSupport {


    public TileRepository(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    public int insertTile(Tile tile) throws IOException, SQLException {

        BufferedImage img = tile.getImg();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( img, "png", baos );
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
}
