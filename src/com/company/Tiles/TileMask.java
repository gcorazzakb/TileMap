package com.company.Tiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.company.Tiles.TileIO.*;

public class TileMask {

    public Tile[][] mask;

    private static Random random= new Random(1337);
    private Map<Color, Integer> hmap= new HashMap<>();
    private Map<Integer, Color> rhmap= new HashMap<>();

    public TileMask(String pathToFile) throws IOException {
        BufferedImage maskImg = ImageIO.read(new File(pathToFile));


        int resX=maskImg.getWidth()/16;
        int resY=maskImg.getHeight()/16;
        mask= new Tile[resX][resY];


        hmap.put(Color.BLUE,0);
        hmap.put(Color.RED,1);
        hmap.put(Color.YELLOW,2);

        rhmap.put(0,Color.BLUE);
        rhmap.put(1,Color.RED);
        rhmap.put(2,Color.YELLOW);

        for (int x = 0; x < resX; x++) {
            for (int y = 0; y < resY; y++) {
                TileEdge[] tileEdges = getTileEdges(maskImg, x, y);
                int[][] heights= getTileHeights(maskImg,x,y);
                mask[x][y]=new Tile(tileEdges, heights, null);
            }
        }

    }




    public Map<Color, Color> genColorMap(){
        Map<Color, Color> cMap = new HashMap<>();
        cMap.put(Color.BLACK,Color.BLACK);
        cMap.put(Color.RED,Color.RED);
        cMap.put(Color.BLUE,Color.BLUE);

        for (int x = 0; x < mask.length; x++) {
            for (int y = 0; y < mask[0].length; y++) {

                for (int i = 0; i < 4; i++) {
                    Color color = cMap.get(mask[x][y].borders[i].getColor());
                    if(color== null){
                        color=Color.getHSBColor(random.nextFloat(), 0.5f + random.nextFloat() / 2, 0.5f + random.nextFloat() / 2);
                        cMap.put(mask[x][y].borders[i].getColor(),color);
                    }
                }

            }
        }
        return cMap;
    }


    public void saveMask(String pathToSaveFile){
        BufferedImage tileSetMaskImg = new BufferedImage(mask.length * 16, mask[0].length * 16, BufferedImage.TYPE_INT_ARGB);
        Graphics g = tileSetMaskImg.getGraphics();
        for (int x = 0; x < mask.length; x++) {
            for (int y = 0; y < mask[0].length; y++) {
                int xoff=x*16, yoff=y*16;
                int[][] heights = mask[x][y].getHeights();

                //edges
                g.setColor(mask[x][y].borders[N_CODE].getColor());
                g.drawLine(xoff,yoff,xoff+15,yoff);

                g.setColor(mask[x][y].borders[E_CODE].getColor());
                g.drawLine(xoff+15,yoff+15,xoff+15,yoff);

                g.setColor(mask[x][y].borders[S_CODE].getColor());
                g.drawLine(xoff+15,yoff+15,xoff,yoff+15);

                g.setColor(mask[x][y].borders[W_CODE].getColor());
                g.drawLine(xoff,yoff,xoff,yoff+15);


                //heights
                g.setColor(rhmap.get(heights[0][0]));
                g.drawRect(xoff+1,yoff+1,0,0);

                g.setColor(rhmap.get(heights[1][0]));
                g.drawRect(xoff+14,yoff+1,0,0);

                g.setColor(rhmap.get(heights[1][1]));
                g.drawRect(xoff+14,yoff+14,0,0);

                g.setColor(rhmap.get(heights[0][1]));
                g.drawRect(xoff+1,yoff+14,0,0);

            }
        }

        try {
            ImageIO.write(tileSetMaskImg, "png", new File(pathToSaveFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public TileEdge[] getTileEdges(BufferedImage maskImg, int x, int y) {
        TileEdge[] assets= new TileEdge[4];
        assets[N_CODE]=getTileEdge(N_CODE, maskImg,x,y);
        assets[E_CODE]=getTileEdge(E_CODE, maskImg,x,y);
        assets[S_CODE]=getTileEdge(S_CODE, maskImg,x,y);
        assets[W_CODE]=getTileEdge(W_CODE, maskImg,x,y);
        return assets;
    }

    private int[][] getTileHeights(BufferedImage maskImg, int x, int y) {
        int[][] heights=new int[2][2];
        heights[0][0] = hmap.get(new Color(maskImg.getRGB(x*16+1,y*16+1)));
        heights[1][0] = hmap.get(new Color(maskImg.getRGB(x*16+14,y*16+1)));
        heights[1][1] = hmap.get(new Color(maskImg.getRGB(x*16+14,y*16+14)));
        heights[0][1] = hmap.get(new Color(maskImg.getRGB(x*16+1,y*16+14)));
        return heights;
    }

    public TileEdge getTileEdge(int nesw, BufferedImage maskImg, int X, int Y){
        int x=0,y=0;
        int hx=0,hy=0;
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

        Color color = new Color(maskImg.getRGB(X*16+x,Y*16+y));

        TileEdge tileEdge=new TileEdge(color);

        return tileEdge;
    }
/*
    public TileEdge getTileEdgeByColor(Color color){
        return getEdge(color, edges);
    }*/
}
