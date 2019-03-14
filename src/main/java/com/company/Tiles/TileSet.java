package com.company.Tiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TileSet {
    final Color from, to;

    Tile[] tiles= new Tile[48];

    static Map<Integer, Integer> bitIntToInt= initBitToIntMap();

    private static Map<Integer, Integer> initBitToIntMap() {
        HashMap<Integer, Integer> map = new HashMap<>();
        String mapString="2 = 1, 8 = 2, 10 = 3, 11 = 4, 16 = 5, 18 = 6, 22 = 7, 24 = 8, 26 = 9, 27 = 10, 30 = 11, 31 = 12, 64 = 13, 66 = 14, 72 = 15, 74 = 16," +
                "75 = 17, 80 = 18, 82 = 19, 86 = 20, 88 = 21, 90 = 22, 91 = 23, 94 = 24, 95 = 25, 104 = 26, 106 = 27, 107 = 28, 120 = 29, 122 = 30," +
                "123 = 31, 126 = 32, 127 = 33, 208 = 34, 210 = 35, 214 = 36, 216 = 37, 218 = 38, 219 = 39, 222 = 40, 223 = 41, 248 = 42, 250 = 43,"+
                "251 = 44, 254 = 45, 255 = 46, 0 = 47";
        String[] split = mapString.split(",");
        for (int i = 0; i < split.length; i++) {
            String[] eq = split[i].split("=");
            int from=Integer.valueOf(eq[0].replaceAll(" ",""));
            int to=Integer.valueOf(eq[1].replaceAll(" ",""));
            map.put(from, to);
        }


        return map;
    }

    public TileSet(String path, Color from, Color to) throws IOException {
        this.from = from;
        this.to = to;

        BufferedImage tilesImg = ImageIO.read(new File(path));
        imgToTiles(tilesImg);
    }

    public void imgToTiles(BufferedImage img){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 6; y++) {
                BufferedImage tileImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
                tileImg.getGraphics().drawImage(img,x*-16,y*-16,null);
                Tile tile = new Tile(tileImg);
                tiles[x+y*8]=tile;
            }
        }
    }

    public Tile[][] paint(int[][] map){
        Tile[][] tMap=new Tile[map.length][map[0].length];
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                boolean[][] boolSurr = getBoolSurr(x, y, map, false);
                int bitTile = get8bitTile(boolSurr);
                int mappedBitTile= bitIntToInt.get(bitTile);
                tMap[x][y]= tiles[mappedBitTile];
            }
        }
        return tMap;
    }

    public boolean[][]  getBoolSurr(int X, int Y, int[][] map, boolean b) {
        boolean[][] surr = new boolean[3][3];
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                surr[x+1][y+1]= getBool(x+X,y+Y,b, map);
            }
        }
        return surr;
    }

    public boolean getBool(int x, int y, boolean b, int[][] map) {
        if(x<0 || y<0){
            return b;
        }
        if(x>=map.length || y>=map.length){
            return b;
        }

        return map[x][y]==1;

    }

    public int get8bitTile(boolean[][] surr){
        surr[0][0]=surr[0][1] && surr[1][0];
        surr[2][0]=surr[1][0] && surr[2][1];
        surr[2][2]=surr[2][1] && surr[1][2];
        surr[0][2]=surr[0][1] && surr[1][2];

        int a=0;

        a+=surr[0][0]?1:0;
        a+=surr[1][0]?2:0;
        a+=surr[2][0]?4:0;
        a+=surr[0][1]?8:0;
        a+=surr[2][1]?16:0;
        a+=surr[0][2]?32:0;
        a+=surr[1][2]?64:0;
        a+=surr[2][2]?128:0;

        return a;
    }


}
