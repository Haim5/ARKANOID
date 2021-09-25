/**
 * @author Haim Hegger.
 * Score Tracking Listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter counter object.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * default constructor, sets a default counter (0).
     */
    public ScoreTrackingListener() {
        this.currentScore = new Counter();
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }

    /**
     * get the score.
     * @return score (int).
     */
    public int getScore() {
        return this.currentScore.getValue();
    }
}
