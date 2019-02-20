package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class TileIO {

    public static final int N_CODE = 0, E_CODE = 1, S_CODE = 2 , W_CODE = 3;

    private static Set<Tile> tiles =new HashSet<>();
    private static Set<TileEdge> tileEdges =new HashSet();
/*

    public static Tile getTile(TileSetImg set, int x, int y){
        BufferedImage img = img16(set.getTileImg(), x , y);
        TileEdge[] borders= getTileEdges(set.getTrans(), x, y);
        Tile tile=new Tile(borders, img);
        return tile;
    }*/

    public static TileEdge[] getTileEdges(BufferedImage maskImg, int x, int y) {
        TileEdge[] assets= new TileEdge[4];
        assets[N_CODE]=getTileEdge(N_CODE, maskImg,x,y);
        assets[E_CODE]=getTileEdge(E_CODE, maskImg,x,y);
        assets[S_CODE]=getTileEdge(S_CODE, maskImg,x,y);
        assets[W_CODE]=getTileEdge(W_CODE, maskImg,x,y);
        return assets;
    }

    private static TileEdge getTileEdge(int nesw, BufferedImage img, int X, int Y){
        int x=0,y=0;
        switch (nesw){
            case N_CODE:{
                x=10; y=0;
                break;
            }
            case E_CODE:{
                x=15; y=10;
                break;
            }
            case S_CODE:{
                x=10; y=15;
                break;
            }
            case W_CODE:{
                x=0; y=10;
                break;
            }
        }

        Color color = new Color(img.getRGB(X*16+x,Y*16+y));
        TileEdge tileEdge = getTileEdgeByColor(color);

        if(tileEdge==null){
            String tileEdgeName = Util.getRandomName();
            tileEdge=new TileEdge(tileEdgeName,color);
            tileEdges.add(tileEdge);
        }
        return tileEdge;
    }

    private static TileEdge getTileEdgeByColor(Color color){
        Iterator<TileEdge> te = tileEdges.iterator();
        while (te.hasNext()) {
            TileEdge next = te.next();
            if (next.getColor().equals(color)){
                return next;
            }
        }
        return null;
    }

    public static BufferedImage img16(BufferedImage img, int x, int y){
        BufferedImage img16 = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        img16.getGraphics().drawImage(img,-x*16,-y*16,null);
        return img16;
    }

    public static Tile getRandomTile(){
        return  getRandomTileFrom(tiles);
    }

    public static Tile getRandomTile(Tile[] tilesNESW){
        TileEdge[] edges= new TileEdge[]{
                tilesNESW[N_CODE]==null?null:tilesNESW[N_CODE].edge(S_CODE),
                tilesNESW[E_CODE]==null?null:tilesNESW[E_CODE].edge(W_CODE),
                tilesNESW[S_CODE]==null?null:tilesNESW[S_CODE].edge(N_CODE),
                tilesNESW[W_CODE]==null?null:tilesNESW[W_CODE].edge(E_CODE),};
        return  getRandomTile(edges);
    }

    public static Tile getRandomTile(TileEdge[] edgesNESW){
        Set<Tile> tiles = getTiles(edgesNESW);
        return  getRandomTileFrom(tiles);
    }

    public static Tile getRandomTileFrom(Collection<Tile> tileSet){
        if(tileSet.isEmpty()){
            return null;
        }
        int randI= (int) (Math.random()*tileSet.size())+1;
        Iterator<Tile> iterator = tileSet.iterator();
        for (int i = 0; i < randI; i++) {
            Tile nTile = iterator.next();
            if (i==randI-1){
                return nTile;
            }
        }
        return  null;
    }

    public static Set<Tile> getTilesFrom(Collection<Tile> tiles, TileEdge[] edges){
        Set<Tile> tileSet= new HashSet<>();
        Iterator<Tile> iterator = tiles.iterator();
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            boolean works=true;
            for (int i = 0; i < 4; i++) {
                if(!(edges[i]== null ||  tile.edge(i).equals(edges[i]))){
                    works=false;
                }
            }
            if (works){
                tileSet.add(tile);
            }
        }
        return  tileSet;
    }

    public static Set<Tile> getTiles(TileEdge[] edges){
        return  getTilesFrom(tiles, edges);
    }

    public static void init() {
        try {
            TileMask tm = new TileMask("ground");
            /*TileIO.tiles.addAll(tm.loadTiles("dirt").tileSet);
            TileIO.tiles.addAll(tm.loadTiles("snow").tileSet);*/
            TileIO.tiles.addAll(tm.loadTiles("grass").tileSet);
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //loadRestStuff();
    }

}
