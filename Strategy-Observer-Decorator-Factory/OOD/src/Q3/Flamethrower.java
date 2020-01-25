package Q3;

public class Flamethrower extends AccessoryDecorator {

    /**
     * Suit to be decorated
     */
    private Suit suit;

    public Flamethrower(Suit suit) {
        this.suit = suit;
    }

    /**
     * @return description of suit and Flamethrower description
     */
    @Override
    public String getDescription() {
        return suit.getDescription() + ", Flamethrower";
    }

    /**
     * Gives cost of suit + Flamethrower cost
     * @return sum of suit and Flamethrower cost
     */
    @Override
    public double cost() {
        return suit.cost() + 50;
    }

    /**
     * Gives weight of suit + Flamethrower weight
     * @return sum of suit and Flamethrower weight
     */
    @Override
    public double weight() {
        return suit.weight() + 2;
    }
}
