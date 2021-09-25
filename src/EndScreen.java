import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author Haim Hegger.
 * EndScreen class.
 */
public class EndScreen implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;
    private final int score;
    private final boolean didWin;
    private Counter location;
    private Button[] arr = new Button[2];
    private Boolean shouldExitGame;
    private SpriteCollection sc;

    /**
     * Constructor.
     * @param keyboard KeyBoardSensor object.
     * @param score the players score.
     * @param didWin did the player win the game.
     */
    public EndScreen(KeyboardSensor keyboard, int score, boolean didWin) {
        this.keyboard = keyboard;
        this.score = score;
        this.stop = false;
        this.didWin = didWin;
        Block b1 = new Block(new Rectangle(new Point(200, 450), 180, 80), Color.RED);
        arr[0] = new Button(b1, "RESTART", 0, true);
        Block b2 = new Block(new Rectangle(new Point(400, 450), 180, 80), Color.RED);
        arr[1] = new Button(b2, "EXIT", 1, true);
        this.location = new Counter();
        this.sc = new SpriteCollection();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.gray);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        for (Button b: this.arr) {
            if (b.getNum() == this.location.getValue()) {
                b.hoverOver(d);
            } else {
                b.drawYourself(d);
            }
        }
        d.setColor(Color.BLACK);
        if (this.didWin) {
            d.drawText(155, d.getHeight() / 2, "You Win! Your score is " + this.score, 32);
            this.confetti(d);
        } else {
            d.drawText(155, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (this.location.getValue() < this.arr.length - 1) {
                this.location.increase(1);
            }
        }
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (this.location.getValue() > 0) {
                this.location.decrease(1);
            }
        }
        if (this.keyboard.isPressed(KeyboardSensor.ENTER_KEY)) {
            if (this.arr[this.location.getValue()].isReacting()) {
                this.shouldExitGame = this.arr[this.location.getValue()].getStr1().equals("EXIT");
                this.stop = true;
            }
        }
    }

    /**
     * Throws confetti.
     * @param d DrawSurface obj.
     */
    private void confetti(DrawSurface d) {
        LinkedList<Sprite> balls = new LinkedList<>();
        Random r = new Random();
        int x = r.nextInt(GameLevel.WIDTH);
        Color c = new Color((int) (Math.random() * 0x1000000));
        Ball ball = new Ball(x, 0, 5, c, Velocity.fromAngleAndSpeed(2, 2));
        balls.add(ball);
        this.sc.addSprite(balls);
        this.sc.notifyAllTimePassed();
        this.sc.drawAllOn(d);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * checks if the user want to close the game.
     * @return true/false.
     */
    public boolean shouldExit() {
        return this.shouldExitGame;
    }
}