import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Haim Hegger.
 * Game class.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final int RADIUS = 3;
    public static final int BLOCKW = 40;
    public static final int BLOCKH = 22;
    public static final int BORDERH = 36;
    private static final int BONUS = 100;
    public static final int FPS = 60;
    private Counter counter;
    private Counter ballCounter;
    private Counter scoreCounter;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private final ScoreTrackingListener stl;
    private AnimationRunner animationRunner;
    private Paddle paddle;
    private LevelInformation info;

    /**
     * Constructor. adds the needed fields.
     */
    public GameLevel() {
        this.sprites = new SpriteCollection(new ArrayList<>());
        this.environment = new GameEnvironment();
        this.counter = new Counter();
        this.ballCounter = new Counter();
        this.scoreCounter = new Counter();
        this.stl = new ScoreTrackingListener(this.scoreCounter);
        this.animationRunner = new AnimationRunner(new GUI("Game", WIDTH, HEIGHT), FPS);
    }

    /**
     * Constructor.
     * @param inf information used to create the level.
     * @param c Counter.
     * @param gui GUI.
     */
    public GameLevel(LevelInformation inf, Counter c, GUI gui) {
        this.scoreCounter = c;
        this.sprites = new SpriteCollection(new ArrayList<>());
        this.environment = new GameEnvironment();
        this.stl = new ScoreTrackingListener(this.scoreCounter);
        this.info = inf;
        this.animationRunner = new AnimationRunner(gui, FPS);
    }

    /**
     * get the LevelInformation obj.
     * @return LevelInformation.
     */
    private LevelInformation getInfo() {
        return this.info;
    }
    /**
     * add a Collidable to GameEnvironment.
     * @param c the new Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a new Sprite to sprites.
     * @param s the new Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * initializes the game by adding lists, balls and blocks.
     */
    public void initialize() {
        this.environment = new GameEnvironment();
        this.counter = new Counter(this.getInfo().numberOfBlocksToRemove());
        this.blockRemover = new BlockRemover(this, this.counter);
        this.ballCounter = new Counter(this.getInfo().numberOfBalls());
        this.ballRemover = new BallRemover(this, this.ballCounter);
        if (this.getInfo().getBackground() != null) {
            this.getInfo().getBackground().addToGame(this);
        }
        makeBlockers();
        for (int i = 0; i < this.info.numberOfBlocksToRemove(); i++) {
            Block temp = this.getInfo().blocks().get(i);
            temp.addHitListener(this.blockRemover);
            temp.addHitListener(this.stl);
            temp.addToGame(this);
        }
        for (int i = 0; i < this.info.numberOfBalls(); i++) {
            int x = WIDTH / 2;
            int y = (int) midScreen().getY() + (BORDERH / 7) - (3 * RADIUS);
            if (this.getInfo().levelName().equals("Direct Hit")) {
                x -= 250;
            }
            Ball ball = new Ball(x,
                    y,
                    RADIUS,
                    Color.cyan,
                    this.environment,
                    this.getInfo().initialBallVelocities().get(i));
            ball.addToGame(this);
        }
        Block b = new Block(new Rectangle(new Point(0, 0), WIDTH, BORDERH), Color.blue);
        ScoreIndicator si = new ScoreIndicator(b, this.scoreCounter);
        si.addToGame(this);
        LevelIndicator li = new LevelIndicator(this.getInfo().levelName());
        li.addToGame(this);
    }

    /**
     * makes the frame blocks and adds them to the game.
     */
    private void makeBlockers() {
        Color c = Color.cyan;
        int k = 0;
        Rectangle[] arr = new Rectangle[2 * Rectangle.CORNERS];
        arr[k++] = new Rectangle(new Point(0, BORDERH), WIDTH, 2);
        arr[k++] = new Rectangle(new Point(0, BORDERH), WIDTH, BORDERH);
        arr[k++] = new Rectangle(new Point(-10, HEIGHT - 2), 2 * WIDTH, BORDERH);
        arr[k++] = new Rectangle(new Point(-10, HEIGHT - 2), 2 * WIDTH, BORDERH);
        arr[k++] = new Rectangle(new Point(0, BORDERH), 2, 2 * HEIGHT);
        arr[k++] = new Rectangle(new Point(0, BORDERH), BORDERH, 2 * HEIGHT);
        double x = WIDTH - BORDERH;
        arr[k++] = new Rectangle(new Point(x + (BORDERH / 2), BORDERH), 2, 2 * HEIGHT);
        arr[k] = new Rectangle(new Point(x, BORDERH), BORDERH, 2 * HEIGHT);
        Block[] blocks = new Block[2 * Rectangle.CORNERS];
        for (int i = 0; i < 2 * Rectangle.CORNERS; i++) {
            blocks[i] = new Block(arr[i], c, new Counter(-1));
            if (arr[i].getUpperLeft().getY() == HEIGHT - 2 || arr[i].getWidth() == 2 || arr[i].getHeight() == 2) {
                blocks[i].addHitListener(this.ballRemover);
            }
            blocks[i].addToGame(this);
        }
    }

    /**
     * get the middle point of the screen, used as the paddle starting position.
     * @return a point.
     */
    private Point midScreen() {
        return new Point((WIDTH / 2) - (this.getInfo().paddleWidth() / 2), HEIGHT - ((BORDERH) / 2) - 1);
    }

    /**
     * running the game. starting the animation loop. adding the paddle.
     */
    public void run() {
        biuoop.KeyboardSensor k = this.animationRunner.getGUI().getKeyboardSensor();
        Point start = this.midScreen();
        if (this.getInfo().levelName().equals("Direct Hit")) {
            start = new Point(2 * BORDERH, start.getY());
        }
        this.paddle = new Paddle(k, new Rectangle(start,
                this.getInfo().paddleWidth(),
                BORDERH / 7),
                Color.yellow,
                this.getInfo().paddleSpeed());
        this.paddle.addToGame(this);
        this.animationRunner.run(new CountdownAnimation(2, 3, this.sprites));
        this.animationRunner.run(this);
    }

    /**
     * returns the block counter.
     * @return counter.
     */
    public Counter getGameCounter() {
        return this.counter;
    }

    /**
     * returns the balls counter.
     * @return ballCounter.
     */
    public Counter getGameBallCounter() {
        return this.ballCounter;
    }

    /**
     * set a new counter as the blocks counter.
     * @param c the new counter.
     */
    public void setCounter(Counter c) {
        this.counter = c;
    }

    /**
     * set a new counter as the ball counter.
     * @param c the new counter.
     */
    public void setBallCounter(Counter c) {
        this.ballCounter = c;
    }

    /**
     * remove a collidble object.
     * @param c the object we want to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.remove(c);
    }

    /**
     * remove a sprite object.
     * @param s the sprite we want ro remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.animationRunner.getGUI().getKeyboardSensor().isPressed("p")
        || this.animationRunner.getGUI().getKeyboardSensor().isPressed("פ")
        || this.animationRunner.getGUI().getKeyboardSensor().isPressed("P")) {
            KeyPressStoppableAnimation kpsa = new KeyPressStoppableAnimation(this.getGui().getKeyboardSensor(),
                    KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.animationRunner.getGUI().getKeyboardSensor()));
            this.animationRunner.run(kpsa);
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        this.paddle.timePassed();
    }

    @Override
    public boolean shouldStop() {
        if (this.getGameCounter().getValue() == 0) {
            this.scoreCounter.increase(BONUS);
            return true;
        }
        if (this.getGameBallCounter().getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * get the GUI obj.
     * @return GUI.
     */
    public GUI getGui() {
        return this.animationRunner.getGUI();
    }

    /**
     * get the AnimationRunner obj.
     * @return AnimationRunner.
     */
    public AnimationRunner getAR() {
        return this.animationRunner;
    }
}
