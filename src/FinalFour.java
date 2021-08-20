import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Haim HEgger.
 * Final Four (level 4) class.
 */
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new LinkedList<>();
        int speed = 5;
        int angle = -120;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            vel.add(Velocity.fromAngleAndSpeed(angle + (-i * angle), speed));
        }
        return vel;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 220;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Point p = new Point(0, 0);
        return new Block(new Rectangle(p, GameLevel.WIDTH, GameLevel.HEIGHT), new Color(30, 20, 140));
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new LinkedList<>();
        int w = ((GameLevel.WIDTH - 2 * GameLevel.BORDERH) / 15);
        int h = GameLevel.BLOCKH;
        int y = 200;
        int rows = 7;
        int blocksPerRow = 15;
        for (int k = 0; k < rows; k++) {
            Color c = new Color(5 * k, 5 * k, 15 * k);
            for (int i = 0; i < blocksPerRow; i++) {
                Point temp = new Point(GameLevel.BORDERH + (i * w), y + (k * h));
                Block bl = new Block(new Rectangle(temp, w, h), c, new Counter(1));
                list.add(bl);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
