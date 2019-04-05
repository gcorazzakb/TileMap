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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest()
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ControllerWithoutServerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    Controller restController;

    @Test
    public void getTileImgWithID10() throws Exception {
        when(restController.getTileImg("10")).then(invocationOnMock -> new byte[]{0,1,2});
        mockMvc.perform(get("/getTileImg?tileID=10")).andExpect(status().isOk())
                .andExpect(content().bytes(new byte[]{0,1,2}));
    }

    @Test
    public void getTileInfoAboutTileWithID10() throws JSONException {
    }

    @Test
    public void getMap() {
    }
}
