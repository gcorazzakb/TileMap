package spring.Repositories;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void before(){
    }

    @Test
    public void getTileImgWithID10() {
        byte[] img = restTemplate.getForObject("http://localhost:" + port + "/getTileImg?tileID=10", byte[].class);
        assertEquals(img.length, 281);
    }

    @Test
    public void getTileInfoAboutTileWithID10() throws JSONException {
        String JOSNTile = restTemplate.getForObject("http://localhost:" + port + "/getTileInfo?tileID=10", String.class);
        JSONObject parsed = new JSONObject(JOSNTile);
        System.out.println(JOSNTile);
        int id = parsed.getInt("id");
        Assert.assertEquals(id, 10);
    }

    @Test
    public void getMap() {
    }
}
