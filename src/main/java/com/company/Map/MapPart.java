package com.company.Map;

import com.company.Tiles.TileCorner;
import com.company.Tiles.TileEdge;
import javafx.util.Pair;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.awt.*;

public class MapPart {

    private static final int LAYERTOPTHREASH = 1;
    final int X,Y,width,height;

    private final float[][] heightMap;
    private static final float threashold=0.5f;

    public MapPart(int x, int y, int width, int height) {
        X = x;
        Y = y;
        this.width = width;
        this.height = height;

        heightMap=genHeightMap();
    }

    float[][] genHeightMap(){
        float[][] hmap=new float[width][height];
        for (int x = 5; x < heightMap.length; x++) {
            for (int y =0; y<heightMap[0].length; y++){
                heightMap[x][y]=1;
            }
        }

        for (int x = 10; x < heightMap.length; x++) {
            for (int y =0; y<heightMap[0].length/2; y++){
                heightMap[x][y]=2;
            }
        }

        return hmap;
    }

    private int[][] getLayer(int l){
        int[][] layer=new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y =0; y< height; y++){
                if(heightMap[x][y]<l){
                    layer[x][y]=-1;
                }else if (heightMap[x][y]>l+LAYERTOPTHREASH){
                    layer[x][y]=1;
                }
            }
        }
    }

    private TileEdge getEdge(){}

    private Pair<Multigraph<TileCorner, TileEdge>, MapTile[][]> generateTileGraph(float hmap[][], Color color){
        Multigraph<TileCorner, TileEdge> tileGraph=new Multigraph(DefaultEdge.class);

        MapTile[][] graphBuilder= new MapTile[hmap.length][hmap[0].length];

        for (int x = 0; x < graphBuilder.length; x++) {
            for (int y =0; y< graphBuilder[0].length; y++){
                MapTile mapTile = new MapTile(null, x, y);
                graphBuilder[x][y]=mapTile;
                tileGraph.addVertex(mapTile);
            }
        }

        for (int x = 0; x < graphBuilder.length-1; x++) {
            for (int y =0; y< graphBuilder[0].length-1; y++){
                MapTile a = graphBuilder[x][y];
                MapTile b = graphBuilder[x+1][y];
                MapTile c = graphBuilder[x][y+1];
                tileGraph.addEdge(a,b, new TileEdge(color));
                tileGraph.addEdge(a,c, new TileEdge(color));
            }
        }

        return new Pair<>(tileGraph,graphBuilder);

    }

    private  int[][] getHeightChangesInt(int X, int Y) {
        int[][] heights =new int[2][2];
        float min=Float.MAX_VALUE;

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                min=Math.min(heightMap[X+x][Y+y], min);
            }
        }

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                heights[x][y]=(int)Math.floor(heightMap[X+x][Y+y]-min);
            }
        }

        return heights;
    }
}
