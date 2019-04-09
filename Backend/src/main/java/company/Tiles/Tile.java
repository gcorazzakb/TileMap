package company.Tiles;

import spring.Models.TileDto;

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

    public static TileDto[][][] convertToJSONModel(Tile[][][] mapPart) {
        TileDto[][][] mapModel = new TileDto[mapPart.length][mapPart[0].length][mapPart[0][0].length];
        for (int x = 0; x < mapPart.length; x++) {
            for (int y = 0; y < mapPart[0].length; y++) {
                for (int z = 0; z < mapPart[0][0].length; z++) {
                    if (mapPart[x][y][z]!=null) {
                        TileDto tileJSONModel = mapPart[x][y][z].toDto();
                        mapModel[x][y][z] = tileJSONModel;
                    }
                }
            }
        }
        return mapModel;
    }

    public TileDto toDto(){
        TileDto tileDto = new TileDto();
        tileDto.setBlock(block);
        tileDto.setId(id);
        return tileDto;
    }

}
