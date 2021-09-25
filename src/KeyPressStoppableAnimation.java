import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Haim Hegger.
 * KeyPressStoppableAnimation class.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyBoard;
    private final String key;
    private final String secondKey;
    private final Animation animation;
    private boolean isAlreadyPressed;
    private String lastKey;

    /**
     * Constructor.
     * @param sensor keyboardsensor object.
     * @param key a String to identify.
     * @param animation animation object.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.secondKey = null;
        this.keyBoard = sensor;
        this.isAlreadyPressed = true;
        this.lastKey = null;
    }

    /**
     * Constructor with two options.
     * @param sensor keyboardsensor object.
     * @param key1 first String to identify.
     * @param key2 Second String to identify.
     * @param animation  animation object.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key1, String key2, Animation animation) {
        this.animation = animation;
        this.key = key1;
        this.secondKey = key2;
        this.keyBoard = sensor;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        if (this.secondKey != null) {
            return this.twoKeysShouldStop();
        }
        if (this.keyBoard.isPressed(this.key)) {
            return !this.isAlreadyPressed;
        } else {
            this.isAlreadyPressed = false;
            return false;
        }
    }

    /**
     * Used when there are 2 possible keys.
     * @return true/false
     */
    private boolean twoKeysShouldStop() {
        if (this.keyBoard.isPressed(this.key) || this.keyBoard.isPressed(this.secondKey)) {
            this.lastKey = this.whatIsPressed();
            return !this.isAlreadyPressed;
        } else {
            this.isAlreadyPressed = false;
            return false;
        }
    }

    /**
     * Used to differentiate between the possible keys.
     * @return String - the pressed key.
     */
    private String whatIsPressed() {
        if (this.keyBoard.isPressed(this.key)) {
            return this.key;
        }
        return this.secondKey;
    }

    /**
     * returns the last pressed key.
     * @return string.
     */
    public String getLastPressedKey() {
        return this.lastKey;
    }
}
