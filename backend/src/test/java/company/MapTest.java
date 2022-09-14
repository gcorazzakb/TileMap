package company;

import company.Map.GameMap;
import org.junit.Ignore;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static org.junit.Assert.assertTrue;

public class MapTest {

    @Test
    @Ignore
    public void mapTest(){
        final GameMap gameMap = new GameMap(1000);

        JFrame frame = new JFrame("Map");
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(gameMap.drawMap(), 0, 0, null);
            }
        };

        panel.setPreferredSize(new Dimension(1000, 1000));
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                assertTrue(true);
            }
        });

        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                assertTrue(true);
            }
        }

    }
}
