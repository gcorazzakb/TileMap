package spring.backend.Models;

import company.Tiles.Tile;

public class TileJSONModel {

    private final int id;

    public TileJSONModel(Tile tile) {
        id = tile.getId();
    }

    public int getId() {
        return id;
    }
}
