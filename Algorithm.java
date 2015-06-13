import java.util.Random;

/**
 * Most significant functions of the genetic algorithm are in this class:
 * crossover, mutate etc.
 * 
 * @author Neel Patel
 * 
 */
public class Algorithm {
    
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;//originally 0.015
    private static final int tournamentSize = 10;
    private static boolean elitism = true;
    
    /**
     * Saves the fittest individual in the current population and then performs
     * crossover and mutation on the rest.
     * 
     * 
     */
    public static Population evolvePopulation(Population pop, int strLength) {
        Population newPopulation = new Population(pop.size(), false, strLength);
        
        // Keep our best individual at index 0
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }
        
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        
        // Loop over the population size and create new individuals with
        // crossover. New individuals saved in index one onwards.
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop, strLength);
            Individual indiv2 = tournamentSelection(pop, strLength);
            Individual newIndiv = crossover(indiv1, indiv2, strLength);
            newPopulation.saveIndividual(i, newIndiv);
        }
        
        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }
    
    /**
     * Crossover individuals
     * 
     * @param indiv1
     * @param indiv2
     * @param strLength
     * @return
     */
    private static Individual crossover(Individual indiv1, Individual indiv2, int strLength) {
        Individual newSol = new Individual(strLength);
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }
    
    /**
     * Mutate an individual
     * 
     * @param indiv
     */
    private static void mutate(Individual indiv) {
        Random rand = new Random();
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                byte gene = (byte) (rand.nextInt(122-32) + 32);
                indiv.setGene(i, gene);
            }
        }
    }
    
    /**
     * Select individuals for crossover
     * 
     * @param pop
     * @param strLength
     * @return
     */
    private static Individual tournamentSelection(Population pop, int strLength) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false, strLength);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));//get a random individual from pop
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}
