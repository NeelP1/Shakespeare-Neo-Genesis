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
    private static Dictionary dictionary;
    
    /**
     * Saves the fittest individual in the current population and then performs
     * crossover and mutation on the rest.
     * 
     * 
     */
    public static Population evolvePopulation(Population pop, int strLength) {
        Population newPopulation = new Population(pop.size(), false, strLength);
        //TODO: do not allow tournament selection for second option as it
        //uses getFitness() which tests Individuals against the solution
        
        //Note: getFittest uses getFitness()
        
        //Keep our best individual at index 0
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
            Individual indiv1 = tournamentSelection(pop, strLength, true);
            Individual indiv2 = tournamentSelection(pop, strLength, true);
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
     * 
     * 
     * @param pop
     * @param strLength
     * @param isOptionOne
     * @return
     */
    public static Population evolvePopulation(Population pop, int strLength, boolean isOptionOne) {
        Population newPopulation = new Population(pop.size(), false, strLength);
        
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        
        // Keep our best individual at index 0
        if(isOptionOne){
        	if (elitism) {
                newPopulation.saveIndividual(0, pop.getFittest());
            }
            
            // Loop over the population size and create new individuals with
            // crossover. New individuals saved in index one onwards.
            for (int i = elitismOffset; i < pop.size(); i++) {
                Individual indiv1 = tournamentSelection(pop, strLength, isOptionOne);
                Individual indiv2 = tournamentSelection(pop, strLength, isOptionOne);
                Individual newIndiv = crossover(indiv1, indiv2, strLength);
                newPopulation.saveIndividual(i, newIndiv);
            }
            
            // Mutate population
            for (int i = elitismOffset; i < newPopulation.size(); i++) {
                mutate(newPopulation.getIndividual(i));
            }
        }else{
        	//1. find individual with highest score put in first position (Elitism)
            //2. tournament without getFittest() then cross over all individuals randomly
            //3. mutate
            
        	if (elitism) {
                newPopulation.saveIndividual(0, pop.getIndividualWithHighestScore());
            }
        	
            // Loop over the population size and create new individuals with
            // crossover. New individuals saved in index one onwards.
            for (int i = 0; i < pop.size(); i++) {
                Individual indiv1 = tournamentSelection(pop, strLength, isOptionOne);
                Individual indiv2 = tournamentSelection(pop, strLength, isOptionOne);
                Individual newIndiv = crossover(indiv1, indiv2, strLength);
                newIndiv.setTotalScore(Algorithm.calculateScore(newIndiv));
                newPopulation.saveIndividual(i, newIndiv);
            }
            
            // Mutate population
            for (int i = 0; i < newPopulation.size(); i++) {
                newPopulation.saveIndividual(i, mutate(newPopulation.getIndividual(i)));
            }
        }
        
        return newPopulation;
    }
    
    /**
     * Calculates score of individual and returns it as a double
     * 
     * @param indiv
     * @return score
     */
    public static double calculateScore(Individual indiv){
    	double temp = 0, score = 0;
    	String[] words = indiv.getArrayOfWords();
    	
    	for(int i = 0; i < indiv.getArrayOfWords().length; i++){
    		temp = dictionary.getSimilarityScore(words[i]);
			score += temp;
			temp = 0;
		}
    	
    	return score;
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
     * @return 
     */
    private static Individual mutate(Individual indiv) {
        Random rand = new Random();
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                byte gene = (byte) (rand.nextInt(122-32) + 32);
                indiv.setGene(i, gene);
            }
        }
        
        return indiv;
    }
    
    /**
     * Select individuals for crossover
     * 
     * @param pop
     * @param strLength
     * @return
     */
    private static Individual tournamentSelection(Population pop, int strLength, boolean isOptionOne) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false, strLength);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));//get a random individual from pop
        }

        if(isOptionOne){
            // Get the fittest
	        Individual fittest = tournament.getFittest();
	        return fittest;
        }else{
        	Individual highestScoringIndividual = tournament.getIndividualWithHighestScore();
        	//System.out.println("Test B: " + tournament.getIndividualWithHighestScore().getTotalScore());
        	return highestScoringIndividual;
        }
    }
    
    /**
     * Sets Dictionary object within Algorithm class
     * 
     * @param dict
     */
    public static void setDictionary(Dictionary dict){
    	dictionary = dict;
    }
    
}
