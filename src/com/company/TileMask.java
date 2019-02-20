package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

class TileMask {

    public Tile[][] mask;
    private String folder;

    private static Random random= new Random(1337);

    public TileMask(String folder) throws IOException {
        BufferedImage maskImg = ImageIO.read(new File("img//"+folder+"//mask.png"));

        this.folder=folder;

        int resX=maskImg.getWidth()/16;
        int resY=maskImg.getHeight()/16;
        mask= new Tile[resX][resY];


        for (int x = 0; x < resX; x++) {
            for (int y = 0; y < resY; y++) {
                TileEdge[] tileEdges = TileIO.getTileEdges(maskImg, x, y);
                mask[x][y]=new Tile(tileEdges, null);
            }
        }
    }

    public TileSet loadTiles(String setname) throws IOException {

        BufferedImage setImg = ImageIO.read(new File("img//"+folder+"//"+setname+".png"));
        HashSet<Tile> tiles = new HashSet<>();
        Map<Color, Color> colorMap = genColorMap();

        for (int x = 0; x < mask.length; x++) {
            for (int y = 0; y < mask[0].length; y++) {
                Tile tile = new Tile(null, TileIO.img16(setImg, x, y));
                TileEdge[] b=new TileEdge[4];

                for (int i = 0; i < 4; i++) {
                    b[i]=new TileEdge(Util.getRandomName(), colorMap.get(mask[x][y].borders[i].getColor()));
                }

                tile.borders=b;
                tiles.add(tile);
            }
        }
        return new TileSet(this, colorMap, tiles);
    }

    private Map<Color, Color> genColorMap(){
        Map<Color, Color> cMap = new HashMap<>();
        cMap.put(Color.BLACK,Color.BLACK);
        cMap.put(Color.RED,Color.RED);
        cMap.put(Color.BLUE,Color.BLUE);

        for (int x = 0; x < mask.length; x++) {
            for (int y = 0; y < mask[0].length; y++) {

                for (int i = 0; i < 4; i++) {
                    Color color = cMap.get(mask[x][y].borders[i].getColor());
                    if(color== null){
                        color=Color.getHSBColor(random.nextFloat(), 0.5f + random.nextFloat() / 2, 0.5f + random.nextFloat() / 2);
                        cMap.put(mask[x][y].borders[i].getColor(),color);
                    }
                }

            }
        }
        return cMap;
    }

    public String getFolder() {
        return folder;
    }
}