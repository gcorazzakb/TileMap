package company.PerlinNoise;

import company.PerlinNoise.Interpolator.CosInterpolate;
import company.PerlinNoise.Interpolator.Interpolator;
import company.PerlinNoise.Interpolator.Linear;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PerlinNoise {

    final int freqX,freqY;
    final int seed;
    private Interpolator interpolator;
    private ProbablisticRandom probablisticRandom;

    public PerlinNoise(int freqX, int freqY, int seed, Interpolator interpolator, ProbablisticRandom probablisticRandom) {
        this.freqX = freqX;
        this.freqY = freqY;
        this.seed= seed;
        this.interpolator= interpolator;
        this.probablisticRandom= probablisticRandom;
    }

    public PerlinNoise(int freqX, int freqY) {
        this(freqX,freqY,new Random().nextInt(), new Linear(), new ProbablisticRandom(1,1));
    }

    public PerlinNoise(int freqX, int freqY, int seed) {
        this(freqX,freqY, seed, new Linear(), new ProbablisticRandom(1,1));
    }

    public PerlinNoise(int freqX, int freqY, int seed, Interpolator interpolator) {
        this(freqX,freqY,seed,interpolator, new ProbablisticRandom(1,1));
    }

    public float getValue(float x, float y){
        int rasterX = (int) Math.floor(x/freqX);
        int rasterY = (int) Math.floor(y/freqX);

        float betweenX=(x-rasterX*freqX)/freqX;
        float betweenY=(y-rasterY*freqY)/freqY;

        float ul = rasterValue(rasterX, rasterY);
        float ur = rasterValue(rasterX+1, rasterY);
        float dl = rasterValue(rasterX, rasterY+1);
        float dr = rasterValue(rasterX+1, rasterY+1);

        float u = interpolator.interpolate(ul, ur, betweenX);
        float d = interpolator.interpolate(dl, dr, betweenX);
        return interpolator.interpolate(u, d, betweenY);

    }

    private float rasterValue(int x, int y){
        Random random = new Random(x);
        Random r1 = new Random(seed + random.nextInt()+ random.nextInt());
        Random random1 = new Random(y);
        Random r2 = new Random( r1.nextInt() + random1.nextInt() + random1.nextInt());
        return probablisticRandom.getRandom(r2);
    }

    public BufferedImage getImg(float X, float Y, int width, int height){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float value = getValue(X + x, Y + y);
                if(value>=0 && value<1)
                g.setColor(new Color(value,value,value));
                g.drawRect(x,y,1,1);
            }
        }
        return img;
    }


}
