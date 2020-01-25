package Q3;

public class Ora extends Suit {

    public Ora() {
        description = "Ora Suit";
    }

    /**
     * @return Cost of Dec suit
     */
    @Override
    public double cost() {
        return 1500;
    }

    /**
     * @return weight of Dec suit
     */
    @Override
    public double weight() {
        return 30;
    }
}
