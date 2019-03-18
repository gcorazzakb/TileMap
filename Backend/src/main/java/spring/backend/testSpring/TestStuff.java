package spring.backend.testSpring;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;

@RestController
public class TestStuff {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin(origins = "*")
    @RequestMapping("/greeting")
    public TestPOJO greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new TestPOJO(name.toUpperCase());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/img", method = RequestMethod.GET, produces = "image/png")
    public String testSendImg() {
        BufferedImage img = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(5,5,5,5);

        // Create a byte array output stream.
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        // Write to output stream
        try {
            ImageIO.write(img, "png", bao);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return "{\"a\": \""+encoder.encode(bao.toByteArray())+"\" }\n";
    }

}

