package Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    public static byte[] imgToByteArray(BufferedImage img){
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        // Write to output stream
        try {
            ImageIO.write(img, "png", bao);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bao.toByteArray();
    }
    

}
