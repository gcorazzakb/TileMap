package com.company.Tiles;

import java.awt.*;

public class TileEdge{
    private Color color;

    public TileEdge(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        return ((TileEdge)obj).color.equals(color);
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
