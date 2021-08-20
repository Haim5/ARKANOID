import biuoop.KeyboardSensor;
import biuoop.GUI;
import java.util.List;

/**
 * @author Haim Hegger.
 * GameFlow class.
 */
public class GameFlow {
    private Counter score;
    private KeyboardSensor key;
    private GUI g;
    private AnimationRunner ar;

    /**
     * Constructor, makes a gui and a counter.
     */
    public GameFlow() {
        this.score = new Counter();
        this.g = new GUI("ARKANOID", GameLevel.WIDTH, GameLevel.HEIGHT);
        this.key = this.g.getKeyboardSensor();
    }

    /**
     * get the counter.
     * @return Counter.
     */
    private Counter getCounter() {
        return this.score;
    }

    /**
     * run each level in a given list.
     * @param levels a list of level to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        MainMenu mm = new MainMenu(this.key);
        boolean exitGame;
        do {
            this.score = new Counter();
            boolean didBreak = false;
            for (LevelInformation levelInfo : levels) {
                GameLevel level = new GameLevel(levelInfo, this.getCounter(), this.g);
                level.initialize();
                this.ar = level.getAR();
                this.ar.run(mm);
                if (mm.shouldExit()) {
                    this.g.close();
                }
                while (level.getGameBallCounter().getValue() > 0 && level.getGameCounter().getValue() > 0) {
                    level.run();
                }
                if (level.getGameBallCounter().getValue() == 0) {
                    didBreak = true;
                    break;
                }
            }
            EndScreen es = new EndScreen(this.key, this.getCounter().getValue(), !didBreak);
            this.ar.run(es);
            exitGame = es.shouldExit();
        } while (!exitGame);
        this.g.close();
    }
}
