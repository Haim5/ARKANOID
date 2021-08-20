import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.LinkedList;

/**
 * @author Haim Hegger.
 * Main menu class.
 */
public class MainMenu implements Animation {
    private Counter location;
    private Counter pacer;
    private final KeyboardSensor keyboard;
    private boolean stop;
    private Background bg;
    private Button[] arr = new Button[BUTTONS];
    private static final String[] MSG = {"PLAY", "PLAY",
            "INSTRUCTIONS", "Move - Arrows | Select - ENTER | Pause - P",
            "EXIT", "EXIT"};
    private static final int BUTTONS = 3;
    private static final int GAP = 6;
    private Boolean shouldExitGame;

    /**
     * Constructor.
     * @param k KeyboardSensor object.
     */
    public MainMenu(KeyboardSensor k) {
        this.location = new Counter(1);
        this.pacer = new Counter(this.location.getValue() * GAP);
        this.keyboard = k;
        this.stop = false;
        this.bg = defaultBG();
        makeButtons();
        this.shouldExitGame = null;
    }

    /**
     * initializes the needed buttons.
     */
    private void makeButtons() {
        Color c = Color.RED;
        int width = 210;
        int height = 65;
        int gap = 20;
        int midX = (GameLevel.WIDTH / 2) - (width / 2);
        int midY = GameLevel.HEIGHT / 2 - 30;
        for (int i = 0; i < BUTTONS; i++) {
            Rectangle temp = new Rectangle(new Point(midX, midY + height + (4 * i * gap)), width, height);
            this.arr[i] = new Button(new Block(temp, c), MSG[2 * i], MSG[(2 * i) + 1], i,
                    (MSG[2 * i].equals(MSG[(2 * i) + 1])));
        }
    }

    /**
     * Make a background.
     * @return Background.
     */
    private Background defaultBG() {
        LinkedList<Sprite> l = new LinkedList<>();
        Rectangle r = new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT);
        l.add(new Block(r, new Color((int) (Math.random() * 0x1000000))));
        return new Background(new SpriteCollection(l));
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.bg.drawOn(d);
        d.setColor(Color.lightGray);
        d.fillOval((GameLevel.WIDTH - 500) / 2 - 50, 60, 610, 200);
        d.setColor(Color.BLACK);
        d.drawOval((GameLevel.WIDTH - 500) / 2 - 50, 60, 610, 200);
        d.drawText(140, 190, "ARKANOID", 100);
        for (Button b : this.arr) {
            if (this.location.getValue() == b.getNum()) {
                b.hoverOver(d);
            } else {
                b.drawYourself(d);
            }
        }
        if (this.keyboard.isPressed(KeyboardSensor.DOWN_KEY)) {
            if (this.location.getValue() < BUTTONS - 1) {
                this.pacer.increase(1);
                if (this.pacer.getValue() % GAP == 0) {
                    this.location.increase(1);
                }
            }
        }
        if (this.keyboard.isPressed(KeyboardSensor.UP_KEY)) {
            if (this.location.getValue() > 0) {
                this.pacer.decrease(1);
                if (this.pacer.getValue() % GAP == 0) {
                    this.location.decrease(1);
                }
            }
        }
        if (this.keyboard.isPressed(KeyboardSensor.ENTER_KEY)) {
            if (this.arr[this.location.getValue()].isReacting()) {
                this.shouldExitGame = this.arr[this.location.getValue()].getStr1().equals("EXIT");
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * check if the user want ro close the game.
     * @return true/false.
     */
    public boolean shouldExit() {
        return this.shouldExitGame;
    }
}
