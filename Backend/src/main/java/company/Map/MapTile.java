package company.Map;


import company.Tiles.Tile;

public class MapTile extends Tile {
    public final long x, y;
    public MapTile N, S, W, E;

    public MapTile(Tile tile, long x, long y) {
        super(tile);
        this.x = x;
        this.y = y;
    }

    public MapTile(Tile tile, long x, long y, MapTile n, MapTile s, MapTile w, MapTile e) {
        super(tile);
        N = n;
        S = s;
        W = w;
        E = e;
        this.x = x;
        this.y = y;
    }

}
