package Q3;

public abstract class Suit {
    protected String description = "Undefined Suit";

    public String getDescription(){
        return description;
    }

    /**
     *
     * @return cost of suit
     */
    public abstract double cost();

    /**
     *
     * @return cost of weight
     */
    public abstract double weight();
}
