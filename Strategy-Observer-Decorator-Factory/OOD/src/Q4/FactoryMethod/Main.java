package Q4.FactoryMethod;

public class Main {

    public static void main(String... args) {

        PlaneFactory planeFactory = new PlaneFactory();
        PlaneMarket planeMarket = new PlaneMarket(planeFactory);

        Plane plane = planeMarket.producePlane(Models.TPX100);
        System.out.println(plane.getModel()+ " is produced\n");

        plane = planeMarket.producePlane(Models.TPX200);
        System.out.println(plane.getModel()+ " is produced\n");

        plane = planeMarket.producePlane(Models.TPX300);
        System.out.println(plane.getModel()+ " is produced");

    }
}
