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
		final int MAX_GENERATIONS = 1;//TODO: should be parsed as an argument
		int lengthOfString = 10, populationSize = 5;//TODO: should be parsed as an argument
		String randomSolution = "";
		
		Population myPop = new Population(populationSize, true, lengthOfString);
		Dictionary dictionary = new Dictionary();
		
		randomSolution = FitnessCalc.createRandomSolution(lengthOfString);
		FitnessCalc.setSolution(randomSolution);
		
		try {
			dictionary.fillWordListArr(dictionary.readFile("1-1000.txt"));
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
				}
				
				myPop.setIndividualScore(i, temp);
				System.out.println("Total: " + myPop.getIndividualScore(i));
				
				temp = 0;
			}
			
			//evolve best scoring individual
			myPop = Algorithm.evolvePopulation(myPop, lengthOfString, false);
			myPop.correctErrorsInIndividuals();
		}
		
		//give all individuals scores
		myPop.giveAllIndividualsScores();
		
		
		//highest scoring individual and give it words from dictionary and match it with closest match
		Individual bestIndividual = myPop.getIndividualWithHighestScore();
		System.out.println("\nHighest Scoring Individual: " + bestIndividual + ", Score: " + bestIndividual.getTotalScore());

		String[] bestIndividualWords = dictionary.correctErrorInIndividual(bestIndividual);
		bestIndividualWords = dictionary.deleteUnecessaryWords(bestIndividualWords);
		//TODO: with the words structure sentence into meaningful English sentence by sorting and validating
		//TODO: get a list of verbs, nouns, objects and have checking for simple sentences, compound sentences
		//and complex sentences
		//Parts of a simple sentence: Subject, Predicate, Clause, Phrase and Modifier
		System.out.println();
		for(int i = 0; i < bestIndividualWords.length; i++){
			System.out.print(bestIndividualWords[i] + " ");
		}
		
	}
	

}
