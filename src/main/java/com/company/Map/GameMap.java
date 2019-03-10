package com.company.Map;

import com.company.Globals;
import com.company.Tiles.*;
import javafx.util.Pair;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class GameMap {
    private static Multigraph<MapTile, TileEdge> map= new Multigraph(DefaultEdge .class);



    private static TileSet grass, dirt, snow, all;

    MapPart part;

    public GameMap() {
        /*mapXY.put("0,0",new MapTile(TileIO.getStartTile(),0,0));
        mapXY.put("1,0",new MapTile(TileIO.getTileByImagePoint(new Point(2,12)),1,0));
        mapXY.put("0,1",new MapTile(TileIO.getTileByImagePoint(new Point(1,13)),0,1));*/
        /*MapTile[] tilesNESW1 = getTilesNESW(0, 1);*/



        try {
            System.out.println(new File("").getAbsolutePath());
            TileMask tm = new TileMask("img//ground1//mask_v1.png");
            grass = tm.loadTiles("img//ground1//grass.png");
            Globals.allTiles=grass;
            tm.saveMask("img//ground1//mask_v1_2.png");
            System.out.println("saved mask");

        } catch (IOException e) {
            e.printStackTrace();
        }

        part= new MapPart(0,0,20,20);

    }

/*

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
    }*/

    private static String xy(long x, long y){
        return x+","+y;
    }

    public BufferedImage drawMap(long X, long Y, int width, int height){
        int scale=5;
        BufferedImage mapImage = new BufferedImage(width*16*scale, width*16*scale, TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) mapImage.getGraphics();
        graphics.scale(scale,scale);

        MapTile[][] map = part.getMap();

        for (int x=0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                MapTile tile = map[x][y];
                if(tile!=null)
                    graphics.drawImage(tile.getImg(),x*16,y*16,null);
            }
        }
        return mapImage;
    }
}
