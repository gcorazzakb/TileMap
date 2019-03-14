package com.company.Tiles;


import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.*;

public class TileSetTest {

    int[][] testMap= new int[10][10];
    TileSet tileSet;

    @Before
    public void init(){
        for (int x = 0; x <10; x++) {
            for (int y = 0; y < 10; y++) {
                testMap[x][y]= (int) ((Math.random()-0.5f*2));
            }
        }

        for (int x = 0; x <5; x++) {
            for (int y = 0; y < 5; y++) {
                testMap[x][y]= 0;
            }
        }


        for (int x = 7; x <10; x++) {
            for (int y = 7; y < 10; y++) {
                testMap[x][y]= 1;
            }
        }

        try {
            tileSet = new TileSet("./img/ground2/grass2.png", Color.BLACK,Color.green);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotEquals(tileSet,null);
    }

    @org.junit.Test
    public void testBitToInt() {
        boolean[][] surr= new boolean[3][3];
        surr[1][0]=true;
        surr[2][0]=true;
        surr[2][1]=true;
        int bitTile = tileSet.get8bitTile(surr);
        assertEquals(bitTile, 22);

        surr[0][2]=true;
        surr[2][2]=true;
        bitTile = tileSet.get8bitTile(surr);
        assertEquals(bitTile, 22);
    }

    @Test
    public void testGenTileMap() {
        int[][] map= new int[4][4];
        map[0][0]=1;
        map[1][0]=1;
        map[0][1]=1;
        map[1][1]=1;
        Tile[][] paint = tileSet.paint(map);
        System.out.println(paint.toString());
    }

}
