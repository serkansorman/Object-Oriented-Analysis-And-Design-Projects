package Q4.FactoryMethod;

public abstract class Plane {

    String model;
    String purpose;
    String skeleton;
    String engine;
    int seats;

    public String getModel() {
        return model;
    }

    /**
     * Constructs skeleton using skeleton type
     */
    public void constructSkeleton(){
        System.out.println("Constructing Skeleton " + skeleton);
    }

    /**
     * Places engines using engine type
     */
    public void placeEngines(){
        System.out.println("Placing " + engine);

    }

    /**
     * Place seats using number of seats
     */
    public void placeSeats(){
        System.out.println("Placing Seats " + seats);

    }
}
