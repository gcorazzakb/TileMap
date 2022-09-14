package company.PerlinNoise.Interpolator;

public class Linear implements Interpolator {
    @Override
    public float interpolate(float x, float y, float between) {
        return x+(y-x)*between;
    }
}
