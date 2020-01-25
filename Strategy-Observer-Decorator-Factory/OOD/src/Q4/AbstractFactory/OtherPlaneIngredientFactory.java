package Q4.AbstractFactory;

public class OtherPlaneIngredientFactory implements PlaneIngredientFactory {
    /**
     *
     * @return GearedTurbofan as a EngineInjection type
     */
    @Override
    public EngineInjection createEngineInjection() {
        return new GearedTurbofan();
    }

    /**
     *
     * @return Leather as a Seating Cover
     */
    @Override
    public SeatingCover createSeatingCover() {
        return new Leather();
    }
}
