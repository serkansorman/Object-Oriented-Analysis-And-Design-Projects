import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Uses tournament selection and 1 point crossover
 */
public class AlgorithmVersion3 extends GeneticAlgorithm {
    /**
     * Implements tournament selection
     */
    @Override
    void selection() {

        ArrayList<Chromosome> selectedChromosomes = new ArrayList<>();
        ArrayList<Chromosome> tournamentList = new ArrayList<>();

        for (int i = 0; i < chromosomes.length; i++){
            //Pick up random 5 chromosomes to tournament
            Random rn = new Random();
            for (int j = 0; j < 5; j++){
                tournamentList.add(chromosomes[rn.nextInt(chromosomes.length)]);
            }
            //Get winner of tournament
            Chromosome winner = Collections.max(tournamentList);
            selectedChromosomes.add(new Chromosome(winner.getGenes(),winner.getFitness()));
            tournamentList.clear();
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
