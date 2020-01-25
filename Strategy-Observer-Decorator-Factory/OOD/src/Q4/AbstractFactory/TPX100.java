package Q4.AbstractFactory;



public class TPX100 extends Plane{
    /**
     * Creates EngineInjection and SeatingCover
     */
    PlaneIngredientFactory planeIngredientFactory;

    public TPX100(PlaneIngredientFactory planeIngredientFactory) {
        purpose = "Domestic flights";
        skeleton = "Aluminum alloy";
        engine = "Single jet engine";
        seats = 50;

        this.planeIngredientFactory = planeIngredientFactory;
        engineInjection = planeIngredientFactory.createEngineInjection();
        seatingCover = planeIngredientFactory.createSeatingCover();

    }
}
