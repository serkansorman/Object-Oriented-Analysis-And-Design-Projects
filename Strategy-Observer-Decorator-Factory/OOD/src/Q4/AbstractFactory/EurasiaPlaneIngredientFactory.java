package Q4.AbstractFactory;

public class EurasiaPlaneIngredientFactory implements PlaneIngredientFactory {

    /**
     *
     * @return Turbofan as a EngineInjection type
     */
    @Override
    public EngineInjection createEngineInjection() {
        return new Turbofan();
    }

    /**
     *
     * @return Linen as a Seating Cover
     */
    @Override
    public SeatingCover createSeatingCover() {
        return new Linen();
    }
}
