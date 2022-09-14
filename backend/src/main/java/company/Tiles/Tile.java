package company.Tiles;

import spring.Models.TileDto;

import java.awt.image.BufferedImage;

public class Tile {
    private boolean[][] block; //3x3
    private BufferedImage img;
    private boolean mustHaveBeneath;
    private boolean canHaveAbove;
    private final int id;


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

    public TileDto toDto(){
        TileDto tileDto = new TileDto();
        tileDto.setBlock(block);
        tileDto.setId(id);
        return tileDto;
    }

}
