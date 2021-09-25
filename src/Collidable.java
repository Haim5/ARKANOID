/**
 * @author Haim Hegger
 * Collidable interface.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return Rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * get a new velocity after a collision.
     * @param hitter the hitting ball.
     * @param collisionPoint the point of collision.
     * @param currentVelocity a given velocity.
     * @return Velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
