/**
 * @author Haim Hegger
 * Velocity Class
 */
public class Velocity {
    private double dx;
    private double dy;
    private static final int EXPO = 2;

    /**
     * constructs a new Velocity.
     * @param dx addition in x axis.
     * @param dy addition in y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * the method returns an Velocity from speed and angle.
     * @param angle direction in numbers (not radians).
     * @param speed the overall speed.
     * @return new Velocity(dx, dy).
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * the method returns dx.
     * @return dx.
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * the method returns dy.
     * @return dy.
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * the method returns a new point.
     * @param p a given point.
     * @return (p.getX() + this.dx), (p.getY() + this.dy).
     */
    public Point applyToPoint(Point p) {
        return new Point((p.getX() + this.dx), (p.getY() + this.dy));
    }

    /**
     * get the speed (a number without direction).
     * @return speed (double)
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.getDX(), EXPO) + Math.pow(this.getDY(), EXPO));
    }
}