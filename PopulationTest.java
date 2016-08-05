import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class PopulationTest {

	@Test
	public void testGetIndividual() {
		//pop size, init, str len
		Population population = new Population(5, true, 8);
		Individual individual = population.getIndividual(1);
		
		//System.out.println(individual);
	}
	
	@Test
	public void testGetFittest() {
		//population length, initialize, individual length
		Population population = new Population(5, true, 8);
		FitnessCalc.setSolution("abcdefgh");
		
		Individual individual = population.getFittest();
		//System.out.println(population.getFittest());
		System.out.println(individual.size());
		assertEquals(8, individual.size());
	}
	
	@Test
	public void testSize(){
		Population population = new Population(5, true, 8);
		assertEquals(5, population.size());
	}
	
	@Test
	public void testSaveIndividual(){
		Population population = new Population(5, true, 8);
		Individual individual = new Individual(8);
		individual.generateIndividual();
		individual.setGenesWithString("abcdefgh");
		
		population.saveIndividual(0, individual);
		
		assertEquals("abcdefgh", population.getIndividualAsString(0));
	}
	
	@Test
	public void testCorrectErrorsInIndividuals(){
		//length of string = 57
		String individualAsString = "This is  test. H car petrol a seat I wind crash h motor x";
		System.out.println(individualAsString.length());
		
		Individual individual = new Individual(57);
		individual.setGenesWithString(individualAsString);
		System.out.println(individual.toString());
		
		Population population = new Population(1, true, 57);
		population.saveIndividual(0, individual);
		System.out.println(population.toString());
		
		population.correctErrorsInIndividuals();
		System.out.println(population.toString());
	}
	
	@Test
	public void testScewedProbability(){
		Random randomNum = new Random();
		System.out.println(randomNum.nextDouble());
	}

}
