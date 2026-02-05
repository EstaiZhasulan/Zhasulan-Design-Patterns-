package edu.narxoz.galactic.bodies;

public abstract class CelestialBody {
    private String name;
    private double x;
    private double y;

    protected CelestialBody(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(CelestialBody other) {
        if (other == null) {
            throw new IllegalArgumentException("Target body cannot be null");
        }
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    @Override
    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }
}