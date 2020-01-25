package Q3;

/**
 * Dec Suit
 */
public class Dec extends Suit {

    public Dec() {
        description = "Dec Suit";
    }

    /**
     * @return Cost of Dec suit
     */
    @Override
    public double cost() {
        return 500;
    }

    /**
     *
     * @return weight of Dec suit
     */
    @Override
    public double weight() {
        return 25;
    }
}
