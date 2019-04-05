package company.PerlinNoise;

import company.PerlinNoise.Interpolator.CosInterpolate;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class PerlinNoiseTest {

    @Test
    public void PerlinNoiseTest() {

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

        final PerlinNoise pn = new PerlinNoise(50,10,13387, new CosInterpolate(), probablisticRandom);

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
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                assertTrue(true);
            }
        });

        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
        }

    }
}
