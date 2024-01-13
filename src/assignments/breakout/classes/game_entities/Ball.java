package assignments.breakout.classes.game_entities;

import acm.graphics.GOval;

import java.util.HashMap;
import java.util.Map;

public class Ball extends GOval {


    private double radius;
    private int delay;

    private double vx, vy;

    /**
     * constructors
     */
    public Ball(double v, double v1, int delay) {
        super(v, v1);
        this.radius = ((int) v) == (int) v1 ? v / 2 : -1;
        this.delay = delay;
        vx = vy = 0;
    }

    public Ball(double v, double v1, double v2, double v3, int delay) {
        super(v, v1, v2, v3);
        this.radius = ((int) v2) == (int) v3 ? v2 / 2 : -1;
        this.delay = delay;
        vx = vy = 0;
    }

    /**
     * getter - setters
     */

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }


    /**
     * coordinate counting
     */
    /*
    returns coordinate distances main points
     on the ball from 90 degree to 45 degree
     */
    public HashMap<Double, Double> distancesOfPoints() {
        HashMap<Double, Double> distances = new HashMap<>();

        int pointCount = 4;
        double degreeStep = 45.0 / (pointCount + 1);

        for (int i = 0; i < pointCount; i++) {
            double radianAlpha = Math.toRadians(90 - degreeStep * (i + 1));

            double x = (radius + 1) * Math.cos(radianAlpha);
            double y = (radius + 1) * Math.sin(radianAlpha);

            distances.put(x, y);
        }
        distances.put(0.0, 1.0);

        return distances;
    }

    public HashMap<Double, Double> putPoint(int side) {
        HashMap<Double, Double> coordinates = new HashMap<>();
        HashMap<Double, Double> distances = distancesOfPoints();

        double centerX = this.getX() + radius;
        double centerY = this.getY() + radius;

        for (Map.Entry<Double, Double> entry : distances.entrySet()) {
            double key, value, x, y;
            /* side 1 = top. side 2 = left. side 3 = bottom. side 4 = right. */
            if (side == 1 || side == 3) {
                key = entry.getKey();
                value = entry.getValue();

                x = centerX + key;
                y = side == 1 ? centerY - value : centerY + value;
                coordinates.put(x, y);

                x = centerX - key;
                coordinates.put(-x, y);
            } else if (side == 2 || side == 4) {
                key = entry.getValue();
                value = entry.getKey();

                x = side == 2 ? centerX - key : centerX + key;
                y = centerY - value;
                coordinates.put(x, y);

                y = centerY + value;
                coordinates.put(x, y);
            }
        }
        return coordinates;
    }

    public HashMap<Double, Double> topPoints() {
        return putPoint(1);
    }

    public HashMap<Double, Double> bottomPoints() {
        return putPoint(3);
    }

    public HashMap<Double, Double> leftPoints() {
        return putPoint(2);
    }

    public HashMap<Double, Double> rightPoints() {
        return putPoint(4);
    }

}
