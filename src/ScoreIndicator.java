import biuoop.DrawSurface;

/**
 * @author Haim Hegger.
 * ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private final Block block;

    /**
     * Constructor.
     * @param b block object.
     */
    public ScoreIndicator(Block b) {
        this.block = b;
        this.score = new Counter();
    }

    /**
     * constructor.
     * @param b block object.
     * @param c counter object.
     */
    public ScoreIndicator(Block b, Counter c) {
        this.block = b;
        this.score = c;
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
        d.drawText((GameLevel.WIDTH / 5) - 40, 22, "Score: " + this.getScore(), 19);
    }

    /**
     * get the score.
     * @return the counters value.
     */
    public int getScore() {
        return this.score.getValue();
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
