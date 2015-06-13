/**
 * Sets solution, performs fitness calculations and reduces
 * the amount of code needed in main class (shakespeareGA.java).
 * 
 * @author Neel Patel
 */
public class FitnessCalc {
    private static byte[] solution;
    
    /*
    public FitnessCalc(){
    	solution = new byte[64];
    }
    */
    /**
     * Set a candidate solution as a byte array
     * 
     * @param newSolution
     */
    public static void setSolution(byte[] newSolution) {
        solution = newSolution;
    }
    
    /**
     * 
     * 
     * @param newSolution
     */
    public static void setSolution(String newSolution) {
        solution = new byte[newSolution.length()];
        // Loop through each character of our string and save it in our byte
        // array
        for (int i = 0; i < newSolution.length(); i++) {
            char character = newSolution.charAt(i);
            
            solution[i] = (byte) character;
        }
    }
    
    /**
     * Calculate individuals fitness by comparing it to our candidate solution
     * 
     * @param  individual
     * @return fitness
     */
    public static int getFitness(Individual individual) {
        int fitness = 0;
        // Loop through our individuals genes and compare them to our cadidates
        for (int i = 0; i < individual.size() && i < solution.length; i++) {
            if (individual.getGene(i) == solution[i]) {
                fitness++;
            }
        }
        
        return fitness;
    }
    
    /**
     * Get optimum fitness
     * 
     * @return maxFitness
     */
    public static int getMaxFitness() {
        int maxFitness = solution.length;
        
        return maxFitness;
    }
}
