import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class.
 * @author Haim Hegger.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    public static final int CORNERS = 4;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upper left point of the rectangle.
     * @param width the rectangle's width.
     * @param height the rectangle's height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * cha the upper left point.
     * @param p the new upper left point.
     */
    public void moveUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * Return a (possibly empty) List of intersection points with a given line.
     * @param line the line to check.
     * @return a list of intersection points with the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Line[] l = this.rectLines();
        Point p;
        for (int i = 0; i < CORNERS; i++) {
            p = line.intersectionWith(l[i]);
            if (p != null) {
                list.add(p);
            }
        }
        return list;
    }

    /**
     * return an array of the rectangle's lines.
     * @return an array of lines.
     */
    public Line[] rectLines() {
        Line[] lines = new Line[CORNERS];
        Point[] points = getCorners();
        lines[0] = new Line(points[0], points[1]);
        lines[1] = new Line(points[1], points[2]);
        lines[2] = new Line(points[2], points[3]);
        lines[3] = new Line(points[3], points[0]);
        return lines;
    }
    /**
     * get the width.
     * @return (double) width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * get the height.
     * @return (double) height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * get the upper left point of th rectangle.
     * @return a point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * get the rectangle's corner points.
     * @return an array of points.
     */
    public Point[] getCorners() {
        double x = this.getUpperLeft().getX();
        double y = this.getUpperLeft().getY();
        Point[] p = new Point[CORNERS];
        p[0] = new Point(x, y); // upper left
        p[1] = new Point(x + this.getWidth(), y); // upper right
        p[2] = new Point(x + this.getWidth(), y + this.getHeight()); // bottom right
        p[3] = new Point(x, y + this.getHeight()); // bottom left
        return p;
    }
}
