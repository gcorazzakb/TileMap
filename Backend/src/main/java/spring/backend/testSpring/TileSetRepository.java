package spring.backend.testSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TileSetRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


}
