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
                newIndividual.addSpacesToIndividual();
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
     * 
     * @param index	index to insert individual
     * 		  indiv Individual to save
     */
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
    
    public double getIndividualScore(int index){
    	return individuals[index].getTotalScore();
    }
    
    public void setIndividualScore(int index, double score){
    	Individual tempIndividual = individuals[index];
    	
    	tempIndividual.setTotalScore(score);
    	
    	individuals[index] = tempIndividual;
    }
    
    public Individual getIndividualWithHighestScore() {
        Individual individualWithHighestScore = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (individualWithHighestScore.getTotalScore() <= getIndividual(i).getTotalScore()) {
            	individualWithHighestScore = getIndividual(i);
            }
        }
        
        return individualWithHighestScore;
    }
}
