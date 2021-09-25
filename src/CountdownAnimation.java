import biuoop.DrawSurface;

/**
 * @author Haim Hegger.
 * CountdownAnimation class.
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection collection;
    private Counter c;
    private long start;
    private long time;

    /**
     * Constructor.
     * @param numOfSeconds animation length.
     * @param countFrom the number the counter starts from.
     * @param gameScreen the screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.collection = gameScreen;
        this.c = new Counter(countFrom);
        this.start = System.currentTimeMillis();
        this.time = (long) ((1000 * numOfSeconds) / (double) (countFrom));
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.collection.drawAllOn(d);
        if (System.currentTimeMillis() - this.start < this.time) {

            d.drawText(d.getWidth() / 2, d.getHeight() / 2 + 50, this.c.getValStr(), 60);
        } else {
            this.c.decrease(1);
            this.start = System.currentTimeMillis();
        }
    }

    @Override
    public boolean shouldStop() {
        if (this.c.getValue() <= 0) {
            return true;
        }
        return false;
    }
}
