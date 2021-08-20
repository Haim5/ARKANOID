/**
 * @author Haim Hegger.
 * Counter class.
 */
public class Counter {
    private int num;

    /**
     * constructor, sets num to zero as default.
     */
    public Counter() {
        this.setNum(0);
    }

    /**
     * constructor with value.
     * @param n the value.
     */
    public Counter(int n) {
        this.setNum(n);
    }

    /**
     * set a value to the counter.
     * @param n the value.
     */
    private void setNum(int n) {
        this.num = n;
    }

    /**
     * add number to current count.
     * @param number the added number.
     */
    public void increase(int number) {
        this.setNum(this.getValue() + number);
    }

    /**
     * subtract number from current count.
     * @param number the value.
     */
    public void decrease(int number) {
        this.setNum(this.getValue() - number);
    }
    /**
     * get current count value.
     * @return the value.
     */
    public int getValue() {
        return this.num;
    }

    /**
     * get the value as a String.
     * @return String.
     */
    public String getValStr() {
        return String.valueOf(this.getValue());
    }
}
