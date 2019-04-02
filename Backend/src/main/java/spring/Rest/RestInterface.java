package spring.Rest;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import company.Map.GameMap;
import company.Tiles.Tile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.Models.TileJSONModel;
import spring.Repositories.TileRepository;

import javax.imageio.ImageIO;

@RestController
public class RestInterface {
    @Autowired
    TileRepository tileRepository;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/img", method = RequestMethod.GET, produces = "image/png")
    public byte[] testSendImg(@RequestParam(value="seed") String seed) {
        BufferedImage img = new GameMap(Integer.valueOf(seed)).drawMap();
        return imgToByteArray(img);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getTileImg", method = RequestMethod.GET, produces = "image/png")
    public byte[] getTileImg( @RequestParam(value="tileID") String tileID) {
        return imgToByteArray(tileRepository.loadTile(Integer.valueOf(tileID)).getImg());
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getTileInfo")
    public String getTileInfo(@RequestParam(value="tileSetID") String tileSetID, @RequestParam(value="tileID") String tileID) {
        Tile tile = getTileBy(tileSetID, tileID);
        return "";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getMapArray")
    public TileJSONModel[][][] getMap(@RequestParam(value="seed") String seed) {
        GameMap gameMap = new GameMap(Integer.valueOf(seed));
        Tile[][][] mapPart = gameMap.getMapPart();
        TileJSONModel[][][] mapModel = new TileJSONModel[mapPart.length][mapPart[0].length][mapPart[0][0].length];
        for (int x = 0; x < mapPart.length; x++) {
            for (int y = 0; y < mapPart[0].length; y++) {
                for (int z = 0; z < mapPart[0][0].length; z++) {
                    if (mapPart[x][y][z]!=null) {
                        TileJSONModel tileJSONModel = new TileJSONModel(mapPart[x][y][z]);
                        mapModel[x][y][z] = tileJSONModel;
                    }
                }
            }
        }
        return mapModel;
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

