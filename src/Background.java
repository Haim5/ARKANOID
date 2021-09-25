import biuoop.DrawSurface;

/**
 * @author Haim Hegger.
 * Background class.
 */
public class Background implements Sprite {
    private SpriteCollection sc;

    /**
     * Constructor.
     * @param sc sprite collection.
     */
    public Background(SpriteCollection sc) {
        this.sc = sc;
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.sc.drawAllOn(d);
    }

    @Override
    public void timePassed() {
        this.sc.notifyAllTimePassed();
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
