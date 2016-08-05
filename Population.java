import java.util.Random;

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
    
    /**
     * Call before calling getIndividualWithHighestScore
     */
    public void giveAllIndividualsScores(){
    	double score = 0.0;
    	for(int i = 0; i < individuals.length; i++){
    		score = Algorithm.calculateScore(individuals[i]);
    		individuals[i].setTotalScore(score);
    	}
    }
    
    public Individual getIndividualWithHighestScore() {
        Individual individualWithHighestScore = individuals[0];
        System.out.println("Original with high score: " + individualWithHighestScore.getTotalScore());
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (individualWithHighestScore.getTotalScore() <= getIndividual(i).getTotalScore()) {
            	System.out.println("individual: " + i + ", score: " + getIndividual(i).getTotalScore());
            	individualWithHighestScore = getIndividual(i);
            }
        }
        
        return individualWithHighestScore;
    }
    
    /**
     * 
     */
    public void correctErrorsInIndividuals(){
    	for(int i = 0; i < individuals.length; i++){
    		individuals[i] = convertIllegalLetters(checkForConsecutiveSpaces(individuals[i]));
    	}
    }
    
    /**
     * 
     * @param indiv
     * @return
     */
    private Individual checkForConsecutiveSpaces(Individual indiv){
    	byte[] genes = indiv.getGenes();
    	//byte previousByte = genes[0];
    	Random rand = new Random();
    	byte newGene = ' ';
    	Individual newIndividual = new Individual(indiv.getDefaultGeneLength());
    	
    	//ternary operator (?) to genes[i] assignment
    	for(int i = 1; i < genes.length; i++){
    		if(genes[i] == (byte) 32 && genes[i - 1] == (byte) 32){
    			newGene = (byte) (rand.nextInt(122-32) + 32);
    			genes[i] = (byte) ((newGene == (byte) 32) ? 'e' : newGene);
    		}
    	}
    	
    	newIndividual.setGenes(genes);
    	
    	return newIndividual;
    }
    
    /**
     * 
     * @param indiv
     * @return
     */
    private Individual convertIllegalLetters(Individual indiv){
    	byte[] genes = indiv.getGenes();
    	Random randomNum = new Random();
		//System.out.println(randomNum.nextDouble());

    	Individual newIndividual = new Individual(indiv.getDefaultGeneLength());
    	
    	//ternary operator (?) to genes[i] assignment
    	for(int i = 1; i < genes.length - 1; i++){
    		if(genes[i - 1] == (byte) 32 && genes[i + 1] == (byte) 32) {
    			genes[i] = (byte) (randomNum.nextDouble() < 0.5 ? 'a' : 'I');
    		}
    	}
    	
    	newIndividual.setGenes(genes);
    	
    	return newIndividual;
    }
    
    @Override
    public String toString(){
    	String populationToBePrinted = "";
    	for(int i = 0; i < individuals.length; i++){
    		populationToBePrinted += individuals[i].toString() + "\n";
    	}
    	
    	return populationToBePrinted;
    }
}
