import java.util.Random;

/**
 * Individual is a sequence of genes (letters)
 * 
 * @author Neel Patel
 */
public class Individual {
    private int defaultGeneLength;//length of sentence
    private byte[] genes;
    private int fitness = 0;
    private double totalScore = 0.0;
    

    public Individual(int length) {
        defaultGeneLength = length;
        genes = new byte[defaultGeneLength];
    }
    
    /**
     * Generate an entire individual (set of random characters)
     */
    public void generateIndividual(){
        Random rand = new Random();
        for (int i = 0; i < size(); i++) {
            byte gene = (byte) (rand.nextInt(122-32) + 32);
            genes[i] = gene;
        }
    }
    

    /**
     * Get a particular gene from an individual
     * 
     * @param index
     * @return
     */
    public char getGeneChar(int index) {
        char letter = (char) genes[index];
        return letter;
    }
    
 
 	/**
 	 * Get a particular gene as a number
 	 * 
 	 * @param index
 	 * @return
 	 */
    public byte getGene(int index) {
        return genes[index];
    }


    /**
     * Set a particular gene to a specified value
     * 
     * @param index
     * @param value
     */
    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }
    

    /**
     * Return length of the Individual
     * 
     * @return genes.length
     */
    public int size() {
        return genes.length;
    }
    
    /**
     * Get fitness of the individual - modified getter
     * 
     * @return fitness
     */
    public int getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }
    
    //TODO: Have error checking before for loop
    /**
     * Give the method a string which will be stored in the individual as an array of bytes
     * 
     * @param genes
     */
    public void setGenesWithString(String genesString){
    	byte[] genesAsByteArr = new byte[size()];
    	
    	for(int i = 0; i < size() && i < genesString.length(); i++){
    		genesAsByteArr[i] = (byte)genesString.charAt(i);
    	}
    	
    	setGenes(genesAsByteArr);
    }
    
    /**
     * Give the method a string array which will be stored in the individual as an array of bytes
     * 
     * @param wordArray
     */
    public void setGenesWithString(String [] wordArray){
    	byte[] genesAsByteArr;
    	String endResult = "";
    	
    	for(int i = 0; i < wordArray.length; i++){
    		endResult += wordArray[i] + " ";
    	}
    	
    	genesAsByteArr = new byte[endResult.length()];
    	
    	for(int i = 0; i < endResult.length(); i++){
    		genesAsByteArr[i] = (byte)endResult.charAt(i);
    	}
    	
    	setGenes(genesAsByteArr);
    }
    
    /**
     * Helps add space characters to a random string.
     * To be used with Option 2.
     */
    public void addSpacesToIndividual(){
    	//use while loop instead
    	Random rand = new Random();
    	
    	int index = 0, randInt = 0;
    	
    	while(index < size()){
        	randInt = (int) (rand.nextGaussian() * 1.2 + 5);
    		randInt = randInt >= 1 ? randInt : 5;
    		index += randInt;
    		
    		if(index < size() - 1 && genes[index-1] != (byte) 32 && genes[index + 1] != (byte) 32){
    			genes[index] = (byte) 32;
    		}
    	}
    }
    
    /**
     * 
     * @return wordsFromIndividual
     */
    public String[] getArrayOfWords(){
    	String[] wordsFromIndividual = toString().split(" ");
    	
    	return wordsFromIndividual;
    }
    
    /*Getters and Setters*/
    
    public int getDefaultGeneLength() {
		return defaultGeneLength;
	}

	public void setDefaultGeneLength(int defaultGeneLength) {
		this.defaultGeneLength = defaultGeneLength;
	}

	public byte[] getGenes() {
		return genes;
	}

	public void setGenes(byte[] genes) {
		this.genes = genes;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	@Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += (char) getGene(i);
        }
        return geneString;
    }
}
