package spring.Repositories;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import company.Map.GameMap;
import company.Tiles.Tile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.Models.TileJSONModel;

import javax.imageio.ImageIO;

@RestController
public class Controller {

    @Autowired
    TileRepository tileRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/img", produces = "image/png")
    public byte[] getImgFromMap(@RequestParam(value="seed") String seed) {
        BufferedImage img = new GameMap(Integer.valueOf(seed)).drawMap();
        return imgToByteArray(img);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getTileImg", produces = "image/png")
    public byte[] getTileImg( @RequestParam(value="tileID") String tileID) {
        return imgToByteArray(tileRepository.loadTile(Integer.valueOf(tileID)).getImg());
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getTileInfo")
    public TileJSONModel getTileInfo(@RequestParam(value="tileID") String tileID) {
        return new TileJSONModel(tileRepository.loadTile(Integer.valueOf(tileID)));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getMapArray")
    public TileJSONModel[][][] getMap(@RequestParam(value="seed") String seed) {
        GameMap gameMap = new GameMap(Integer.valueOf(seed));
        Tile[][][] mapPart = gameMap.getMapPart();
        TileJSONModel[][][] mapModel = convertToJSONModel(mapPart);
        return mapModel;
    }

    private TileJSONModel[][][] convertToJSONModel(Tile[][][] mapPart) {
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

