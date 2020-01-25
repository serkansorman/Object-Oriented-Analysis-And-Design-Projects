public class Main {
    public static void main(String... args){

        System.out.println("############ Iteration: 100, Population size: 100 #############");

        System.out.println("############ Version 1 #############");
        AlgorithmVersion1 algorithmVersion1 = new AlgorithmVersion1();
        algorithmVersion1.optimizeFunction();

        System.out.println("############ Version 2 #############");
        AlgorithmVersion2 algorithmVersion2 = new AlgorithmVersion2();
        algorithmVersion2.optimizeFunction();

        System.out.println("############ Version 3 #############");
        AlgorithmVersion3 algorithmVersion3 = new AlgorithmVersion3();
        algorithmVersion3.optimizeFunction();

    }
}
