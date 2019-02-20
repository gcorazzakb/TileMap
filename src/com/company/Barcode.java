package com.company;

import java.awt.image.BufferedImage;

public class Barcode {
    private static final double BARCODE_DIFF_TOL=0.1d;
    private String name;
    private BufferedImage barcode;

    public Barcode(String name, BufferedImage barcode) {
        this.name = name;
        this.barcode = barcode;
    }

    public boolean isSame(BufferedImage barcode1){
        return true;
    }

    public String getName() {
        return name;
    }
}
