package com.company;

import java.awt.*;

public class TileEdge{
    private String name;
    private Color color;
    private int heightChange=0;

    public TileEdge(String name, Color color, int heightChange) {
        this.name = name;
        this.color = color;
        this.heightChange = heightChange;
    }

    @Override
    public boolean equals(Object obj) {
        return ((TileEdge)obj).color.equals(color);
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }



}