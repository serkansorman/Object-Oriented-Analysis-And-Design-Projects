package sample;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class GeneticAlgorithm {

    Chromosome[] chromosomes;

    final void optimizeFunction() {

        Random rn = new Random();

        generateInitialPopulation();
        computeFitness();

        Chromosome maxFitCh = getBestFit();
        double maxFit = maxFitCh.getFitness();

        int i = 0;
        while(true){

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

            updateGUI(maxFit);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            GUI.iteration++;


        }
    }

    abstract void selection();

    abstract void crossover();

    private void generateInitialPopulation(){

        chromosomes = new Chromosome[50];
        for (int i = 0; i < chromosomes.length; i++) {
            chromosomes[i] = new Chromosome();
        }
    }

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

    private void mutation() {

        Random rn = new Random();

        //Flip values at the mutation point
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

    private Chromosome getBestFit(){
        return Collections.max(Arrays.asList(chromosomes));
    }

    private void shuffleChromosomes(){
        List<Chromosome> intList = Arrays.asList(chromosomes);
        Collections.shuffle(intList);
        intList.toArray(chromosomes);
    }


    protected double binToDouble(String val){
        return Double.longBitsToDouble(new BigInteger(val, 2).longValue());
    }

    private boolean isValid(double x1, double x2,double func){
        return x1 >= 0 && x1 <= 5 && x2 >= 0 && x2 <= 5 &&
                x1 + x2 <= 5 &&  func >= 0;
    }

    /**
     * Updates line charts
     * @param maxFit maximum fitness
     */
    private void updateGUI(double maxFit){

        if(this instanceof AlgorithmVersion1){
            GUI.optValues.set(0,maxFit);
        }
        else if(this instanceof AlgorithmVersion2){
            GUI.optValues.set(1,maxFit);
        }
        else if(this instanceof AlgorithmVersion3){
            GUI.optValues.set(2,maxFit);
        }
    }
}
