package Q3;

public class RocketLauncher extends AccessoryDecorator {

    private Suit suit;

    public RocketLauncher(Suit suit) {
        this.suit = suit;
    }

    /**
     * @return description of suit and RocketLauncher description
     */
    @Override
    public String getDescription() {
        return suit.getDescription() + ", RocketLauncher";
    }

    /**
     * Gives cost of suit + RocketLauncher cost
     * @return sum of suit and RocketLauncher cost
     */
    @Override
    public double cost() {
        return suit.cost() + 150;
    }

    /**
     * Gives weight of suit + RocketLauncher weight
     * @return sum of suit and RocketLauncher weight
     */
    @Override
    public double weight() {
        return suit.weight() + 7.5;
    }
}
