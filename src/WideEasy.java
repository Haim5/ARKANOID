import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author HAim HEgger.
 * Wide Easy (level 2) class.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = 5;
        int angle = 120;
        List<Velocity> vel = new LinkedList<>();
        for (int i = 0; i <= (this.numberOfBalls() - 1) / 2; i++) {
            vel.add(Velocity.fromAngleAndSpeed(angle + (8 * i), speed));
            vel.add(Velocity.fromAngleAndSpeed(angle + (8 * i) + 90, speed));
        }
        return vel;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Point p = new Point(0, 0);
        Block bg = new Block(new Rectangle(p, GameLevel.WIDTH, GameLevel.HEIGHT), Color.pink);
        List<Sprite> ls = new LinkedList<>();
        ls.add(bg);
        return new Background(new SpriteCollection(ls));
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new LinkedList<>();
        int w = ((GameLevel.WIDTH - 2 * GameLevel.BORDERH) / 15);
        int h = GameLevel.BLOCKH;
        for (int i = 0; i < 15; i++) {
            Color c = new Color(15 * i, 30, 30);
            Point temp = new Point(GameLevel.BORDERH + (i * w), 200);
            Block bl = new Block(new Rectangle(temp, w, h), c, new Counter(1));
            list.add(bl);
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
