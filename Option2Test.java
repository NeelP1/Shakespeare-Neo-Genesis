import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;


public class Option2Test {

	@Ignore
	public void singleIndividualtest() {
		final int MAX_GENERATIONS = 1;
		int lengthOfString = 16, populationSize = 1;
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
		
		//splits individual into array of words
		String[] arrayOfWords = myPop.getIndividual(0).getArrayOfWords();
		//System.out.println(dictionary.searchLikeWords(arrayOfWords[0]));
		
		//Print individuals and like words
		for(int i = 0; i < arrayOfWords.length; i++){
			System.out.println(arrayOfWords[i] + " -> (most similar word) -> " + dictionary.searchLikeWords(arrayOfWords[i]));
		}
		
		System.out.println();
	}
	
	@Ignore
	public void singleIndividualScoreTest(){
		final int MAX_GENERATIONS = 1;
		int lengthOfString = 16, populationSize = 1;
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
		
		//splits individual into array of words
		String[] arrayOfWords = myPop.getIndividual(0).getArrayOfWords();
		//System.out.println(dictionary.searchLikeWords(arrayOfWords[0]));
		
		//Does individual exist in the dictionary?
		for(int i = 0; i < arrayOfWords.length; i++){
			System.out.println(arrayOfWords[i] + " -> (most similar word) -> " + dictionary.searchList(arrayOfWords[i]));
		}
		
		System.out.println();
	}
	
	@Ignore
	public void populationTest(){
		final int MAX_GENERATIONS = 1;
		int lengthOfString = 16, populationSize = 5;
		Population myPop = new Population(populationSize, true, lengthOfString);
		Dictionary dictionary = new Dictionary();
		
		System.out.println("\nPopulation Test");
		
		try {
			dictionary.fillWordListArr(dictionary.readFile("words.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dictionary.fillHashMap();
		
		//splits individual into array of words
		String[] arrayOfWords;
		
		for(int i = 0; i < populationSize; i++){
			arrayOfWords = myPop.getIndividual(i).getArrayOfWords();
			
			System.out.println("\nIndividual: " + (i + 1));
			
			for(int j = 0; j < arrayOfWords.length; j++){
				System.out.println(arrayOfWords[j] + " -> (most similar word) -> " + dictionary.searchLikeWords(arrayOfWords[j]));
			}
			
		}
		
		System.out.println();
	}
	
	@Ignore
	public void populationScoreTest(){
		int lengthOfString = 16, populationSize = 5;
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
		
		//splits individual into array of words
		String[] arrayOfWords;
		double topScore = 0, temp = 0;
		int bestIndividualIndex = 0;
		
		for(int i = 0; i < populationSize; i++){
			arrayOfWords = myPop.getIndividual(i).getArrayOfWords();
			
			System.out.println("\nIndividual: " + (i + 1));
			
			for(int j = 0; j < arrayOfWords.length; j++){
				System.out.println(dictionary.getSimilarityScore(arrayOfWords[j]));
				temp += dictionary.getSimilarityScore(arrayOfWords[j]);
			}
			
			System.out.println("Total: " + temp);
			
			if(temp > topScore){
				topScore = temp;
				bestIndividualIndex = i + 1;
			}
			
			temp = 0;
		}
		
		System.out.println("\nBest Individual: " + bestIndividualIndex);
	}
	
	@Test
	public void FitnessCalcTest(){
		String randomSolution = "";
				
		randomSolution = FitnessCalc.createRandomSolution(16);
		FitnessCalc.setSolution(randomSolution);
		
	}
	
	@Test
	public void IndividualMutateTest(){
		final int MAX_GENERATIONS = 5;
		int lengthOfString = 32, populationSize = 5;
		String randomSolution = "";
		
		Population myPop = new Population(populationSize, true, lengthOfString);
		Dictionary dictionary = new Dictionary();
		
		randomSolution = FitnessCalc.createRandomSolution(lengthOfString);
		FitnessCalc.setSolution(randomSolution);
		
		try {
			dictionary.fillWordListArr(dictionary.readFile("words.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dictionary.fillHashMap();
		
		//splits individual into array of words
		String[] arrayOfWords;
		double topScore = 0, temp = 0;
		int bestIndividualIndex = 0;
		
		for(int generation = 0; generation < MAX_GENERATIONS; generation++){
			System.out.println("\nGeneration: " + (generation + 1));
			
			for(int i = 0; i < populationSize; i++){
				arrayOfWords = myPop.getIndividual(i).getArrayOfWords();
				
				System.out.println("\nIndividual: " + (i + 1));
				
				for(int j = 0; j < arrayOfWords.length; j++){
					System.out.println(arrayOfWords[j] + " -> " + dictionary.getSimilarityScore(arrayOfWords[j]));
					temp += dictionary.getSimilarityScore(arrayOfWords[j]);
				}
				
				System.out.println("Total: " + temp);
				
				if(temp > topScore){
					topScore = temp;
					bestIndividualIndex = i + 1;
				}
				
				temp = 0;
			}
			
			System.out.println("\nBest Individual: " + bestIndividualIndex);
			myPop = Algorithm.evolvePopulation(myPop, lengthOfString);
			//TODO: evolve best individual
			
		}
		
		System.out.println("\nMax Fitness: " + FitnessCalc.getMaxFitness() + ", Fittest: " + myPop.getFittest().getFitness());
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());
	}
	

}
