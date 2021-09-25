import  biuoop.DrawSurface;

/**
 * @author Haim Hegger
 * Animation interface.
 */
public interface Animation {
    /**
     * make one frame of the animation.
     * @param d drawsurface object
     */
    void doOneFrame(DrawSurface d);

    /**
     * determine if the animation should stop.
     * @return boolean value.
     */
    boolean shouldStop();
}
