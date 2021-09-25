import java.util.List;

/**
 * @author Haim Hegger.
 * Level information interface.
 */
public interface LevelInformation {
    /**
     * get the number of balls in the level.
     * @return int.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball (initialBallVelocities().size() == numberOfBalls()).
     * @return List of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * get the paddles speed.
     * @return int.
     */
    int paddleSpeed();

    /**
     * get the paddles width.
     * @return int.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return level name - string.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return Sprite.
     */
    Sprite getBackground();

    /**
     * a list of the blocks that make up this level.
     * @return list.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * @return int.
     */
    int numberOfBlocksToRemove();
}
