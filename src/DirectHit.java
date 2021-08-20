import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Haim Hegger.
 * Direct Hit level class (level 1).
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new LinkedList<>();
        vel.add(new Velocity(4, -5));
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Point p = new Point(0, 0);
        Rectangle t = new Rectangle(p, GameLevel.WIDTH, GameLevel.HEIGHT);
        Block top = new Block(t, new Color(0, 181, 226));
        Point p2 = new Point(0, (2 * GameLevel.HEIGHT) / 3);
        Rectangle b = new Rectangle(p2, GameLevel.WIDTH, GameLevel.HEIGHT);
        Block bot = new Block(b, new Color(0, 154, 23));
        Point l = new Point(450, 100);
        Rectangle left = new Rectangle(l, 4, 320);
        Block leftPole = new Block(left, Color.white);
        Point r = new Point(550, 100);
        Rectangle right = new Rectangle(r, 4, 320);
        Block rightPole = new Block(right, Color.white);
        Point c = new Point(l.getX(), 345);
        Rectangle cross = new Rectangle(c, r.getX() - l.getX(), 4);
        Block crossBar = new Block(cross, Color.white);
        Point temp = new Point(50, 83);
        Rectangle board = new Rectangle(temp, 91, 75);
        Block scoreBoard = new Block(board, Color.lightGray);
        List<Block> scoreBoardList = new LinkedList<>();
        scoreBoardList.add(new Block(new Rectangle(new Point(55, 88), 10, 25),
                Color.green.darker()));
        scoreBoardList.add(new Block(new Rectangle(new Point(65, 88), 10, 25), Color.WHITE));
        scoreBoardList.add(new Block(new Rectangle(new Point(75, 88), 10, 25), Color.RED));
        scoreBoardList.add(new Block(new Rectangle(new Point(105, 88), 10, 25), Color.blue));
        scoreBoardList.add(new Block(new Rectangle(new Point(115, 88), 10, 25), Color.WHITE));
        scoreBoardList.add(new Block(new Rectangle(new Point(125, 88), 10, 25), Color.RED));
        scoreBoardList.add(new Block(new Rectangle(new Point(55, 120), 30, 5), Color.black));
        scoreBoardList.add(new Block(new Rectangle(new Point(80, 120), 5, 30), Color.black));
        scoreBoardList.add(new Block(new Rectangle(new Point(55, 145), 30, 5), Color.black));
        scoreBoardList.add(new Block(new Rectangle(new Point(55, 120), 5, 30), Color.black));
        scoreBoardList.add(new Block(new Rectangle(new Point(105, 120), 30, 5), Color.black));
        scoreBoardList.add(new Block(new Rectangle(new Point(105, 132), 30, 5), Color.black));
        scoreBoardList.add(new Block(new Rectangle(new Point(105, 145), 30, 5), Color.black));
        scoreBoardList.add(new Block(new Rectangle(new Point(130, 120), 5, 30), Color.black));
        List<Sprite> sl = new LinkedList<>();
        sl.add(top);
        sl.add(bot);
        sl.add(leftPole);
        sl.add(rightPole);
        sl.add(crossBar);
        sl.add(scoreBoard);
        sl.addAll(scoreBoardList);
        return new Background(new SpriteCollection(sl));
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new LinkedList<>();
        Point temp = new Point(480, GameLevel.HEIGHT / 4);
        Block b = new Block(new Rectangle(temp, GameLevel.BLOCKW, GameLevel.BLOCKH), Color.PINK, new Counter(1));
        list.add(b);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}