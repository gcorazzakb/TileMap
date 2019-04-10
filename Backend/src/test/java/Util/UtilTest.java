package Util;

import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void getRandomName() {
        String randomName = Util.getRandomName();
        assertTrue(randomName !=null && !randomName.equals(""));
    }

    @Test
    public void img16() {
        BufferedImage bufferedImage = Util.img16(new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB), 0, 0);
        assertEquals(16, bufferedImage.getWidth());
        assertEquals(16, bufferedImage.getHeight());
    }

    @Test
    public void imgToByteArray() {
        byte[] bytes = Util.imgToByteArray(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));
        assertTrue(bytes.length>100);
    }
}
