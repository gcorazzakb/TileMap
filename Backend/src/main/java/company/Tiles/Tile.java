package company.Tiles;

import java.awt.image.BufferedImage;

public class Tile {
    private boolean[][] block; //3x3
    private BufferedImage img;
    private boolean mustHaveBeneath;
    private boolean canHaveAbove;

    private boolean canSetOnTop(Tile down, Tile up) {
        if (up.mustHaveBeneath) {
            return down.canHaveAbove;
        } else {
            return true;
        }
    }

    public Tile(BufferedImage img) {
        this.img = img;
        /*height=new byte[3][3];
        block=new boolean[3][3];*/
    }

    public Tile(Tile cTile) {
        if (cTile == null)
            return;
        img = cTile.img;
    }

    public BufferedImage getImg() {
        return img;
    }

}
