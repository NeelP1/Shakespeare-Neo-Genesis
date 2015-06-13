import static org.junit.Assert.*;

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
	

}
