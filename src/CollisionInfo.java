/**
 * @author Haim Hegger.
 * CollisionInfo Class.
 */
public class CollisionInfo {
    private Point collision;
    private Collidable obj;

    /**
     * make a new object from a collision point and object.
     * @param p the point of collision.
     * @param o the object collided.
     */
    public CollisionInfo(Point p, Collidable o) {
        this.collision = p;
        this.obj = o;
    }
    // the point at which the collision occurs.

    /**
     * get the point at which the collision occurs.
     * @return Point.
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * get the collidable object involved in the collision.
     * @return a Collidable object.
     */
    public Collidable collisionObject() {
        return this.obj;

    }
}
