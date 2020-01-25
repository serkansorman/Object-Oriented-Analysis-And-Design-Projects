package Q4.FactoryMethod;

/**
 * Plane market that holds Plane Factory
 */
public class PlaneMarket {
    PlaneFactory planeFactory;

    public PlaneMarket(PlaneFactory planeFactory) {
        this.planeFactory = planeFactory;
    }

    /**
     * Produce plane using create plane then calls construct methods
     * @param model of plane to be created
     * @return plane to be produced
     */
    public Plane producePlane(Models model){
        Plane plane = null;

        try {

            plane = planeFactory.createPlane(model);
            plane.constructSkeleton();;
            plane.placeEngines();
            plane.placeSeats();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plane;
    }
}
