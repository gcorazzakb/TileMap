package company.PerlinNoise.Interpolator;

public class CosInterpolate implements  Interpolator {
    @Override
    public float interpolate(float x, float y, float between) {
        return (float)(x+(y-x)*(1-(Math.cos(between*Math.PI)+1)/2));
    }
}
