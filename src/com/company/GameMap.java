package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class GameMap {
    private Map<String, MapTile> mapXY;

    public GameMap() {
        mapXY = new HashMap<>();
        /*mapXY.put("0,0",new MapTile(TileIO.getStartTile(),0,0));
        mapXY.put("1,0",new MapTile(TileIO.getTileByImagePoint(new Point(2,12)),1,0));
        mapXY.put("0,1",new MapTile(TileIO.getTileByImagePoint(new Point(1,13)),0,1));*/
        /*MapTile[] tilesNESW1 = getTilesNESW(0, 1);*/

        for (int x = 0; x < 20; x++) {
            for (int y =0; y<20; y++){
                MapTile[] tilesNESW = getTilesNESW(x, y);
                Tile tile = TileIO.getRandomTile(tilesNESW);
                if(tile!=null)
                    mapXY.put(x+","+y,new MapTile(tile, x, y));
                else
                    System.out.println("found no tile");
            }
        }
    }

    private MapTile[] getTilesNESW(long x, long y){
        MapTile[] tileAround =new MapTile[4];
        MapTile t;
        if((t=mapXY.get(xy(x,y)))!=null){
            tileAround[0]=t.N;
            tileAround[1]=t.E;
            tileAround[2]=t.S;
            tileAround[3]=t.W;
        }else{
            tileAround[0]=mapXY.get(xy(x,y-1));
            tileAround[1]=mapXY.get(xy(x+1,y));
            tileAround[2]=mapXY.get(xy(x,y+1));
            tileAround[3]=mapXY.get(xy(x-1,y));
        }
        return tileAround;
    }

    private String xy(long x, long y){
        return x+","+y;
    }

    public BufferedImage drawMap(long X, long Y, int width, int height){
        int scale=5;
        BufferedImage mapImage = new BufferedImage(width*16*scale, width*16*scale, TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) mapImage.getGraphics();
        graphics.scale(scale,scale);

        for (int x=0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                MapTile tile = mapXY.get(xy(x+X, y+Y));
                if(tile!=null)
                    graphics.drawImage(tile.getImg(),x*16,y*16,null);
            }
        }
        return mapImage;
    }
}
