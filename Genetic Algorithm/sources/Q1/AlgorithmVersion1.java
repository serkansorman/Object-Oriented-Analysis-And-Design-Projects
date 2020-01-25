import java.util.ArrayList;
import java.util.Random;

/**
 * Uses roulette wheel selection and 1 point crossover
 */
public class AlgorithmVersion1 extends GeneticAlgorithm{

    /**
     * Implements roulette wheel selection
     */
    @Override
    void selection() {

        ArrayList<Chromosome> selectedChromosomes = new ArrayList<>();
        int sum = 0;
        for (Chromosome value : chromosomes) {
            sum += value.getFitness();
        }

        if(sum == 0)
            sum += 1;

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
     * Implements 1 point crossover
     */
    @Override
    void crossover() {

        Random rn = new Random();

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(chromosomes[0].getGenesSize());

        //Swap values among parents
        for(int i = 0; i<chromosomes.length - 1; ++i){
            char[] firstArr = chromosomes[i].getGenes().toCharArray();
            char[] secondArr = chromosomes[i+1].getGenes().toCharArray();

            for (int j = 0; j < crossOverPoint; j++) {
                char temp = firstArr[j];
                firstArr[j] = secondArr[j];
                secondArr[j] = temp;
            }

            chromosomes[i].setGenes(String.valueOf(firstArr));
            chromosomes[i+1].setGenes(String.valueOf(secondArr));

            ++i;
        }
    }
}
