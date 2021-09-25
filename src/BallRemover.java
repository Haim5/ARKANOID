/**
 * @author Haim Hegger.
 * BallRemover listener.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param game game object.
     * @param removedBalls counter object.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.game.setBallCounter(new Counter(this.game.getGameBallCounter().getValue() - 1));
    }
}
