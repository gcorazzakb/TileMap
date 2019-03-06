package com.company.Tiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class TileIO {

    public static final int N_CODE = 0, E_CODE = 1, S_CODE = 2 , W_CODE = 3;

    public static TileSet loadTiles(String pathToTileImg, TileMask tileMask) throws IOException {
        Tile[][] mask = tileMask.mask;
        BufferedImage setImg = ImageIO.read(new File(pathToTileImg));
        HashSet<Tile> tiles = new HashSet<>();
        Map<Color, Color> colorMap = tileMask.genColorMap();

        for (int x = 0; x < mask.length; x++) {
            for (int y = 0; y < mask[0].length; y++) {

                TileEdge[] tileEdges=new TileEdge[4];

                for (int i = 0; i < 4; i++) {
                    TileEdge te=mask[x][y].borders[i];
                    tileEdges[i] = new TileEdge(colorMap.get(te.getColor()));
                }

                Tile tile = new Tile(tileEdges, mask[x][y].getHeights(), TileIO.img16(setImg, x, y));
                tiles.add(tile);
            }
        }
        return new TileSet(tileMask, colorMap, tiles);
    }


    public static BufferedImage img16(BufferedImage img, int x, int y){
        BufferedImage img16 = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        img16.getGraphics().drawImage(img,-x*16,-y*16,null);
        return img16;
    }

}
