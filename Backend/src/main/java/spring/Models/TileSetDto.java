package spring.Models;

public class TileSetDto {
    private TileDto[] tiles = new TileDto[48];
    private int ID;

    public void setTiles(TileDto[] tiles) {
        this.tiles = tiles;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public TileDto[] getTiles() {
        return tiles;
    }

    public int getID() {
        return ID;
    }
}
