import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

/**
 * @author Haim Hegger.
 * Pause Screen Class.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     * @param k KeyBoardSensor obj.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}