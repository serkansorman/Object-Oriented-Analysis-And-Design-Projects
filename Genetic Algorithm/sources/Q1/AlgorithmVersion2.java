import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Uses rank selection and 2 point crossover.
 */
public class AlgorithmVersion2 extends GeneticAlgorithm {

    /**
     * Implements rank selection
     */
    @Override
    void selection() {
        ArrayList<Chromosome> selectedChromosomes = new ArrayList<>();

        //Set fitness to 0,1,2... according to the their fitness values
        Arrays.sort(chromosomes);
        for (int i = 0; i < chromosomes.length; i++) {
            chromosomes[i].setFitness(i);
        }

        int sum = 0;
        for (Chromosome value : chromosomes) {
            sum += value.getFitness();
        }

        for (int i = 0; i < chromosomes.length; i++){
            int partialSum = 0;
            int rand = new Random().nextInt(sum);
            for (Chromosome chromosome : chromosomes) {
                partialSum += chromosome.getFitness();
                if (partialSum >= rand){
                    selectedChromosomes.add(new Chromosome(chromosome.getGenes(),chromosome.getFitness()));
                    break;
                }
            }
        }

        //Replace with selected chromosomes
        for (int i = 0; i < selectedChromosomes.size(); i++) {
            chromosomes[i] = selectedChromosomes.get(i);
        }

    }

    /**
     * Implements 2 point crossover
     */
    @Override
    void crossover() {

        Random rn = new Random();

        //Select a random crossover points
        int crossOverPoint1 = rn.nextInt(chromosomes[0].getGenesSize());
        int crossOverPoint2 = rn.nextInt(chromosomes[0].getGenesSize());

        // Ensure crosspoints are different...
        if (crossOverPoint1 == crossOverPoint2){
            if(crossOverPoint1 == 0){
                ++crossOverPoint2;
            } else {
                --crossOverPoint1;
            }
        }

        //if crossOverPoint2 is lower than  crossOverPoint1 swap them
        if (crossOverPoint2 < crossOverPoint1) {
            int temp = crossOverPoint1;
            crossOverPoint1 = crossOverPoint2;
            crossOverPoint2 = temp;
        }

        //Swap values among parents
        for(int i = 0; i<chromosomes.length - 1; ++i){
            char[] firstArr = chromosomes[i].getGenes().toCharArray();
            char[] secondArr = chromosomes[i+1].getGenes().toCharArray();

            for (int j = 0; j < chromosomes[i].getGenesSize(); j++) {
                if(j > crossOverPoint1 && j < crossOverPoint2){
                    char temp = firstArr[j];
                    firstArr[j] = secondArr[j];
                    secondArr[j] = temp;
                }
            }

            chromosomes[i].setGenes(String.valueOf(firstArr));
            chromosomes[i+1].setGenes(String.valueOf(secondArr));

            ++i;
        }
    }
}
