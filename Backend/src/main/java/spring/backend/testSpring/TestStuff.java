package spring.backend.testSpring;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import company.Map.GameMap;
import company.Tiles.Tile;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;

@RestController
public class TestStuff {

    @CrossOrigin(origins = "*")
    @RequestMapping("/greeting")
    public TestPOJO greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new TestPOJO(name.toUpperCase());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/img", method = RequestMethod.GET, produces = "image/png")
    public byte[] testSendImg(@RequestParam(value="seed") String seed) {
        BufferedImage img = new GameMap(Integer.valueOf(seed)).drawMap();
        return imgToByteArray(img);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getTileImg", method = RequestMethod.GET, produces = "image/png")
    public byte[] getTileImg(@RequestParam(value="tileSetID") String tileSetID, @RequestParam(value="tileID") String tileID) {
        Tile tile = getTileBy(tileSetID, tileID);
        return imgToByteArray(tile.getImg());
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getTileInfo")
    public String getTileInfo(@RequestParam(value="tileSetID") String tileSetID, @RequestParam(value="tileID") String tileID) {
        Tile tile = getTileBy(tileSetID, tileID);
        return "";
    }

    private Tile getTileBy(String tileSet, String tile){
        Integer tileSetID = Integer.valueOf(tileSet);
        Integer tileID = Integer.valueOf(tile);
        return GameMap.tileSets.get(tileSetID).getTile(tileID);
    }

    private byte[] imgToByteArray(BufferedImage img){
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        // Write to output stream
        try {
            ImageIO.write(img, "png", bao);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bao.toByteArray();
    }

}

