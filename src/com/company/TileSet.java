package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class TileSet {
    TileMask tm;
    Map<Color, Color> reverseColorMap;
    Set<Tile> tileSet;

    public TileSet(TileMask tm, Map<Color, Color> cm, Set<Tile> tileSet) {
        this.tm = tm;
        this.tileSet = tileSet;
        reverseColorMap=cm;
    }

    public BufferedImage saveTiles(String setname){
        BufferedImage tileSetImg = new BufferedImage(tm.mask.length * 16, tm.mask[0].length * 16, BufferedImage.TYPE_INT_ARGB);
        Graphics g = tileSetImg.getGraphics();
        for (int x = 0; x < tm.mask.length; x++) {
            for (int y = 0; y < tm.mask[0].length; y++) {
                TileEdge[] b= new TileEdge[4];
                for (int i = 0; i < 4; i++) {
                    Color color = reverseColorMap.get(tm.mask[x][y].borders[i].getColor());
                    if (color==null){
                        System.out.println();
                    }
                    b[i]=new TileEdge(Util.getRandomName(),color);
                }
                Iterator<Tile> it = TileIO.getTilesFrom(tileSet, b).iterator();
                if (it.hasNext()){
                    Tile tile= it.next();
                    tileSet.remove(tile);
                    g.drawImage(tile.getImg(),x*16,y*16,null);
                }
            }
        }

        if(setname!=null){
            try {
                ImageIO.write(tileSetImg, "png", new File(("img//"+tm.getFolder()+"//"+setname+".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tileSetImg;
    }
}
