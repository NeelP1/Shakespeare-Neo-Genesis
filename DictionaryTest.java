import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class DictionaryTest {

	@Test
	public void hasherTest() {
		Dictionary dictionary = new Dictionary();
		
		System.out.println(dictionary.hasher("a"));
		System.out.println(dictionary.hasher("b"));
		System.out.println(dictionary.hasher("A"));
		System.out.println(dictionary.hasher("B"));
		
		assertEquals("52-99-",dictionary.hasher("4c"));
	}
	
	@Test
	public void fillWordListArrTest(){
		Dictionary dictionary = new Dictionary();
		
		String str = "cat\nhat\nnut";
		dictionary.fillWordListArr(str);
		String[] strArrResult = dictionary.getWordListArr();
		String[] strArrExpected = {"cat", "hat", "nut"};
		
		assertEquals(strArrResult[0], strArrExpected[0]);
	}
	
	@Test
	public void fllHashMapTest(){
		Dictionary dictionary = new Dictionary();
		String[] strArr = {"cat", "hat", "nut"};
		HashMap<String, String> wordList;
		
		dictionary.setWordListArr(strArr);
		dictionary.fillHashMap();
		wordList = dictionary.getWordList();
		
		assertTrue(wordList.containsValue("cat"));
	}
	
	@Test
	public void searchListTest(){
		Dictionary dictionary = new Dictionary();
		String[] strArr = {"cat", "hat", "nut"};
		HashMap<String, String> wordList = new HashMap<String, String>();
		for(int i = 0; i < strArr.length; i++){
			wordList.put(i + "", strArr[i]);
		}
		
		dictionary.setWordList(wordList);
		
		assertTrue(dictionary.searchList(strArr[0]));
		assertTrue(dictionary.searchList(strArr[1]));
	}
	
	@Test
	public void distanceTest(){
		Dictionary dictionary = new Dictionary();
		
		assertEquals(0, dictionary.distance("home", "home"));
		assertEquals(4, dictionary.distance("", "home"));
		System.out.println(dictionary.distance("homx", "ome"));
	}
	
	@Test
	public void similarityTest(){
		Dictionary dictionary = new Dictionary();
		
		System.out.println(dictionary.similarity("planetary", "earth"));
		
		//assertEquals(expected, actual, max difference of expected and actual -> ex - ac)
		assertEquals(0.5, dictionary.similarity("homx", "ome"), 0.0);
	}
	
	@Test
	public void searchListWordsTest(){
		Dictionary dictionary = new Dictionary();
		String[] strArr = {"cat", "love", "bounce"};
		HashMap<String, String> wordList = new HashMap<String, String>();
		dictionary.setWordListArr(strArr);
		for(int i = 0; i < strArr.length; i++){
			wordList.put(i + "", strArr[i]);
		}
		dictionary.setWordList(wordList);
		
		assertEquals("cat", dictionary.searchLikeWords("jakt"));
		assertEquals("love", dictionary.searchLikeWords("k4;eo"));
		assertEquals("bounce", dictionary.searchLikeWords("jbwoukje"));
		
	}
	
	@Test
	public void getSimilarityScoreTest(){
		Dictionary dictionary = new Dictionary();
		String[] strArr = {"cat", "love", "bounce"};
		HashMap<String, String> wordList = new HashMap<String, String>();
		dictionary.setWordListArr(strArr);
		
		for(int i = 0; i < strArr.length; i++){
			wordList.put(i + "", strArr[i]);
		}
		
		dictionary.setWordList(wordList);
		
		System.out.println("getSimilarity test output: " + dictionary.getSimilarityScore("jakt"));
		System.out.println("getSimilarity test output: " + dictionary.getSimilarityScore("k4;eo"));
		System.out.println("getSimilarity test output: " + dictionary.getSimilarityScore("jbwoukje"));
	}

}
