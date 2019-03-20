package spring.backend;

import company.Tiles.Tile;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;

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
            return getJdbcTemplate().queryForObject("INSERT INTO tile VALUES(?) RETURNING id;",
                    new Object[]{new SerialBlob(imageInByte)}, new int[]{Types.BLOB}, Integer.class);

    }
}
