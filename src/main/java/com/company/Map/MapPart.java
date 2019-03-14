package com.company.Map;

import com.company.Globals;
import com.company.Tiles.Tile;
import com.company.Tiles.TileCorner;
import com.company.Tiles.TileEdge;
import javafx.util.Pair;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.awt.*;
import java.util.Iterator;
import java.util.Set;

import static com.company.Globals.*;

public class MapPart {

    private static final int LAYERTOPTHREASH = 1;
    final int X,Y,width,height;

    //private final float[][] heightMap;
    private final MapTile[][] map;
    private static final float threashold=0.5f;
    private Color trans= Color.magenta, alpha =Color.BLACK, flat=Color.green;

    public MapPart(int x, int y, int width, int height) {
        X = x;
        Y = y;
        this.width = width;
        this.height = height;

        float[][] heightMap=genHeightMap(new float[width][height]);

        map=generateMap(heightMap);

    }

    public MapTile[][] getMap() {
        return map;
    }

    private MapTile[][] generateMap(float[][] heightMap) {
        return genMapLayer(heightMap, 1);
    }

    private MapTile[][] genMapLayer(float[][] heightMap, int l){
        int[][] layer = getLayer(heightMap, l);

        Pair<Multigraph<TileCorner, TileEdge>, TileCorner[][]> multigraphPair = generateHeightGraphMap(heightMap);
        paintOnGraphWithHeights(multigraphPair, layer);

        return  getTileMap(multigraphPair);
    }

    private MapTile[][] getTileMap(Pair<Multigraph<TileCorner, TileEdge>, TileCorner[][]> graphMap){
        TileCorner[][] corners = graphMap.getValue();
        Multigraph<TileCorner, TileEdge> map = graphMap.getKey();
        MapTile[][] tileMap= new MapTile[corners.length-1][corners[0].length-1];
        for (int x = 0; x < tileMap.length; x++) {
            for (int y =0; y < tileMap[0].length; y++){
                TileEdge[] edges=new TileEdge[4];

                edges[N_CODE]= map.getEdge(corners[x][y],corners[x+1][y]);
                edges[E_CODE]= map.getEdge(corners[x+1][y],corners[x+1][y+1]);
                edges[S_CODE]= map.getEdge(corners[x+1][y+1],corners[x][y+1]);
                edges[W_CODE]= map.getEdge(corners[x][y+1],corners[x][y]);

                //Tile tile = Globals.allTiles.getTiles(edges).getRandomTile();
                //tileMap[x][y]=new MapTile(tile,x,y);
            }
        }
        return tileMap;
    }

    private void paintOnGraphWithHeights(Pair<Multigraph<TileCorner, TileEdge>, TileCorner[][]> multigraphPair, int[][] layer ) {
        Multigraph<TileCorner, TileEdge> map = multigraphPair.getKey();
        TileCorner[][] corners = multigraphPair.getValue();
        for (int x = 0; x < layer.length; x++) {
            for (int y =0; y<layer[0].length; y++){
                if (layer[x][y]==0){
                    TileCorner corner=corners[x][y];
                    Set<TileEdge> tileEdges = map.edgesOf(corner);
                    Iterator<TileEdge> edit = tileEdges.iterator();
                    while (edit.hasNext()) {
                        TileEdge edge = edit.next();
                        TileCorner otherside=map.getEdgeSource(edge);
                        if (otherside==corner){
                            otherside=map.getEdgeTarget(edge);
                        }
                        if(layer[otherside.getX()][otherside.getY()]==0){
                            edge.setColor(flat);
                        }else{
                            edge.setColor(trans);
                            /*float gradient=corner.getHeight()-otherside.getHeight();
                            if(gradient<threashold){
                                //green
                                edge.setColor(flat);
                            }*/
                        }
                    }
                }
            }
        }
    }


    float[][] genHeightMap(float[][] heightMap){
        for (int x = 5; x < heightMap.length; x++) {
            for (int y =0; y<heightMap[0].length; y++){
                heightMap[x][y]=1;
            }
        }

        for (int x = 10; x < heightMap.length; x++) {
            for (int y =0; y<heightMap[0].length/4; y++){
                heightMap[x][y]=2;
            }
        }

        heightMap[0][0]=1;
        heightMap[1][0]=0.7f;
        heightMap[2][0]=0.2f;

        return heightMap;
    }

    private int[][] getLayer(float[][] heightMap, int l){
        int[][] layer=new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y =0; y< height; y++){
                if(heightMap[x][y]<l){
                    layer[x][y]=-1;
                }
            }
        }

        return layer;
    }

    private  Pair<Multigraph<TileCorner, TileEdge>, TileCorner[][]> generateHeightGraphMap(float[][]heightMap){
        Multigraph<TileCorner, TileEdge> tileGraph=new Multigraph(DefaultEdge.class);

        TileCorner[][] graphBuilder= new TileCorner[heightMap.length][heightMap[0].length];

        for (int x = 0; x < graphBuilder.length; x++) {
            for (int y =0; y< graphBuilder[0].length; y++){
                TileCorner tileCorner = new TileCorner(heightMap[x][y],x,y);
                graphBuilder[x][y]=tileCorner;
                tileGraph.addVertex(tileCorner);
            }
        }

        for (int x = 0; x < graphBuilder.length-1; x++) {
            for (int y =0; y< graphBuilder[0].length-1; y++){
                TileCorner a = graphBuilder[x][y];
                TileCorner b = graphBuilder[x+1][y];
                TileCorner c = graphBuilder[x][y+1];
                tileGraph.addEdge(a,b, new TileEdge(alpha));
                tileGraph.addEdge(a,c, new TileEdge(alpha));
            }
        }

        return new Pair<>(tileGraph, graphBuilder);

    }


}
