package Q4.AbstractFactory;


public class TPX300 extends Plane{

    /**
     * Creates EngineInjection and SeatingCover
     */
    PlaneIngredientFactory planeIngredientFactory;

    public TPX300(PlaneIngredientFactory planeIngredientFactory) {
        purpose = "Transatlantic flights";
        skeleton = "Titanium alloy";
        engine = "Quadro jet engines";
        seats = 250;

        this.planeIngredientFactory = planeIngredientFactory;
        engineInjection = planeIngredientFactory.createEngineInjection();
        seatingCover = planeIngredientFactory.createSeatingCover();

    }

}
