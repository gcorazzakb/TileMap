package company;

import company.Map.GameMap;
import org.junit.Ignore;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    @Ignore
    public void main() throws InterruptedException {
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

        /*frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                TileIO.saveTileSet("TestSave");
            }
        });*/
        while(true){
            Thread.sleep(1000);
        }
    }
}
