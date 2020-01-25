package Q4.AbstractFactory;

public interface PlaneIngredientFactory {
    /**
     *
     * @return EngineInjection according to Market
     */
    EngineInjection createEngineInjection();

    /**
     *
     * @return SeatingCover according to Market
     */
    SeatingCover createSeatingCover();
}
