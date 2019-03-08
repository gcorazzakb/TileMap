package com.company.Map;

import com.company.Tiles.Tile;
import com.company.Tiles.TileMask;
import com.company.Tiles.TileSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class GameMap {
    private static  Map<String, MapTile> mapXY;
    private static float[][] heightMap= new float[21][21];
    private static TileSet grass, dirt, snow, all;

    public GameMap() {
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
            System.out.println(new File("").getAbsolutePath());
            TileMask tm = new TileMask("img//ground1//mask_v1.png");
            grass = tm.loadTiles("img//ground1//grass.png");
            tm.saveMask("img//ground1//mask_v1_2.png");
            System.out.println("saved mask");

        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int x = 0; x < 20; x++) {
            for (int y =0; y<20; y++){

                if (x==1&& y==1){
                    System.out.println();
                }

                MapTile[] tilesNESW = getTilesNESW(x, y);
                //int[][] heights= getHeightChangesInt(x,y);
                Tile tile = grass.getRandomTile(tilesNESW, null);
                if(tile!=null)
                    mapXY.put(x+","+y,new MapTile(tile, x, y));
                else
                    System.out.println("found no tile");
            }
        }
    }

    private static  int[][] getHeightChangesInt(int X, int Y) {
        int[][] heights =new int[2][2];
        float min=Float.MAX_VALUE;

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                min=Math.min(heightMap[X+x][Y+y], min);
            }
        }

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                heights[x][y]=(int)Math.floor(heightMap[X+x][Y+y]-min);
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
