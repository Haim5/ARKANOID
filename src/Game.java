import java.util.LinkedList;
import java.util.List;

/**
 * @author Haim Hegger.
 * Ass3Game class.
 */
public class Game {
    /**
     * main class.
     * @param args command line input (not used).
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new LinkedList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());
        levels.add(new FinalFour());
        GameFlow gf = new GameFlow();
        gf.runLevels(levels);
    }
}
