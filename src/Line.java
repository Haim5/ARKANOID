import java.util.List;

/**
 * @author Haim Hegger
 * a Line Class
 */
public class Line {
    private Point start;
    private Point end;
    private static final double AVG = 2;

    /**
     * a constructor from two points.
     * @param start the starting point.
     * @param end the end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * a constructor from 4 values.
     * @param x1 x value of start point.
     * @param y1 y value of start point.
     * @param x2 x value of end point.
     * @param y2 y value of end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculates the length of the line.
     * @return line's length.
     */
    public double length() {
        return this.start.distance(this.end());
    }

    /**
     * calculates the middle of the line.
     * @return the middle point.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end().getX()) / AVG;
        double y = (this.start.getY() + this.end().getY()) / AVG;
        return new Point(x, y);
    }

    /**
     * returns the starting point.
     * @return this.start.
     */
    public Point start() {
        return this.start;
    }

    /**
     * returns the ending point.
     * @return this.end.
     */
    public Point end() {
        return this.end;
    }

    /**
     * calculates the line's slope.
     * @return the line's slope.
     */
    private double slope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * checks if the line has a slope. (checks if the slope can be calculated).
     * @return true if there is a slope false otherwise.
     */
    private boolean isSlopeable() {
        return this.end.getX() != this.start.getX();
    }

    /**
     * the general form of a line is y = mx + b. this method return the b.
     * @return b.
     */
    private double getB() {
        return this.start.getY() - (this.start.getX() * this.slope());
    }
    /**
     * the function checks if two line2 are intersecting.
     * @param other another line to compare with.
     * @return true if they intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double m1 = 0, m2 = 0, b1 = 0, b2 = 0;
        boolean slope1 = false, slope2 = false;
        if (this.isSlopeable()) {
            m1 = this.slope();
            b1 = this.getB();
            slope1 = true;
        }
        if (other.isSlopeable()) {
            m2 = other.slope();
            b2 = other.getB();
            slope2 = true;
        }
        if (slope1 && slope2 && m1 == m2) {
            return this.isIntersectingEqualSlopes(other, b1, b2);
        }
        if (!slope1 || !slope2) {
            return slopeless(this, other, slope1, slope2);
        }
        double x = (b2 - b1) / (m1 - m2);
        if ((Math.min(this.start.getX(), this.end.getX()) > x) || (x > Math.max(this.start.getX(), this.end.getX()))) {
            return false;
        }
        if (Math.min(other.start.getX(), other.end.getX()) > x || x > Math.max(other.start.getX(), other.end.getX())) {
            return false;
        }
        return true;
    }

    /**
     * a help  method that checks if 2 lines with equal slopes intersect.
     * @param other another line to compare with.
     * @param b1 the b value of line1.
     * @param b2 the b value of line2.
     * @return true if intersect, false otherwise.
     */
    private boolean isIntersectingEqualSlopes(Line other, double b1, double b2) {
        if (b1 != b2) {
            return false;
        }
        double max = Math.max(this.start.getX(), this.end.getX());
        double min = Math.min(this.start.getX(), this.end.getX());
        if (other.start().getX() <= max && other.start().getX() >= min) {
            return true;
        }
        if (other.end().getX() <= max && other.end().getX() >= min) {
            return true;
        }
        max = Math.max(other.start().getX(), other.end().getX());
        min = Math.min(other.start().getX(), other.end().getX());
        if (this.start.getX() <= max && this.start.getX() >= min) {
            return true;
        }
        if (this.end.getX() <= max && this.end.getX() >= min) {
            return true;
        }
        return false;
    }

    /**
     * a help method that checks if 2 lines without slopes intersect.
     * @param other another line to compare with.
     * @return true if the lines intersect false otherwise.
     */
    private boolean isIntersectingNoSlopes(Line other) {
        if (other.start.getX() != this.start.getX()) {
            return false;
        }
        Line small = shorter(this, other);
        Line large = longer(this, other);
        double comp = small.start.getY();
        double max = Math.max(large.start().getY(), large.end().getY());
        double min = Math.min(large.start().getY(), large.end().getY());
        if (comp <= max && comp >= min) {
            return true;
        }
        comp = small.end.getY();
        if (comp <= max && comp >= min) {
            return true;
        }
        return false;
    }

    /**
     * a method that checks which line is shorter.
     * @param l1 line 1.
     * @param l2 line 2.
     * @return the shorter line between l1  & l2.
     */
    private Line shorter(Line l1, Line l2) {
        if (l1.length() <= l2.length()) {
            return l1;
        }
        return l2;
    }

    /**
     * a method that checks which line is longer.
     * @param l1 line 1.
     * @param l2 line 2.
     * @return the longer between l1 & l2.
     */
    private Line longer(Line l1, Line l2) {
        if (l1.length() >= l2.length()) {
            return l1;
        }
        return l2;
    }

    /**
     * a method that checks if two lines intersect when one has a slope and the other does not.
     * @param l line with a slope.
     * @param noSlope line without a slope.
     * @param b b value of l
     * @param m l slope
     * @return true if the lines intersect false otherwise.
     */
    private boolean isIntersectingOneSlope(Line l, Line noSlope, double b, double m) {
        double y = (m * noSlope.start.getX()) + b;
        if (y > Math.max(l.start.getY(), l.end.getY()) || y < Math.min(l.start.getY(), l.end.getY())) {
            return false;
        }
        double max = Math.max(noSlope.start.getY(), noSlope.end.getY());
        double min = Math.min(noSlope.start.getY(), noSlope.end.getY());
        if (y > max || y < min) {
            return false;
        }
        return true;
    }

    /**
     * a method used when one or more of the lines does not have a slope.
     * the method sends the parameters to the adequate method.
     * @param l1 line 1
     * @param l2 line 2
     * @param s1 true/false if li has a slope.
     * @param s2 true/false if l2 has a slope.
     * @return true if the lines intersect' false otherwise. using other methods to decide.
     */
    private boolean slopeless(Line l1, Line l2, boolean s1, boolean s2) {
        if (!s1 && !s2) {
            return l1.isIntersectingNoSlopes(l2);
        }
        if (!s2) {
            return isIntersectingOneSlope(l1, l2, l1.getB(), l1.slope());
        }
        return isIntersectingOneSlope(l2, l1, l2.getB(), l2.slope());
    }

    /**
     * the methods returns an intersection point between 2 lines, if one exists.
     * @param other another line to compare with.
     * @return if there is an intersection point the methods returns the point, otherwise null.
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        double b1, b2, m1, m2;
        boolean s1 = this.isSlopeable();
        boolean s2 = other.isSlopeable();
        if (s1 && s2) {
            b1 = this.getB();
            b2 = other.getB();
            m1 = this.slope();
            m2 = other.slope();
            if (m1 != m2) {
                double x = (b2 - b1) / (m1 - m2);
                if (this.isXInRange(x) && other.isXInRange(x)) {
                    return new Point(x, (m1 * x) + b1);
                }
                return null;
            }
            if (b1 != b2) {
                return null;
            }
            double max1 = Math.max(this.start.getX(), this.end.getX());
            double min1 = Math.min(this.start.getX(), this.end.getX());
            double max2 = Math.max(other.start.getX(), other.end.getX());
            double min2 = Math.min(other.start.getX(), other.end.getX());
            if (max2 > max1) {
                if (min2 == max1) {
                    return new Point(min2, (min2 * m2) + b2);
                }
                return null;
            }
            if (max2 < max1 && min1 == max2) {
                return new Point(min1, (min1 * m1) + b1);
            }
            return null;
        }
        if (s1) {
            return this.intersectionWithOneSlope(other);
        }
        if (s2) {
            return other.intersectionWithOneSlope(this);
        }
        if (this.isPoint() || other.isPoint()) {
            return intersectionWithPoints(this, other);
        }
        double max1 = Math.max(this.start.getY(), this.end.getY());
        double min1 = Math.min(this.start.getY(), this.end.getY());
        double max2 = Math.max(other.start.getY(), other.end.getY());
        double min2 = Math.min(other.start.getY(), other.end.getY());
        if (max1 < max2) {
            if (min2 == max1) {
                return new Point(other.start.getX(), min2);
            }
            return null;
        }
        if (max1 > max2) {
            if (min1 == max2) {
                return new Point(other.start.getX(), min1);
            }
            return null;
        }
        if (max1 == max2 && (min1 == max1 || min2 == max2)) {
            return new Point(other.start.getX(), max1);
        }
        return null;
    }

    /**
     * a method for the edge case that one line is a point.
     * @param l1 line 1.
     * @param l2 line 2.
     * @return intersection point if there is, null otherwise.
     */
    private Point intersectionWithPoints(Line l1, Line l2) {
        boolean s1 = l1.isPoint();
        boolean s2 = l2.isPoint();
        if (s1 && s2) {
            if (l1.start().equals(l2.start())) {
                return l1.start();
            }
            return null;
        }
        if (s1) {
            if (l1.start().getX() != l2.start().getX() || !l2.isYInRange(l1.start().getY())) {
                return null;
            }
            return l1.start();
        }
        if (l2.start().getX() != l1.start().getX() || !l1.isYInRange(l2.start().getY())) {
            return null;
        }
        return l2.start();
    }

    /**
     * the method checks if a value is in the lines y range.
     * @param y the value.
     * @return true if in range, false otherwise.
     */
    private boolean isYInRange(double y) {
        if (Math.max(this.end.getY(), this.start.getY()) < y) {
            return false;
        }
        if (Math.min(this.end.getY(), this.start.getY()) > y) {
            return false;
        }
        return true;
    }

    /**
     * the method checks if a value is in the lines x range.
     * @param x the value.
     * @return true if in range, false otherwise.
     */
    private boolean isXInRange(double x) {
        if (Math.max(this.end.getX(), this.start.getX()) < x) {
            return false;
        }
        if (Math.min(this.end.getX(), this.start.getX()) > x) {
            return false;
        }
        return true;
    }

    /**
     * check if the lines are equal.
     * @param other the line to compare with.
     * @return true if the line are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this.length() != other.length()) {
            return false;
        }
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return this.start.equals(other.end) && this.end.equals(other.start);
    }

    /**
     * checks if a line is a point.
     * @return true if it a point, false otherwise.
     */
    private boolean isPoint() {
        return this.start.distance(this.end) == 0;
    }

    /**
     * find intersection point when one line does not have a lope and the other does.
     * @param other line to compare with.
     * @return the intersection point it there is one, null if not.
     */
    private Point intersectionWithOneSlope(Line other) {
        double y = (this.slope() * other.start.getX()) + this.getB();
        if (!this.isYInRange(y)) {
            return null;
        }
        return new Point(other.start.getX(), y);
    }

    /**
     * get the closest intersection point between the start of the line to a given rectangle.
     * @param rect the rectangle the line collides with.
     * @return closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this);
        if (list.isEmpty()) {
            return null;
        }
        Point p = list.get(0);
        double min = p.distance(this.start());
        double temp;
        for (int i = 1; i < list.size(); i++) {
            temp = list.get(i).distance(this.start());
            if (temp < min) {
                min = temp;
                p = list.get(i);
            }
        }
        return p;
    }
}
