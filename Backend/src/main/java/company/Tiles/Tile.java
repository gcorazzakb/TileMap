package company.Tiles;

import java.awt.image.BufferedImage;

public class Tile {
    private boolean[][] block; //3x3
    private BufferedImage img;
    private boolean mustHaveBeneath;
    private boolean canHaveAbove;
    final int id;

    private boolean canSetOnTop(Tile down, Tile up) {
        if (up.mustHaveBeneath) {
            return down.canHaveAbove;
        } else {
            return true;
        }
    }

    public Tile(BufferedImage img, int id) {
        this.img = img;
        /*height=new byte[3][3];
        block=new boolean[3][3];*/
        this.id = id;
    }

    public BufferedImage getImg() {
        return img;
    }

    public int getId() {
        return id;
    }

    public boolean[][] getBlock() {
        return block;
    }
}
