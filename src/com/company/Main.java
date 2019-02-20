package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        System.out.println("started");
        init();
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

    private static void init(){
        TileIO.init();
    }
}
