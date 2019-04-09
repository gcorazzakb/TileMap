package company.Map;

import company.Tiles.Tile;
import company.Tiles.TileSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.Repositories.TileSetRepository;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static company.Tiles.TileSet.getBackendDir;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class GameMap {
    public static final int layerHeight = 5;
    MapPart part;
    final int seed;

    @Autowired
    TileSetRepository tileSetRepository;

    //public static final ArrayList<TileSet> tileSets= loadTileSetsPerImg();
    public static ArrayList<TileSet> tileSets;

    private void loadTileSets() {
        if(tileSets!=null){
            return;
        }

        if(tileSetRepository!=null){
            tileSets =  tileSetRepository.getAllTileSets();
            return;
        }

        tileSets= loadTileSetsPerImg();
    }

    private static ArrayList<TileSet> loadTileSetsPerDB() {
        ArrayList<TileSet> tileSets = new ArrayList<>();
        return tileSets;
    }

    private static ArrayList<TileSet> loadTileSetsPerImg() {
        ArrayList<TileSet> tileSets = new ArrayList<>();

        try {
            String backendDir = getBackendDir();
            tileSets.add(TileSet.loadSmallTileSet(backendDir + "./img/ground2/grassWater.png"));
            tileSets.add(TileSet.loadSmallTileSet(backendDir + "./img/ground2/grassAir.png"));
            tileSets.add(TileSet.loadSmallTileSet(backendDir + "./img/ground2/snowWater.png"));
            tileSets.add(TileSet.loadSmallTileSet(backendDir + "./img/ground2/snowAir.png"));
            tileSets.add(TileSet.loadSmallTileSet(backendDir + "./img/ground2/dirtWater.png"));
            tileSets.add(TileSet.loadSmallTileSet(backendDir + "./img/ground2/dirtAir.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return tileSets;
    }

    public GameMap(int seed) {
        loadTileSets();
        part = new MapPart(seed, 0, 60, 80, 100);
        this.seed = seed;
    }

    public BufferedImage drawMap() {
        int scale = 1;
        Tile[][][] map = part.getMap();

        int width = map[0].length;
        int height = map[0][0].length;
        BufferedImage mapImage = new BufferedImage(width * 16 * scale, height * 16 * scale, TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) mapImage.getGraphics();
        graphics.scale(scale, scale);


        for (int i = 0; i < layerHeight; i++) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Tile tile = map[i][x][y];
                    if (tile != null)
                        graphics.drawImage(tile.getImg(), x * 16, y * 16, null);
                }
            }
        }
        return mapImage;
    }

    public Tile[][][] getMapPart() {
        return part.getMap();
    }
}
