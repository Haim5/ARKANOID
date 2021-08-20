import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Haim Hegger.
 * Button class.
 */
public class Button {
    private final Block b;
    private final String txt;
    private Block bigger;
    private final String secondTxt;
    private int num;
    private boolean isReactive;
    private static final int LIM = 17;

    /**
     * Constructor.
     * @param b Block.
     * @param s1 text for normal situation.
     * @param s2 text for situation 2.
     * @param num button serial number.
     * @param reaction is the button reacting to keys being pressed.
     */
    public Button(Block b, String s1, String s2, int num, boolean reaction) {
        this.b = b;
        this.txt = s1;
        makeBiggerBlock(s2.length());
        this.secondTxt = s2;
        this.num = num;
        this.isReactive = reaction;
    }

    /**
     * alternative cinstructor.
     * @param b block.
     * @param txt text for both situations.
     * @param num serial number.
     * @param bool is reactive.
     */
    public Button(Block b, String txt, int num, boolean bool) {
        this(b, txt, txt, num, bool);
    }

    /**
     * draw the button on the drawsurfacce.
     * @param d DrawSurface obj.
     */
    public void drawYourself(DrawSurface d) {
        this.b.drawOn(d);
        this.printTxt(d, this.txt);
    }

    /**
     * draw the button when the user is hovering over the the button.
     * @param d the DrawSurface.
     */
    public void hoverOver(DrawSurface d) {
        this.bigger.drawOn(d);
        this.printTxt(d, this.secondTxt);
    }

    /**
     * make the bigger block for hovering situation.
     * @param len the texts length.
     */
    private void makeBiggerBlock(int len) {
        Rectangle help = this.b.getCollisionRectangle();
        int diffX = 15;
        int diffY = 7;
        if (len > LIM) {
            diffX = 100;
        }
        Point p = new Point(help.getUpperLeft().getX() - diffX, help.getUpperLeft().getY() - diffY);
        Rectangle r = new Rectangle(p, help.getWidth() + (2 * diffX), help.getHeight() + (2 * diffY));
        this.bigger = new Block(r, Color.green);
    }

    /**
     * get the block obj.
     * @return Block.
     */
    public Block getBlock() {
        return this.b;
    }

    /**
     * print the wanted text on the button.
     * @param d Draw Surface obj.
     * @param str the text.
     */
    private void printTxt(DrawSurface d, String str) {
        Point p = this.b.getCollisionRectangle().getUpperLeft();
        int width = (int) this.b.getCollisionRectangle().getWidth();
        int height = (int) this.b.getCollisionRectangle().getHeight();
        int size = 25;
        int space = 8 * str.length();
        if (str.length() > LIM) {
            size = 18;
            space /= 2;
            space += 26;
        }
        d.drawText((int) p.getX() + (width / 2) - (space), (int) p.getY() + (height / 2) + 13, str, size);
    }

    /**
     * return the option 1 text.
     * @return String.
     */
    public String getStr1() {
        return this.txt;
    }

    /**
     * get the serial number.
     * @return int.
     */
    public int getNum() {
        return this.num;
    }

    /**
     * is the button reacting to keys being pressed.
     * @return true/false.
     */
    public boolean isReacting() {
        return this.isReactive;
    }
}
