import java.util.Arrays;
import java.util.Random;

/**
 * Expresses x1 and x2
 */
public class Chromosome implements Comparable{

    private double fitness;
    private String genes;
    private int genesSize = 126;


    /**
     * Generate random x1 and x2 value under given constraints
     */
    public Chromosome() {

        genes = "";

        Random rn = new Random();
        double x1;
        double x2;

        do{
            x1 = 0 + (rn.nextDouble() * (5));
            x2 = 0 + (rn.nextDouble() * (5));
        }
        while(x1 + x2 > 5);

        genes = doubleToBin(x1) + doubleToBin(x2);

        fitness = 0;
    }

    /**
     * Copy constructor
     * @param genes
     * @param fitness
     */
    Chromosome(String genes,double fitness){
        this.genes = String.valueOf(genes);
        this.fitness = fitness;
    }

    /**
     * Converts given double to 63 bit binary string
     * @param val double number that will be converted
     * @return 63 bit binary string
     */
    private String doubleToBin(double val){
        return String.format("%63s", Long.toBinaryString(Double.doubleToRawLongBits(val))).replace(' ', '0');
    }


    public double getFitness() {
        return fitness;
    }

    public void setGenes(String genes) {
        this.genes = genes;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public String getGenes() {
        return genes;
    }

    public int getGenesSize() {
        return genesSize;
    }

    /**
     * Get X1 as 63 bit binary string from 126 bits genes
     * @return 63 bit binary string
     */
    public String getGenesX1(){
        return genes.substring(0,genes.length() / 2);
    }

    /**
     * Get X2 as 63 bit binary string from 126 bits genes
     * @return 63 bit binary string
     */
    public String getGenesX2(){
        return genes.substring(genes.length() / 2);
    }

    /**
     * Compare given chromosome with current chromosome according to fitness value
     * @param o chromosome that will be compared
     * @return -1 if current fitness is lower, 1 if current fitness is greater, 0 if fitness are equal
     */
    @Override
    public int compareTo(Object o) {
        Chromosome chromosome = (Chromosome) o;
        return Double.compare(fitness,chromosome.getFitness());
    }
}
