/**
 * @author HAim Hegger.
 * HitNotifier interface.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl HitNotifier object.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl HitNotifier object.
     */
    void removeHitListener(HitListener hl);
}
