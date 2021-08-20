import java.util.LinkedList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * @author Haim Hegger.
 * SpriteCollection Class.
 */
public class SpriteCollection {
    private List<Sprite> list;

    /**
     * Constructor. Makes an object with List<Sprite>.
     * @param l a list of sprites.
     */
    public SpriteCollection(List<Sprite> l) {
        this.list = l;
    }

    /**
     * alternative constructor without arguments.
     */
    public SpriteCollection() {
        this(new LinkedList<>());
    }

    /**
     * add a new sprite.
     * @param s the new sprite
     */
    public void addSprite(Sprite s) {
        this.list.add(s);
    }

    /**
     * add an entire list.
     * @param l a list of sprites.
     */
    public void addSprite(List<Sprite> l) {
        for (Sprite s : l) {
            this.addSprite(s);
        }
    }

    /**
     * get the sprite list.
     * @return the object's list.
     */
    public List<Sprite> getList() {
        return this.list;
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        int size = this.list.size() - 1;
        for (int i = 0; i < size - 1; i++) {
            this.getList().get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d a DrawSurface object.
     */
    public void drawAllOn(DrawSurface d) {
        int size = this.getList().size();
        for (int i = 0; i < size; i++) {
            this.getList().get(i).drawOn(d);
        }
    }

    /**
     * remove a sprite object from the collection.
     * @param s the object we want to remove.
     */
    public void remove(Sprite s) {
        this.list.remove(s);
    }

    /**
     * remove every item in the list.
     */
    public void empty() {
        this.list = new LinkedList<>();
    }
}
