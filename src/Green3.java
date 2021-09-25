import java.util.LinkedList;
import java.util.List;
import java.awt.Color;

/**
 * @author Haim Hegger.
 * Green 3 (level 3) class.
 */
public class Green3 implements LevelInformation {
    private static final int ROWS = 4;
    private static final int BLOCKS = 9;
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new LinkedList<>();
        int angle = -120;
        int speed = 5;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            vel.add(Velocity.fromAngleAndSpeed(angle + (180 * i), speed));
        }
        return vel;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 190;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Point p = new Point(0, 0);
        return new Block(new Rectangle(p, GameLevel.WIDTH, GameLevel.HEIGHT), new Color(100, 150, 0));
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new LinkedList<>();
        int wb = GameLevel.WIDTH - GameLevel.BORDERH;
        for (int i = 0; i <= ROWS; i++) {
            Color c = new Color(i, 30 * i, 20 * i);
            for (int k = 0; k <= BLOCKS + i - ROWS; k++) {
                Point temp = new Point(wb - ((k + 1) * GameLevel.BLOCKW), 275 - (i * GameLevel.BLOCKH));
                Block b = new Block(new Rectangle(temp, GameLevel.BLOCKW, GameLevel.BLOCKH), c, new Counter(1));
                list.add(b);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
