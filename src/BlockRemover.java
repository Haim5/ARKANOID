/**
 * @author Haim Hegger.
 * BlockRemover listener.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game game object.
     * @param removedBlocks Counter object.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCountVal() != 0) {
            return;
        }
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.game.setCounter(new Counter(this.game.getGameCounter().getValue() - 1));
    }
}
