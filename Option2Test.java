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
	
	@Ignore
	public void FitnessCalcTest(){
		String randomSolution = "";
				
		randomSolution = FitnessCalc.createRandomSolution(16);
		FitnessCalc.setSolution(randomSolution);
		
	}
	
	@Test
	public void IndividualMutateTest(){
		final int MAX_GENERATIONS = 1;
		int lengthOfString = 100, populationSize = 20;
		String randomSolution = "";
		
		Population myPop = new Population(populationSize, true, lengthOfString);
		Dictionary dictionary = new Dictionary();
		
		randomSolution = FitnessCalc.createRandomSolution(lengthOfString);
		FitnessCalc.setSolution(randomSolution);
		
		try {
			dictionary.fillWordListArr(dictionary.readFile("google-10000-english-usa.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dictionary.fillHashMap();
		Algorithm.setDictionary(dictionary);
		
		//splits individual into array of words
		String[] arrayOfWords;
		double topScore = 0, temp = 0;
		int bestIndividualIndex = 0;
		
		for(int generation = 0; generation < MAX_GENERATIONS; generation++){
			System.out.println("\nGeneration: " + (generation + 1));
			
			for(int i = 0; i < populationSize; i++){
				arrayOfWords = myPop.getIndividual(i).getArrayOfWords();
				
				System.out.println("\nIndividual: " + (i + 1) + ", " + myPop.getIndividual(i).toString());
				
				for(int j = 0; j < arrayOfWords.length; j++){
					System.out.println(arrayOfWords[j] + " -> " + dictionary.getSimilarityScore(arrayOfWords[j]));
					temp += dictionary.getSimilarityScore(arrayOfWords[j]);
				}
				
				if(temp > topScore){
					topScore = temp;
					bestIndividualIndex = i + 1;
				}
				
				myPop.setIndividualScore(i, temp);
				System.out.println("Total: " + myPop.getIndividualScore(i));
				
				temp = 0;
			}
			
			//System.out.println("\nBest Individual: " + bestIndividualIndex);
			//evolve best scoring individual
			myPop = Algorithm.evolvePopulation(myPop, lengthOfString, false);
		}
		//Note: the following uses getFittest()
		
		//TODO: get highest scoring individual and give it words from dictionary and match it with closest match
		Individual bestIndividual = myPop.getIndividualWithHighestScore();
		String[] bestIndividualWords = dictionary.returnLikeWordsArray(bestIndividual.getArrayOfWords());
		//TODO: with the words structure sentence into meaningful English sentence by sorting and validating
		System.out.println();
		for(int i = 0; i < bestIndividualWords.length; i++){
			System.out.print(bestIndividualWords[i] + " ");
		}
		
		System.out.println("\nHighest Scoring Individual: " + bestIndividual + ", Score: " + bestIndividual.getTotalScore());
	}
	

}
