package com.company;

import java.awt.image.BufferedImage;

class Tile {
    //private byte[][] height;   //3x3
    public TileEdge[] borders; //private
    //private boolean[][] block; //3x3
    private BufferedImage img;

    public Tile(TileEdge[] borders, BufferedImage img) {
        this.borders = borders;
        this.img=img;
        /*height=new byte[3][3];
        block=new boolean[3][3];*/
    }

    public Tile(Tile cTile){
        borders=cTile.borders;
        img=cTile.img;
    }

    public BufferedImage getImg() {
        return img;
    }

    public TileEdge edge(int nesw){
        return borders[nesw];
    }

}