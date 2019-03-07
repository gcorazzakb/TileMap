package com.company.Tiles;

import com.company.Map.MapTile;

import java.awt.*;
import java.util.*;

import static com.company.Globals.*;

public class TileSet {
    TileMask tm;
    Map<Color, Color> colorMap;
    Set<Tile> tileSet;

    public TileSet(TileMask tm, Map<Color, Color> cm, Set<Tile> tileSet) {
        this.tm = tm;
        this.tileSet = tileSet;
        colorMap = cm;
    }

    public TileSet(TileSet tileSetObj, Set<Tile> tileSet) {
        tm = tileSetObj.tm;
        colorMap = tileSetObj.colorMap;
        this.tileSet = tileSet;
    }

    public Tile getRandomTile() {
        if (tileSet.isEmpty()) {
            return null;
        }
        int randI = (int) (Math.random() * tileSet.size()) + 1;
        Iterator<Tile> iterator = tileSet.iterator();
        for (int i = 0; i < randI; i++) {
            Tile nTile = iterator.next();
            if (i == randI - 1) {
                return nTile;
            }
        }
        return null;
    }


    public Tile getRandomTile(Tile[] tilesNESW) {
        return getTiles(tilesToEdges(tilesNESW)).getRandomTile();
    }

    public Tile getRandomTile(MapTile[] tilesNESW, int[][] heights) {
        return getTiles(tilesToEdges(tilesNESW), heights).getRandomTile();
    }


    public TileSet getTiles(TileEdge[] edges) {
        return getTiles(edges, null);
    }

    public TileSet getTiles(TileEdge[] edges, int[][] heights) {
        Iterator<Tile> iterator = tileSet.iterator();
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            boolean works = true;

            for (int i = 0; i < 4; i++) {
                if (!(edges[i] == null || tile.edge(i).equals(edges[i]))) {
                    works = false;
                }
            }

            if (heights != null)
                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        if (heights[x][y] != -1 && heights[x][y] != tile.getHeights()[x][y]) {
                            works = false;
                        }
                    }
                }

            if (works) {
                tileSet.add(tile);
            }
        }
        return new TileSet(this, tileSet);
    }

    private static TileEdge[] tilesToEdges(Tile[] tilesNESW) {
        TileEdge[] edges = new TileEdge[]{
                tilesNESW[N_CODE] == null ? null : tilesNESW[N_CODE].edge(S_CODE),
                tilesNESW[E_CODE] == null ? null : tilesNESW[E_CODE].edge(W_CODE),
                tilesNESW[S_CODE] == null ? null : tilesNESW[S_CODE].edge(N_CODE),
                tilesNESW[W_CODE] == null ? null : tilesNESW[W_CODE].edge(E_CODE)};
        return edges;
    }

/*
    public BufferedImage saveTiles(String setname){
        BufferedImage tileSetImg = new BufferedImage(tm.mask.length * 16, tm.mask[0].length * 16, BufferedImage.TYPE_INT_ARGB);
        Graphics g = tileSetImg.getGraphics();
        for (int x = 0; x < tm.mask.length; x++) {
            for (int y = 0; y < tm.mask[0].length; y++) {
                TileEdge[] b= new TileEdge[4];
                for (int i = 0; i < 4; i++) {
                    Color color = colorMap.get(tm.mask[x][y].borders[i].getColor());
                    if (color==null){
                        System.out.println();
                    }
                    b[i]=getTileEdgeByColor(color);
                    if (b[i]==null){
                        System.out.println();
                    }
                }
                Iterator<Tile> it = TileIO.getTilesFrom(tileSet, b).iterator();
                if (it.hasNext()){
                    Tile tile= it.next();
                    tileSet.remove(tile);
                    g.drawImage(tile.getImg(),x*16,y*16,null);
                }
            }
        }

        if(setname!=null){
            try {
                ImageIO.write(tileSetImg, "png", new File(("img//"+tm.getFolder()+"//"+setname+".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tileSetImg;
    }

    public TileEdge getTileEdgeByColor(Color color){
        return getEdge(color, edges);
    }
*/


}
