package Q4.AbstractFactory;



public class TPX200 extends Plane{

    /**
     * Creates EngineInjection and SeatingCover
     */
    PlaneIngredientFactory planeIngredientFactory;

    public TPX200(PlaneIngredientFactory planeIngredientFactory) {
        purpose = "Domestic and short international flights";
        skeleton = "Nickel alloy";
        engine = "Twin jet engines";
        seats = 100;

        this.planeIngredientFactory = planeIngredientFactory;
        engineInjection = planeIngredientFactory.createEngineInjection();
        seatingCover = planeIngredientFactory.createSeatingCover();

    }

}
