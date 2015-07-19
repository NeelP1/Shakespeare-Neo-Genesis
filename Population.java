/**
 * A set of individuals 
 * 
 * @author Neel Patel
 */
public class Population {
    
    private Individual[] individuals;
    
    /**
     * 
     * Constructor
     */
    public Population(int populationSize, boolean initialise, int stringLength) {
        individuals = new Individual[populationSize];
        // Initialise population
        if(initialise) {
            // Loop and create individuals
            for (int i = 0; i < size(); i++) {
                Individual newIndividual = new Individual(stringLength);
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }
        }
    }
    
    /**
     * Get a individual at a certain location
     */
    public Individual getIndividual(int index) {
        return individuals[index];
    }
    
    public String getIndividualAsString(int index){
    	Individual individual = individuals[index];
    	
    	String individualAsString = individual.toString();
    	
    	return individualAsString;
    }
    
    /**
     * Get fittest individual
     */
    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        
        return fittest;
    }
    
    /**
     * return length of the population
     */
    public int size() {
        return individuals.length;
    }
    
    /**
     * Save individual
     */
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
    
    
}
