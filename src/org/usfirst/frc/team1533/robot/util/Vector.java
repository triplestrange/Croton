package org.usfirst.frc.team1533.robot.util;

/**
 * A 2D mathematical vector.
 * 
 * @author Duncan Page
 */
public class Vector {
    public double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the vector's angle in radians between -pi and pi
     * 
     * @return angle in radians
     */
    public double getAngle() {
        return Math.atan2(y, x);
    }

    /**
     * Gets the vector's magnitude.
     * 
     * @return magnitude
     */
    public double getMagnitude() {
        return Math.hypot(x, y);
    }

    /**
     * Multiplies the vector by a scalar.
     * @param scalar scalar to multiply
     * @return a reference to this vector
     */
    public Vector multiply(double scalar) {
        x *= scalar;
        y *= scalar;
        return this;
    }

    /**
     * Adds another vector to this vector.
     * @param v the second vector to add
     * @return a reference to this vector
     */
    public Vector add(Vector v) {
        x += v.x;
        y += v.y;
        return this;
    }

    /**
     * Subtracts another vector from this vector.
     * @param v the second vector to subtract
     * @return a reference to this vector
     */
    public Vector subtract(Vector v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    /**
     * Rotates the vector by an angle in radians. Positive is a counter-clockwise rotation.
     * @param radians angle in radians
     * @return a reference to this vector
     */
    public Vector rotate(double radians) {
        double tempx = x;
        x = x * Math.cos(radians) - y * Math.sin(radians);
        y = tempx * Math.sin(radians) + y * Math.cos(radians);
        return this;
    }

    /**
     * Returns a copy of this vector.
     */
    public Vector clone() {
        return new Vector(x, y);
    }
}
