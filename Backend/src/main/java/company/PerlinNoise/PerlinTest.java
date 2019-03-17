package company.PerlinNoise;

import company.PerlinNoise.Interpolator.CosInterpolate;
import company.PerlinNoise.Interpolator.Linear;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class PerlinTest {
    public static void main(String[] args) {

        ProbablisticRandom probablisticRandom = new ProbablisticRandom(1, 1f);
        probablisticRandom.addProbablisticPoint(0.0100f, 1f);
        probablisticRandom.addProbablisticPoint(0.0101f, 1f);
        probablisticRandom.addProbablisticPoint(0.9900f, 1f);
        probablisticRandom.addProbablisticPoint(0.9901f, 1.f);

        ArrayList<Float> resultSet= new ArrayList<>();

        Random random = new Random(1);

        for (int i = 0; i < 10000; i++) {
            float v = probablisticRandom.getRandom(random);
            resultSet.add(v);
        }

        resultSet.sort((o1, o2) -> {
            if(o1==o2){
                return 0;
            }else if(o1>o2){
                return 1;
            }
            return -1;
        });

        final PerlinNoise pn = new PerlinNoise(10,10,13387, new CosInterpolate(), probablisticRandom);

        JFrame frame = new JFrame("Perlin Noise");
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pn.getImg(0, 0, 500, 500), 0, 0, null);
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

    }
}
