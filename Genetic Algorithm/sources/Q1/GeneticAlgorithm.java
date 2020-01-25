import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Super class for Three version of GA
 */
public abstract class GeneticAlgorithm {

    /**
     * Population
     */
    Chromosome[] chromosomes;

    /**
     * Runs genetic algorithms step by step
     */
    public final void optimizeFunction() {

        int i = 0;
        Random rn = new Random();

        generateInitialPopulation();
        computeFitness();

        Chromosome maxFitCh = getBestFit();
        double maxFit = maxFitCh.getFitness();

        while(i<100){

            selection();
            shuffleChromosomes();
            crossover();

            if (rn.nextInt()%7 < 5)
                mutation();

            computeFitness();

            if(getBestFit().getFitness() > maxFit){
                maxFitCh = getBestFit();
                maxFit = maxFitCh.getFitness();
            }

            ++i;
        }

        System.out.println("Optimal Value: " + String.format("%.4f,",maxFit) + " x1: " + String.format("%.4f",binToDouble(maxFitCh.getGenesX1())) +
                " x2: " + String.format("%.4f",binToDouble(maxFitCh.getGenesX2())));
    }

    abstract void selection();

    abstract void crossover();

    /**
     * Inits population
     */
    private void generateInitialPopulation(){

        chromosomes = new Chromosome[100];
        for (int i = 0; i < chromosomes.length; i++)
            chromosomes[i] = new Chromosome();

    }


    /**
     * Computes fitness of each chromosome, according to function result
     */
    private void computeFitness() {

        int worstFit = 0;

        for (Chromosome chromosome : chromosomes) {
            double x1 = binToDouble(chromosome.getGenesX1());
            double x2 = binToDouble(chromosome.getGenesX2());
            double func =  (20*x1*x2) + (16*x2) - (2*Math.pow(x1,2)) - Math.pow(x2,2) - Math.pow(x1+x2,2);

            if (isValid(x1,x2,func))
                chromosome.setFitness(func);
            else
                chromosome.setFitness(worstFit);

        }
    }

    /**
     * Selects a random mutation point on genes and reverts it
     */
    private void mutation() {

        Random rn = new Random();

        for(int i = 0; i<chromosomes.length - 1; ++i){
            //Select a random mutation point
            int mutationPoint = rn.nextInt(chromosomes[i].getGenesSize());
            char[] modifiedGenes = chromosomes[i].getGenes().toCharArray();

            if (modifiedGenes[mutationPoint] == '0') {
                modifiedGenes[mutationPoint] = '1';
            } else {
                modifiedGenes[mutationPoint] = '0';
            }

            chromosomes[i].setGenes(String.valueOf(modifiedGenes));
        }
    }

    /**
     * Get chromosome has maximum fitness
     * @return chromosome has maximum fitness
     */
    private Chromosome getBestFit(){
        return Collections.max(Arrays.asList(chromosomes));
    }

    /**
     * Shuffles population to prevents the succession of the same elements
     */
    private void shuffleChromosomes(){
        List<Chromosome> intList = Arrays.asList(chromosomes);
        Collections.shuffle(intList);
        intList.toArray(chromosomes);
    }

    /**
     * Convert given binary string to double
     * @param val 63 bit binary string
     * @return double number
     */
    private double binToDouble(String val){
        return Double.longBitsToDouble(new BigInteger(val, 2).longValue());
    }

    /**
     * Checks x1 and x2 meet given constraints
     * @param x1
     * @param x2
     * @param func result of function
     * @return true if meets the constraints, otherwise return false
     */
    private boolean isValid(double x1, double x2,double func){
        return x1 >= 0 && x1 <= 5 && x2 >= 0 && x2 <= 5 &&
                x1 + x2 <= 5 &&  func >= 0;
    }
}
