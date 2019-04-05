package spring.Models;

import company.Tiles.Tile;

public class TileJSONModel {

    private final int id;
    private final boolean[][] block;

    public TileJSONModel(Tile tile) {
        block= tile.getBlock();
        id = tile.getId();
    }

    public int getId() {
        return id;
    }

    public boolean[][] getBlock() {
        return block;
    }
}
