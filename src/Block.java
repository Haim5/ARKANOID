import biuoop.DrawSurface;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/**
 * a block class.
 * @author Haim Hegger.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color c;
    private List<HitListener> hitListeners;
    private Counter count;

    /**
     * Constructing a block from rectangle and color.
     * @param r a rectangle
     * @param c a color
     */
    public Block(Rectangle r, Color c) {
        this.rect = r;
        this.c = c;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * contructor.
     * @param r rectangle.
     * @param c color.
     * @param count a counter, used in order to make special blocks.
     */
    public Block(Rectangle r, Color c, Counter count) {
        this.rect = r;
        this.c = c;
        this.count = count;
        this.hitListeners = new LinkedList<>();
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double v = currentVelocity.getSpeed() + 3;
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();
        double x1 = this.getCollisionRectangle().getUpperLeft().getX();
        double x2 = x1 + this.getCollisionRectangle().getWidth();
        double cpx = collisionPoint.getX();
        if (Math.abs(cpx - x1) <= v || Math.abs(cpx - x2) <= v) {
            dx = -dx;
        }
        double y1 = this.getCollisionRectangle().getUpperLeft().getY();
        double y2 = y1 + this.getCollisionRectangle().getHeight();
        double cpy = collisionPoint.getY();
        if (Math.abs(cpy - y1) <= v || Math.abs(cpy - y2) <= v) {
            dy = -dy;
        }
        decreaseCount();
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * get the value of the counter.
     * @return the counters value.
     */
    public int getCountVal() {
        return this.getCounter().getValue();
    }

    /**
     * get the counter.
     * @return counter.
     */
    private Counter getCounter() {
        return this.count;
    }
    /**
     * decrease the counters value by 1.
     */
    private void decreaseCount() {
        if (this.count != null) {
            this.count.decrease(1);
        }
    }
    @Override
    public void timePassed() {
    }
    @Override
    public void drawOn(DrawSurface d) {
        int x1 = (int) this.rect.getUpperLeft().getX();
        int y1 = (int) this.rect.getUpperLeft().getY();
        int x2 = (int) this.getCollisionRectangle().getWidth();
        int y2 = (int) this.getCollisionRectangle().getHeight();
        d.setColor(this.c);
        d.fillRectangle(x1, y1, x2, y2);
        d.setColor(Color.black);
        d.drawRectangle(x1, y1, x2, y2);
    }
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify all listeners about a hit.
     * @param hitter the hitting ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * remove a block from the game.
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
}
