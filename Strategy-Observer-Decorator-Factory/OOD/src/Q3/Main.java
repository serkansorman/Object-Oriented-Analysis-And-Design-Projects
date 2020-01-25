package Q3;

public class Main {

    public static void main(String... args) {

        Suit suit = new Dec();
        System.out.println(suit.getDescription() + " Total Weight: " + suit.weight() + "kg Total Cost: "
                 + suit.cost() + "k TL");

        Suit suit2 = new Ora();
        suit2 = new Flamethrower(suit2);
        suit2 = new AutoRifle(suit2);
        suit2 = new RocketLauncher(suit2);
        suit2 = new Laser(suit2);

        System.out.println(suit2.getDescription() + " Total Weight: " + suit2.weight() + "kg Total Cost: "
                + suit2.cost() + "k TL");

        Suit suit3 = new Tor();
        suit3 = new Flamethrower(suit3);
        suit3 = new AutoRifle(suit3);
        suit3 = new AutoRifle(suit3);
        suit3 = new Laser(suit3);
        System.out.println(suit3.getDescription() + " Total Weight: " + suit3.weight() + "kg Total Cost: "
                + suit3.cost() + "k TL");
    }
}
