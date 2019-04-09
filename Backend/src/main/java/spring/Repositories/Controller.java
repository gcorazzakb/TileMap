package spring.Repositories;


import java.awt.image.BufferedImage;

import company.Map.GameMap;
import company.Tiles.Tile;
import company.Tiles.TileSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import spring.Models.TileDto;
import spring.Models.TileSetDto;

import static Util.Util.imgToByteArray;
import static company.Tiles.Tile.convertToJSONModel;

@RestController
public class Controller {

    @Autowired
    TileRepository tileRepository;

    @Autowired
    TileSetRepository tileSetRepository;

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
    @GetMapping(value = "/getTileInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public TileDto getTileInfo(@RequestParam(value="tileID") String tileID) {
        return tileRepository.loadTile(Integer.valueOf(tileID)).toDto();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getMapArray")
    public TileDto[][][] getMap(@RequestParam(value="seed") String seed) {
        GameMap gameMap = new GameMap(Integer.valueOf(seed));
        Tile[][][] mapPart = gameMap.getMapPart();
        TileDto[][][] mapModel = convertToJSONModel(mapPart);
        return mapModel;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getTileSetIDs")
    public Integer[] getTileSetIDs() {
        Integer[] tileSetIds = tileSetRepository.getTileSetIds();
        return tileSetIds;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getTileSet")
    public TileSetDto getTileSet(@RequestParam(value = "tileSetID") String tileSetID ) {
        TileSet tileSet = tileSetRepository.getTileSet(Integer.valueOf(tileSetID));
        return tileSet.toDto();
    }

}

