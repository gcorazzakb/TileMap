package company.Tiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static company.Globals.*;

public class TileMask {

    public Tile[][] mask;

    private static Random random = new Random(1337);
    private Map<Color, Integer> hmap = new HashMap<>();
    private Map<Integer, Color> rhmap = new HashMap<>();


    public TileMask(String pathToFile) throws IOException {
        BufferedImage maskImg = ImageIO.read(new File(pathToFile));


        int resX = maskImg.getWidth() / 16;
        int resY = maskImg.getHeight() / 16;
        mask = new Tile[resX][resY];


        hmap.put(Color.BLUE, 0);
        hmap.put(Color.RED, 1);
        hmap.put(Color.YELLOW, 2);

        rhmap.put(0, Color.BLUE);
        rhmap.put(1, Color.RED);
        rhmap.put(2, Color.YELLOW);

        for (int x = 0; x < resX; x++) {
            for (int y = 0; y < resY; y++) {
                if (hasTileAt(maskImg, x, y)) {
                    TileEdge[] tileEdges = getTileEdges(maskImg, x, y);
                    //mask[x][y]=new Tile(tileEdges, null, null);
                }
            }
        }

    }

    private boolean hasTileAt(BufferedImage maskImg, int x, int y) {
        return !(maskImg.getRGB(x * 16, y * 16) == Color.WHITE.getRGB());
    }
/*


    public TileSet loadTiles(String pathToTileImg) throws IOException {
        BufferedImage setImg = ImageIO.read(new File(pathToTileImg));
        HashSet<Tile> tiles = new HashSet<>();
        Map<Color, Color> colorMap = genColorMap();

        for (int x = 0; x < mask.length; x++) {
            for (int y = 0; y < mask[0].length; y++) {
                if(mask[x][y]!=null) {
                    TileEdge[] tileEdges = new TileEdge[4];

                    for (int i = 0; i < 4; i++) {
                        TileEdge te = mask[x][y].borders[i];
                        tileEdges[i] = new TileEdge(colorMap.get(te.getColor()));
                    }

                    Tile tile = new Tile(tileEdges, mask[x][y].getHeights(), Util.img16(setImg, x, y));
                    tiles.add(tile);
                }
            }
        }
        return new TileSet(this, colorMap, tiles);
    }




    public Map<Color, Color> genColorMap(){
        Map<Color, Color> cMap = new HashMap<>();
        cMap.put(Color.BLACK,Color.BLACK);
        cMap.put(Color.RED,Color.RED);
        cMap.put(Color.BLUE,Color.BLUE);

        for (int x = 0; x < mask.length; x++) {
            for (int y = 0; y < mask[0].length; y++) {

                for (int i = 0; i < 4 && mask[x][y]!=null; i++) {
                    Color color = cMap.get(mask[x][y].borders[i].getColor());
                    if(color== null){
                        color=Color.getHSBColor(random.nextFloat(), 0.5f + random.nextFloat() / 2, 0.5f + random.nextFloat() / 2);
                        cMap.put(mask[x][y].borders[i].getColor(),mask[x][y].borders[i].getColor());// no mapping
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
                g.drawImage(drawTileMask(mask[x][y]),x*16,y*16,null);
            }
        }

        try {
            ImageIO.write(tileSetMaskImg, "png", new File(pathToSaveFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage drawTileMask(Tile t){
        BufferedImage tileMask = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics g = tileMask.getGraphics();

        if(t==null){
            g.setColor(Color.white);
            g.fillRect(0,0,16,16);
            return tileMask;
        }

        int[][] heights = t.getHeights();

        //edges
        g.setColor(t.borders[N_CODE].getColor());
        g.drawLine(0,0,15,0);

        g.setColor(t.borders[E_CODE].getColor());
        g.drawLine(15,15,15,0);

        g.setColor(t.borders[S_CODE].getColor());
        g.drawLine(15,15,0,15);

        g.setColor(t.borders[W_CODE].getColor());
        g.drawLine(0,0,0,15);

        return tileMask;
    }
*/


    public TileEdge[] getTileEdges(BufferedImage maskImg, int x, int y) {
        TileEdge[] assets = new TileEdge[4];
        assets[N_CODE] = getTileEdge(N_CODE, maskImg, x, y);
        assets[E_CODE] = getTileEdge(E_CODE, maskImg, x, y);
        assets[S_CODE] = getTileEdge(S_CODE, maskImg, x, y);
        assets[W_CODE] = getTileEdge(W_CODE, maskImg, x, y);
        return assets;
    }

    public TileEdge getTileEdge(int nesw, BufferedImage maskImg, int X, int Y) {
        int x = 0, y = 0;
        switch (nesw) {
            case N_CODE: {
                x = 10;
                y = 0;
                break;
            }
            case E_CODE: {
                x = 15;
                y = 10;
                break;
            }
            case S_CODE: {
                x = 10;
                y = 15;
                break;
            }
            case W_CODE: {
                x = 0;
                y = 10;
                break;
            }
        }

        Color color = new Color(maskImg.getRGB(X * 16 + x, Y * 16 + y));

        TileEdge tileEdge = new TileEdge(color);

        return tileEdge;
    }


/*
    public TileEdge getTileEdgeByColor(Color color){
        return getEdge(color, edges);
    }*/
}
