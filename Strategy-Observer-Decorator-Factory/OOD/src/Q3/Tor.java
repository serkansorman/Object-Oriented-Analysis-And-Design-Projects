package Q3;

/**
 * Suit Tor
 */
public class Tor extends Suit {

    public Tor() {
        description = "Tor Suit";
    }

    /**
     * @return Cost of Tor suit
     */
    @Override
    public double cost() {
        return 5000;
    }

    /**
     * @return weight of Tor suit
     */
    @Override
    public double weight() {
        return 50;
    }
}
