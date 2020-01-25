package Q4.AbstractFactory;


import Q4.FactoryMethod.Models;

public class DomesticPlaneMarket extends PlaneMarket {

    /**
     * Creates plane according to DomesticPlaneIngredientFactory and Model
     * @param model of plane to be created
     * @return Plane
     * @throws Exception
     */
    @Override
    Plane createPlane(Models model) throws Exception {

        Plane plane;
        PlaneIngredientFactory planeIngredientFactory = new DomesticPlaneIngredientFactory();

        if(model.equals(Models.TPX100)){
            plane = new TPX100(planeIngredientFactory);
            plane.setModel("Domestic TPX 100");
        }
        else if(model.equals(Models.TPX200)){
            plane = new TPX200(planeIngredientFactory);
            plane.setModel("Domestic TPX 200");
        }
        else if(model.equals(Models.TPX300)){
            plane = new TPX300(planeIngredientFactory);
            plane.setModel("Domestic TPX 300");
        }
        else
            throw new Exception("Undefined Model");

        return plane;
    }
}
