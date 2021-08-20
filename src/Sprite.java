import biuoop.DrawSurface;

/**
 * @author Haim Hegger.
 * Sprite interface.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d a DrawSurface object.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add the sprite to the game.
     * @param game Game object.
     */
    void addToGame(GameLevel game);
}
