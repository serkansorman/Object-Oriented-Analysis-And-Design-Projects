package Q4.AbstractFactory;

import Q4.FactoryMethod.Models;

public class Main {

    public static void main(String... args){

        PlaneMarket domesticPlaneMarket = new DomesticPlaneMarket();
        PlaneMarket  eurasiaPlaneMarket = new EurasiaPlaneMarket();

        Plane plane = domesticPlaneMarket.producePlane(Models.TPX100);
        System.out.println("Plane " + plane.getModel() + " is produced\n");

        plane = domesticPlaneMarket.producePlane(Models.TPX200);
        System.out.println("Plane " + plane.getModel() + " is produced\n");

        plane = domesticPlaneMarket.producePlane(Models.TPX300);
        System.out.println("Plane " + plane.getModel() + " is produced\n");


        plane = eurasiaPlaneMarket.producePlane(Models.TPX100);
        System.out.println("Plane " + plane.getModel() + " is produced\n");

        plane = eurasiaPlaneMarket.producePlane(Models.TPX200);
        System.out.println("Plane " + plane.getModel() + " is produced\n");

        plane = eurasiaPlaneMarket.producePlane(Models.TPX300);
        System.out.println("Plane " + plane.getModel() + " is produced\n");
    }
}
