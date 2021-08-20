import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * @author Haim Hegger
 * animationrunner class.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * Constructor.
     * @param g gui object.
     * @param fps frames per second.
     */
    public AnimationRunner(GUI g, int fps) {
        this.gui = g;
        this.framesPerSecond = fps;
    }

    /**
     * run an animation loop.
     * @param animation the running animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = getGUI().getDrawSurface();
            animation.doOneFrame(d);
            getGUI().show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * get the GUI object.
     * @return GUI.
     */
    public GUI getGUI() {
        return this.gui;
    }
}