package company.PerlinNoise;

import company.PerlinNoise.Interpolator.Interpolator;
import company.PerlinNoise.Interpolator.Linear;

import java.util.ArrayList;
import java.util.Random;

public class ProbablisticRandom {
    private ArrayList<ProbPoint> probPoints = new ArrayList();
    private Interpolator interpolator;

    public ProbablisticRandom(float l, float r, Interpolator interpolator) {
        probPoints.add(new ProbPoint(0, l));
        probPoints.add(new ProbPoint(1, r));
        this.interpolator = interpolator;
    }

    public ProbablisticRandom(float l, float r) {
        this(l, r, new Linear());
    }

    public void addProbablisticPoint(float x, float y) {
        for (int i = 1; i < probPoints.size(); i++) {
            ProbPoint probPoint = probPoints.get(i);
            if (probPoint.x > x) {
                probPoints.add(i, new ProbPoint(x, y));
                return;
            }
        }
    }

    public float getRandom(Random random) {
        float prob, randX;
        do {
            randX = random.nextFloat();
            Pair<ProbPoint, ProbPoint> pointsSurr = getPointsSurr(randX);
            ProbPoint l = pointsSurr.first;
            ProbPoint r = pointsSurr.second;
            float b = (randX - r.x) / (l.x - r.x);
            prob = interpolator.interpolate(l.y, r.y, b);
        } while (random.nextFloat() > prob);
        return randX;
    }

    private Pair<ProbPoint, ProbPoint> getPointsSurr(float between) {
        for (int i = 1; i < probPoints.size(); i++) {
            ProbPoint probPoint = probPoints.get(i);
            if (probPoint.x >= between) {
                return new Pair<>(probPoints.get(i - 1), probPoints.get(i));
            }
        }
        return null;
    }


    class ProbPoint {
        final float x, y;

        ProbPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
