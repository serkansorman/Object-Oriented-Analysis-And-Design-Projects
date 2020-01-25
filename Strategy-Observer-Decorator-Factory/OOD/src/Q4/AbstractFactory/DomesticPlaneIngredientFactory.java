package Q4.AbstractFactory;

public class DomesticPlaneIngredientFactory implements PlaneIngredientFactory {

    /**
     *
     * @return Turbojet as a EngineInjection type
     */
    @Override
    public EngineInjection createEngineInjection() {
        return new Turbojet();
    }

    /**
     *
     * @return Velvet as a Seating Cover
     */
    @Override
    public SeatingCover createSeatingCover() {
        return new Velvet();
    }
}
