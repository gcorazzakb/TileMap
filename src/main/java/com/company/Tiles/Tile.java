package com.company.Tiles;

import java.awt.image.BufferedImage;

public class Tile {
    private int[][] heights;   //2x2
    public TileEdge[] borders; //private
    //private boolean[][] block; //3x3
    private BufferedImage img;

    public Tile(TileEdge[] borders, int[][] heights, BufferedImage img) {
        this.borders = borders;
        this.img=img;
        this.heights=heights;
        /*height=new byte[3][3];
        block=new boolean[3][3];*/
    }

    public Tile(Tile cTile){
        if (cTile==null)
            return;

        borders=cTile.borders;
        img=cTile.img;
    }

    public int[][] getHeights() {
        return heights;
    }

    public BufferedImage getImg() {
        return img;
    }

    public TileEdge edge(int nesw){
        return borders[nesw];
    }

}
