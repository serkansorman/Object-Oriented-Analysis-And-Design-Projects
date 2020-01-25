package sample;

import java.util.Random;

public class Chromosome implements Comparable{

    private double fitness;
    private String genes;
    private int genesSize = 126;

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

    Chromosome(String genes, double fitness){
        this.genes = String.valueOf(genes);
        this.fitness = fitness;
    }

    private String doubleToBin(double val){
        return String.format("%63s", Long.toBinaryString(Double.doubleToRawLongBits(val))).replace(' ', '0');
    }

   //public void setGene(int )
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

    public String getGenesX1(){
        return genes.substring(0,genes.length() / 2);
    }

    public String getGenesX2(){
        return genes.substring(genes.length() / 2);
    }

    @Override
    public int compareTo(Object o) {
        Chromosome chromosome = (Chromosome) o;
        return Double.compare(fitness,chromosome.getFitness());
    }
}
