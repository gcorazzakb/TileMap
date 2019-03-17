package company.Map;

import company.Tiles.Tile;
import company.Tiles.TileSet;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class GameMap {

    private static TileSet grass, dirt, snow, all;
    public static final int layerHeight=5;
    MapPart part;

    public GameMap() {
        part = new MapPart(0, 0, 20, 20);
    }

    public BufferedImage drawMap(long X, long Y, int width, int height) {
        int scale = 3;
        Tile[][][] map = part.getMap();

        width = map[0].length;
        height = map[0][0].length;
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
}
