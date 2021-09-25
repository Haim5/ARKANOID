import biuoop.DrawSurface;

/**
 * @author Haim Hegger.
 * LevelIndicator class.
 */
public class LevelIndicator implements Sprite {
    private final String txt;

    /**
     * Constructor.
     * @param str text to write on the block.
     */
    public LevelIndicator(String str) {
        this.txt = str;
    }

    /**
     * get the String object.
     * @return String.
     */
    private String getTxt() {
        return this.txt;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText((GameLevel.WIDTH / 2) + 120, 22, "Level Name: " + this.getTxt(), 19);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
