package Q4.FactoryMethod;

public class PlaneFactory {

    /**
     * Create a Plane object
     * @param model of plane to be created
     * @return plane created according to model
     * @throws Exception
     */
    public Plane createPlane(Models model) throws Exception {
        Plane plane = null;

        if(model.equals(Models.TPX100))
            plane = new TPX100();
        else if(model.equals(Models.TPX200))
            plane = new TPX200();
        else if(model.equals(Models.TPX300))
            plane = new TPX300();
        else
            throw new Exception("Undefined Model");

        return plane;

    }
}
