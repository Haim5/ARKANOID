/**
 * @author Haim Hegger
 * Point Class
 */
public class Point {
    private static final int EXPO = 2;
    private double x;
    private double y;
    // width and height are limit values used in animations.
    private int width;
    private int height;

    /**
     * Constructing a new Point from 2 numbers.
     * @param x value at x axis.
     * @param y value at y axis.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this method returns the distance between 2 points.
     * @param other another point to compare with.
     * @return distance (double).
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((this.x - other.x), EXPO) + Math.pow((this.y - other.y), EXPO));
    }

    /**
     * this method checks if 2 points are equal.
     * @param other another point to compare with.
     * @return true if the points are equal, false otherwise (boolean).
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -15);
        return Math.abs(this.getX() - other.getX()) < epsilon && Math.abs(this.getY() - other.getY()) < epsilon;
    }

    /**
     * a method to get the value at x axis.
     * @return this.x.
     */
    public double getX() {
        return this.x;
    }

    /**
     * a method to get the value at y axis.
     * @return this.y.
     */
    public double getY() {
        return this.y;
    }

    /**
     * set a new value at x axis.
     * @param num the new value.
     */
    public void setX(double num) {
        this.x = num;
    }

    /**
     * set a new value at x axis.
     * @param num the new value.
     */
    public void setY(double num) {
        this.y = num;
    }

    /**
     * set the width limit - useful for animations.
     * @param w the limit.
     */
    public void setWidth(int w) {
        this.width = w;
    }

    /**
     * set the height limit - useful for animations.
     * @param h the limit.
     */
    public void setHeight(int h) {
        this.height = h;
    }

    /**
     * returns the width limit.
     * @return this.width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * returns the height limit.
     * @return this.height.
     */
    public int getHeight() {
        return this.height;
    }
}
