import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class
 * 
 * 
 * 
 * @author Neel Patel
 */
public class ShakespeareGA extends InputOutput{
    

    /**
     * @param args[0] option 1 or 2 (solution or no solution)
     * @param args[1] filename of input file or length of string in second option
     * @param args[2] maximum number of generations
     */
    public static void main(String[] args) {
    	final int MAX_GENERATIONS = Integer.parseInt(args[2]);
        int generationCount = 0, populationSize = 50;
       
        //System.out.println(new File("file").getAbsoluteFile());
        //System.out.println(System.getProperty("user.dir"));
        
        //default target solution
        String sol = "My name is Maximus Decimus Meridius. "
                + "Commander of the Armies of the North, General "
                + "of the Felix Legions, loyal servant to the true "
                + "emperor, Marcus Aurelius. Father to a murdered "
                + "son, husband to a murdered wife. And I will have "
                + "my vengeance, in this life or the next.";
        
        //Option 1: user gives program a string for program to replicate
        //Option 2: program users dictionary to create new sentence
    	
        if(args.length > 0){	
	    	if(Integer.parseInt(args[0]) == 1){
	    		//Option 1
	    		//example arguments: 1 file.txt 200
	    		
	            System.out.println(args[1]);
	            try {
	            	sol = readFile(args[1]);
	            } catch (IOException ex) {
	            	Logger.getLogger(ShakespeareGA.class.getName()).log(Level.SEVERE, null, ex);
	            }
	
	            FitnessCalc.setSolution(sol);
	            
	            // Create an initial population and maybe give it fitness calculator capabilities
	            Population myPop = new Population(populationSize, true, sol.length());
	            
	            while (myPop.getFittest().getFitness() <  FitnessCalc.getMaxFitness() && generationCount < MAX_GENERATIONS) {
	                generationCount++;
	                System.out.println("Generation: " + generationCount + ", Fittest: " + myPop.getFittest().getFitness());
	                myPop = Algorithm.evolvePopulation(myPop, sol.length());
	            }
	            
	            System.out.println("\nGeneration: " + generationCount + ", Max Fitness: " + FitnessCalc.getMaxFitness() + ", Fittest: " + myPop.getFittest().getFitness());
	            System.out.println("Genes:");
	            System.out.println(myPop.getFittest());
	    		
	    	}else if(Integer.parseInt(args[0]) == 2){
	    		//Option 2
	    		//example arguments: 2 16 50

	    		//1: create random population
	    		//2: setup the dictionary/import words to dictionary
	    		//3: check with dictionary, give fitness rating to each individual
	    		
	    		int lengthOfString = Integer.parseInt(args[1]);
	    		Population myPop = new Population(populationSize, true, lengthOfString);
	    		Dictionary dictionary = new Dictionary();
	    		
	    		try {
					dictionary.fillWordListArr(dictionary.readFile("words.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    		
	    		dictionary.fillHashMap();
	    		
	    		for(int i = 0; i < MAX_GENERATIONS; i++){
	    			
	    		}
	    		
	    		
	    		
	    	}else{
	    		System.out.println("no option was chosen, oops");
	    	}
        }
        else{
            System.out.println("No arguments found");
        }
        
        //TODO: move different options into different classes to keep main class clean
    }
    
    
}
