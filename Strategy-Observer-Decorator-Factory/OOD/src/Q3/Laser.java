package Q3;

public class Laser extends AccessoryDecorator {
    private Suit suit;

    public Laser(Suit suit) {
        this.suit = suit;
    }

    /**
     * @return description of suit and Laser description
     */
    @Override
    public String getDescription() {
        return suit.getDescription() + ", Laser";
    }

    /**
     * Gives cost of suit + Laser cost
     * @return sum of suit and Laser cost
     */
    @Override
    public double cost() {
        return suit.cost() + 200;
    }

    /**
     * Gives weight of suit + Laser weight
     * @return sum of suit and Laser weight
     */
    @Override
    public double weight() {
        return suit.weight() + 5.5;
    }
}
