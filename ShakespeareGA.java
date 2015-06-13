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
     * @param args[0] filename of input file
     * @param args[1] maximum number of generations
     */
    public static void main(String[] args) {
        final int MAX_GENERATIONS = Integer.parseInt(args[1]);
        int generationCount = 0;
        //FitnessCalc fitCalculator = new FitnessCalc();
       
        //System.out.println(new File("file").getAbsoluteFile());
        //System.out.println(System.getProperty("user.dir"));
        
        //default target solution
        String sol = "My name is Maximus Decimus Meridius. "
                + "Commander of the Armies of the North, General "
                + "of the Felix Legions, loyal servant to the true "
                + "emperor, Marcus Aurelius. Father to a murdered "
                + "son, husband to a murdered wife. And I will have "
                + "my vengeance, in this life or the next.";
        
        //TODO: Add another option, user can select to have no solution
        //Option 1: user gives program a string for program to replicate
        //Option 2: program users dictionary to create new sentence
        
        if(args.length > 0){
            System.out.println(args[0]);
            try {
                sol = readFile(args[0]);
            } catch (IOException ex) {
                Logger.getLogger(ShakespeareGA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("No arguments found");
        }

        //fitCalculator.setSolution(sol);
        FitnessCalc.setSolution(sol);
        
        // Create an initial population and maybe give it fitness calculator capabilities
        Population myPop = new Population(50, true, sol.length());
        
        while (myPop.getFittest().getFitness() <  FitnessCalc.getMaxFitness() && generationCount < MAX_GENERATIONS) {
            generationCount++;
            System.out.println("Generation: " + generationCount + ", Fittest: " + myPop.getFittest().getFitness());
            myPop = Algorithm.evolvePopulation(myPop, sol.length());
        }
        
        System.out.println("\nGeneration: " + generationCount + ", Max Fitness: " + FitnessCalc.getMaxFitness() + ", Fittest: " + myPop.getFittest().getFitness());
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());
        
    }
    
    
}
