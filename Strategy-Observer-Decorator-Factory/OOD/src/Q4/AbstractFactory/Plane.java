package Q4.AbstractFactory;

public abstract class Plane {

    String model;
    String purpose;
    String skeleton;
    String engine;
    int seats;

    EngineInjection engineInjection;
    SeatingCover seatingCover;

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
    public void placeEngines() {
        System.out.println("Placing " + engine + " Engine Injection Type: "+ engineInjection);
    }

    /**
     * Place seats using number of seats
     */
    public void placeSeats() {
        System.out.println("Placing Seats " + seats + " Seating Cover: "+ seatingCover);
    }

    public void setModel(String model) {
        this.model = model;
    }
}
