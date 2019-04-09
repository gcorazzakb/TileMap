package spring.Repositories;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest()
@RunWith(SpringRunner.class)
@WebAppConfiguration
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
                .andExpect(content().contentType(MediaType.IMAGE_PNG))
                .andExpect(content().bytes(new byte[]{0,1,2}));
    }

/*    @Test
    public void getTileInfoAboutTileWithID10() throws Exception {
        when(restController.getTileInfo("10")).then(invocationOnMock -> null);

        mockMvc.perform(get("/getTileInfo?tileID=10")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"id\":10,\"block\":null}"));
    }*/
}
