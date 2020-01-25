package Q3;

public class AutoRifle extends AccessoryDecorator {
    /**
     * Suit to be decorated
     */
    private Suit suit;

    public AutoRifle(Suit suit) {
        this.suit = suit;
    }

    /**
     * @return description of suit and AutoRifle description
     */
    @Override
    public String getDescription() {
        return suit.getDescription() + ", AutoRifle";
    }

    /**
     * Gives cost of suit + AutoRifle cost
     * @return sum of suit and AutoRifle cost
     */
    @Override
    public double cost() {
        return suit.cost() + 30;
    }

    /**
     * Gives weight of suit + AutoRifle weight
     * @return sum of suit and AutoRifle weight
     */
    @Override
    public double weight() {
        return suit.weight() + 1.5;
    }
}
