package company;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Util {

    public static String getRandomName() {
        byte[] name = new byte[10];
        new Random().nextBytes(name);
        return new String(name);
    }

    public static BufferedImage img16(BufferedImage img, int x, int y) {
        BufferedImage img16 = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        img16.getGraphics().drawImage(img, -x * 16, -y * 16, null);
        return img16;
    }
}
