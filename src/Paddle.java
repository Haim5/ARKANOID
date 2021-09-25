import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Haim Hegger.
 * Paddle class.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle r;
    private final Color color;
    private int move;

    /**
     * Constructor.
     * @param k KeyboardSensor obj
     * @param rect Rectangle obj
     * @param c Color
     * @param speed paddle speed.
     */
    public Paddle(biuoop.KeyboardSensor k, Rectangle rect, Color c, int speed) {
        this.keyboard = k;
        this.r = rect;
        this.color = c;
        this.move = speed;
    }

    /**
     * get the paddles speed.
     * @return int
     */
    private int getSpeed() {
        return this.move;
    }

    /**
     * get the paddle's color.
     * @return Color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * move the paddle to the left given the screen limit.
     */
    public void moveLeft() {
        double x = this.getCollisionRectangle().getUpperLeft().getX();
        if (x - this.getSpeed() >= GameLevel.BORDERH) {
            x -= this.getSpeed();
        }
        Point p = new Point(x, this.getCollisionRectangle().getUpperLeft().getY());
        this.getCollisionRectangle().moveUpperLeft(p);
    }

    /**
     * move the paddle to the right given the screen limit.
     */
    public void moveRight() {
        double x = this.getCollisionRectangle().getUpperLeft().getX();
        if (x + this.getCollisionRectangle().getWidth() + this.getSpeed() <= GameLevel.WIDTH - GameLevel.BORDERH) {
            x += this.getSpeed();
        }
        Point p = new Point(x, this.getCollisionRectangle().getUpperLeft().getY());
        this.getCollisionRectangle().moveUpperLeft(p);
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            return;
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        double x = this.getCollisionRectangle().getUpperLeft().getX();
        double y = this.getCollisionRectangle().getUpperLeft().getY();
        double w = this.getCollisionRectangle().getWidth();
        double h = this.getCollisionRectangle().getHeight();
        d.setColor(this.getColor());
        d.fillRectangle((int) x, (int) y, (int) w, (int) h);
        d.setColor(Color.black);
        d.drawRectangle((int) x, (int) y, (int) w, (int) h);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.r;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getY() != this.getCollisionRectangle().getUpperLeft().getY()) {
            return new Velocity(currentVelocity.getDX() * (-1), currentVelocity.getDY() * (-1));
        }
        double w = this.getCollisionRectangle().getWidth();
        double spot = collisionPoint.getX() - this.getCollisionRectangle().getUpperLeft().getX();
        int piece = (int) (Math.round((spot / w) * 5));
        if (piece == 0) {
            piece = 1;
        }
        if (piece == 3) {
            return new Velocity(currentVelocity.getDX(), (-1) * currentVelocity.getDY());
        }
        if (collisionPoint.getX() == this.getCollisionRectangle().getUpperLeft().getX()) {
            piece = 1;
        }
        if (collisionPoint.getX() == this.getCollisionRectangle().getUpperLeft().getX() + w) {
            piece = 5;
        }
        int angle = 180 + ((3 - piece) * 30);
        return Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed());
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}