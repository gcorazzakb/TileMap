package com.company.Map;

import com.company.Tiles.Tile;
import com.company.Tiles.TileIO;
import com.company.Tiles.TileMask;
import com.company.Tiles.TileSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class GameMap {
    private static  Map<String, MapTile> mapXY;
    private static int[][] heightMap= new int[21][21];
    private static TileSet grass, dirt, snow, all;

    public static void init() {
        mapXY = new HashMap<>();
        /*mapXY.put("0,0",new MapTile(TileIO.getStartTile(),0,0));
        mapXY.put("1,0",new MapTile(TileIO.getTileByImagePoint(new Point(2,12)),1,0));
        mapXY.put("0,1",new MapTile(TileIO.getTileByImagePoint(new Point(1,13)),0,1));*/
        /*MapTile[] tilesNESW1 = getTilesNESW(0, 1);*/

        for (int x = 2; x < 4; x++) {
            for (int y =2; y<4; y++){
                heightMap[x][y]=1;
            }
        }

        try {
            TileMask tm = new TileMask("ground//mask.png");
            grass = TileIO.loadTiles("ground//grass.png", tm);
            dirt = TileIO.loadTiles("ground//dirt.png", tm);
            snow = TileIO.loadTiles("ground//snow.png", tm);
            tm.saveMask("ground//mask_v1.png");
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int x = 0; x < 20; x++) {
            for (int y =0; y<20; y++){

                if (x==1&& y==1){
                    System.out.println();
                }

                MapTile[] tilesNESW = getTilesNESW(x, y);
                int[][] heights= getHeights(x,y);
                Tile tile = grass.getRandomTile(tilesNESW, heights);
                if(tile!=null)
                    mapXY.put(x+","+y,new MapTile(tile, x, y));
                else
                    System.out.println("found no tile");
            }
        }
    }

    private static  int[][] getHeights(int X, int Y) {
        int[][] heights =new int[2][2];
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                heights[x][y]=heightMap[X+x][Y+y];
            }
        }
        return heights;
    }

    private static MapTile[] getTilesNESW(long x, long y){
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

    private static String xy(long x, long y){
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
