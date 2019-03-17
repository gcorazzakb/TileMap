package company.Map;

import company.PerlinNoise.PerlinNoise;
import company.Tiles.Tile;
import company.Tiles.TileSet;

import java.awt.*;
import java.io.IOException;

import static company.Map.GameMap.layerHeight;

public class MapPart {

    private static final int LAYERTOPTHREASH = 1;
    final int X, Y, width, height;

    private final float[][] heightMap;
    private final Tile[][][] map;
    private static final float threashold = 0.5f;
    private Color trans = Color.magenta, alpha = Color.BLACK, flat = Color.green;

    private TileSet grassWater, grassAir;

    {
        try {
            grassWater = TileSet.loadSmallTileSet("./img/ground2/grassWater.png", Color.BLUE, Color.green);
            grassAir = TileSet.loadSmallTileSet("./img/ground2/grassAir.png", Color.BLACK, Color.green);

            //grass = new TileSet("./img/ground2/grass2.png", Color.BLACK,Color.green);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MapPart(int x, int y, int width, int height) {
        X = x;
        Y = y;
        this.width = width;
        this.height = height;

        heightMap = genHeightMap(new float[width][height]);

        map = generateMap(heightMap);

    }

    public Tile[][][] getMap() {
        return map;
    }

    private Tile[][][] generateMap(float[][] heightMap) {
        Tile[][][] map = new Tile[layerHeight][width][height];

        for (int i = 0; i < layerHeight; i++) {
            Tile[][] l = genMapLayer(heightMap, i);
            map[i] = l;
        }
        return map;
    }

    private Tile[][] genMapLayer(float[][] heightMap, int l) {
        int[][] layer = getLayer(heightMap, l);

        if (l == 0 || l == 1) {
            return grassWater.paint(layer);
        } else {
            return grassAir.paint(layer);
        }
    }


    float[][] genHeightMap(float[][] heightMap) {
        PerlinNoise perlinNoise = new PerlinNoise(10, 10,1200);
        for (int x = 0; x < heightMap.length; x++) {
            for (int y = 0; y < heightMap[0].length; y++) {
                heightMap[x][y]=perlinNoise.getValue(x, y)*layerHeight;
            }
        }
        return heightMap;
    }

    private int[][] getLayer(float[][] heightMap, int l) {
        int[][] layer = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (heightMap[x][y] > l) {
                    if (heightMap[x][y] > l + 2) {
                        layer[x][y] = -1;
                    } else {
                        layer[x][y] = 1;
                    }

                }
            }
        }

        return layer;
    }


}
