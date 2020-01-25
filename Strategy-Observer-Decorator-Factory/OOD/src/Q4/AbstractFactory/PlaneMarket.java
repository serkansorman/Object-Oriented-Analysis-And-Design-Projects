package Q4.AbstractFactory;

import Q4.FactoryMethod.Models;

public abstract class PlaneMarket {

    /**
     * Creates plane according to Market
     * @param model of plane to be created
     * @return Plane
     * @throws Exception
     */
    abstract Plane createPlane(Models model) throws Exception;

    public Plane producePlane(Models model){

        Plane plane = null;

        try {

            plane = createPlane(model);
            plane.constructSkeleton();;
            plane.placeEngines();
            plane.placeSeats();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plane;
    }
}
