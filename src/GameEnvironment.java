import java.util.LinkedList;
import java.util.List;

/**
 * @author Haim Hegger.
 * GameEnvironment Class.
 */
public class GameEnvironment {
    private List<Collidable> list;

    /**
     * Constructor, makes a new list.
     */
    public GameEnvironment() {
        this.list = new LinkedList<>();
    }
    /**
     * add a collidable object to the game environment.
     * @param c the new object.
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }

    /**
     * get a CollisionInfo object if there is a collision.
     * @param trajectory the trajectory of the object.
     * @return if no collision is made null, otherwise new CollisionInfo object.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.list.isEmpty()) {
            return null;
        }
        int size = this.list.size();
        List<Collidable> collideWith = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if (!this.list.get(i).getCollisionRectangle().intersectionPoints(trajectory).isEmpty()) {
                collideWith.add(this.list.get(i));
            }
        }
        //check if there were collisions.
        if (collideWith.isEmpty()) {
            return null;
        }
        int k = 0;
        size = collideWith.size();
        Point min = trajectory.closestIntersectionToStartOfLine(collideWith.get(k).getCollisionRectangle());
        Point temp;
        double minDist = trajectory.start().distance(min);
        double comp;
        //find the closest collision.
        for (int i = 1; i < size; i++) {
            temp = trajectory.closestIntersectionToStartOfLine(collideWith.get(i).getCollisionRectangle());
            comp = trajectory.start().distance(temp);
            if (comp < minDist) {
                minDist = comp;
                min = temp;
                k = i;
            }
        }
        return new CollisionInfo(min, collideWith.get(k));
    }

    /**
     * remove a colliadble.
     * @param c the object we want to remove.
     */
    public void remove(Collidable c) {
        this.list.remove(c);
    }
    /**
     * get the Collidables list.
     * @return the object's list.
     */
    public List<Collidable> getList() {
        return this.list;
    }
}
