import java.awt.Color;
import biuoop.DrawSurface;

/**
 * @author Haim Hegger
 * Ball class.
 */
public class Ball implements Sprite {
    private Point center;
    private final int r;
    private Color color;
    private Velocity v;
    private GameEnvironment game;

    /**
     * constructor from 2 values for a point, a radius and color.
     * @param x value at x axis.
     * @param y value at y axis.
     * @param r the radius.
     * @param color the color.
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.initGameEnv();
    }

    /**
     * constructor from 2 values for a point, a radius ,a color, GameEnvironment object and velocity.
     * @param x value at x axis.
     * @param y value at y axis.
     * @param r the radius.
     * @param c the color.
     * @param g GameEnvironment object.
     * @param v velocity
     */
    public Ball(int x, int y, int r, Color c, GameEnvironment g, Velocity v) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = c;
        this.game = g;
        this.v = v;
    }

    /**
     * Constructor.
     * @param x value at x axis
     * @param y value at y axis
     * @param r radius
     * @param c color
     * @param v velocity
     */
    public Ball(int x, int y, int r, Color c, Velocity v) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = c;
        this.v = v;
        this.initGameEnv();
    }

    /**
     * this method initializes a gameEnvironment object for the ball.
     */
    private void initGameEnv() {
        this.game = new GameEnvironment();
        double w = this.center.getWidth();
        double h = this.center.getHeight();
        Point p = new Point(0, 0);
        Rectangle screen = new Rectangle(p, w, h);
        this.game.addCollidable(new Block(screen, Color.yellow));
    }

    /**
     * constructor from point, radius and color.
     * @param center center point.
     * @param r radius.
     * @param color color.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * get the ball's color.
     * @return this.color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * set a new color.
     * @param c the new color
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * get the radius.
     * @return this.r.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * get the balls center.
     * @return this.center.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * returns the x value of the center point.
     * @return this.center.getX().
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * get the value of the ball's center at y axis.
     * @return this.center.getY().
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * drawing the ball.
     * @param d a surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle((int) this.getX(), (int) this.getY(), this.r);
        //return d;
    }

    /**
     * set a velocity to the ball.
     * @param vel velocity
     */
    public void setVelocity(Velocity vel) {
        this.v = vel;
    }

    /**
     * set velocity from dx and dy.
     * @param dx velocity in x axis.
     * @param dy velocity in y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * get the ball's velocity.
     * @return this.v.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * get the GameEnvironment object.
     * @return GameEnvironment object.
     */
    private GameEnvironment getGameEnv() {
        return this.game;
    }
    /**
     * moves the ball to the next point according to the velocity and colliding objects.
     */
    public void moveOneStep() {
        if (this.getVelocity().getDY() == 0) {
            this.setVelocity(this.getVelocity().getDX(), 1);
        }
        if (this.getVelocity().getDX() == 0) {
            this.setVelocity(1, this.getVelocity().getDY());
        }
        double endX = this.getCenter().getX() + this.getVelocity().getDX();
        double endY = this.getCenter().getY() + this.getVelocity().getDY();
        Line trajectory = new Line(this.getCenter(), new Point(endX, endY));
        CollisionInfo collision = this.getGameEnv().getClosestCollision(trajectory);
        if (collision != null) {
            Point collide = collision.collisionPoint();
            Collidable obj = collision.collisionObject();
            this.setVelocity(obj.hit(this, collide, this.getVelocity()));
        }
        this.setCenter(this.getVelocity().applyToPoint(this.getCenter()));
    }

    /**
     * set a new center point.
     * @param p the new center.
     */
    private void setCenter(Point p) {
        this.center = p;
    }
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove a ball from the game.
     * @param g the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}