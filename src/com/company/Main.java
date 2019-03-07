package com.company;

import com.company.Map.GameMap;
import com.company.Tiles.TileIO;
import com.company.Tiles.TileMask;
import com.company.Tiles.TileSet;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap();
        JFrame frame = new JFrame("Map");
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(gameMap.drawMap(0,0,10,10),0,0,null);
            }
        };

        panel.setPreferredSize( new Dimension(1000,1000));
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        /*frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                TileIO.saveTileSet("TestSave");
            }
        });*/

    }
}
