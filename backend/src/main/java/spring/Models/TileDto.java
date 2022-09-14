package spring.Models;

import company.Tiles.Tile;

public class TileDto {

    private int id;
    private boolean[][] block;


    public void setId(int id) {
        this.id = id;
    }

    public void setBlock(boolean[][] block) {
        this.block = block;
    }

    public int getId() {
        return id;
    }

    public boolean[][] getBlock() {
        return block;
    }
}
