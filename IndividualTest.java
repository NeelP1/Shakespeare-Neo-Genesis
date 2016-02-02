import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class IndividualTest {

	@Ignore
	public void testGenerateIndividual() {
		int len = 8;
		byte[] byteArr;
		Individual individual = new Individual(len);
		
		individual.generateIndividual();
		byteArr = individual.getGenes();
		//assertTrue(individual.getGenes().equals(byte[].class));
		//System.out.println(individual.getGenes().equals(byte[].class));
		for(int i = 0; i < len; i++){
			System.out.print((char)byteArr[i]);
		}
		System.out.println();
	}

	@Ignore
	public void testGetGeneChar() {
		Individual individual = new Individual(2);
		
		individual.setGene(0, (byte) 104);
		individual.setGene(1, (byte) 105);
		
		assertEquals('h', individual.getGeneChar(0));
		assertEquals('i', individual.getGeneChar(1));
	}

	@Ignore
	public void testGetGene() {
		Individual individual = new Individual(2);
		
		individual.setGene(0, (byte) 104);
		individual.setGene(1, (byte) 105);
		
		assertEquals(104, individual.getGene(0));
		assertEquals(105, individual.getGene(1));
	}

	@Test
	public void testSetGene() {
		Individual individual = new Individual(2);
		
		individual.setGene(0, (byte) 104);
		individual.setGene(1, (byte) 105);
	}

	@Ignore
	public void testSize() {
		Individual individual = new Individual(2);
		//individual.generateIndividual();
		assertEquals(2, individual.size());
		
	}

	@Ignore
	public void testGetFitness() {
		byte[] byteArr = {102, 103};
		FitnessCalc.setSolution(byteArr);
		Individual individual = new Individual(2);
		individual.setGene(0, (byte) 104);
		individual.setGene(1, (byte) 105);
		
		assertEquals(0, individual.getFitness());
	}

	@Ignore
	public void testToString() {
		Individual individual = new Individual(2);
		individual.setGene(0, (byte) 104);
		individual.setGene(1, (byte) 105);
		assertEquals("hi", individual.toString());
	}
	
	
	@Test
	public void testAddSpacesToIndividualAlt(){
		Individual individual = new Individual(100);
		individual.generateIndividual();
		individual.addSpacesToIndividual();
		System.out.println("Testing addSpacesToIndividualAlt:");
		System.out.println(individual.toString());
	}

}
