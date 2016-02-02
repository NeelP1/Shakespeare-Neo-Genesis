import java.util.HashMap;

/* 
 * package has InputOutput (readFile method included) class, Dictionary extends this
 * 
 * http://www.vogella.com/tutorials/JUnit/article.html
 * 
 */

public class Dictionary extends InputOutput{

	private String[] wordListArr;
	private HashMap<String, String> wordList;
	
	public Dictionary(){
		this.wordListArr = new String[1];
		this.wordList = new HashMap<String, String>();
		
	}
	
	/**
	 * Takes in a word and returns a hashed code
	 * 
	 * (ascii characters in decimal number form)
	 * 
	 * @param  word 	a string you want a hash key for 
	 * @return hashKey  key for the string being hashed
	 */
	public String hasher(String word){
		String hashKey = "";
		
		for(int i = 0; i < word.length(); i++){
			hashKey += ((int)word.charAt(i)) + "-";
		}
		
		return hashKey;
	}
	
	/**
	 * Converts a string that is a list of words separated by \n
	 * into an string array of words
	 * 
	 * @param wordListAsString
	 */
	public void fillWordListArr(String wordListAsString){
		wordListArr = wordListAsString.split("\n");
	}
	
	/**
	 * converts wordListArr into hashMap with new keys
	 */
	public void fillHashMap(){
		for(int i = 0; i < wordListArr.length; i++){
			wordList.put(hasher(wordListArr[i]), wordListArr[i]);
		}
			
	}
	
	/**
	 * Returns true if wordList contains exact word
	 * 
	 * @param  potentialWord
	 * @return true if potentialWord is in wordList
	 */
	public boolean searchList(String potentialWord){
		if(wordList.containsValue(potentialWord)){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Searchers wordList for best match
	 * 
	 * @param  approxWord a string you want to identify the match with 
	 * @return bestMatch  best match with approxWord
	 */
	public String searchLikeWords(String approxWord){
		String bestMatch = "";
		double bestMatchScore = 0.0, temp = 0.0;
		
		//future modification: skip to words starting with first letter of 'word' string
		for(int i = 0; i < wordListArr.length; i++){
			temp = similarity(approxWord, wordListArr[i]);
			if(temp > bestMatchScore){
				bestMatchScore = temp;
				bestMatch = wordListArr[i];
			}
		}
		
		return bestMatch;
	}
	
	
	//TODO: change above method to return a object which holds a word (most similar word to approxWord),
	//double (similarity score)
	public String[] returnLikeWordsArray(String [] approxWords){
		String[] likeWordsArray = new String[approxWords.length];
		
		for(int i = 0; i < likeWordsArray.length; i++){
			likeWordsArray[i] = searchLikeWords(approxWords[i]);
		}
		
		return likeWordsArray;
	}
	
	
	/**
	 * Similar method to searchLikeWords but returns the best similarity score
	 * 
	 * @param  approxWord 
	 * @return bestMatchScore
	 */
	public double getSimilarityScore(String approxWord){
		double bestMatchScore = 0.0, temp = 0.0;
		
		for(int i = 0; i < wordListArr.length; i++){
			temp = similarity(approxWord, wordListArr[i]);
			if(temp > bestMatchScore){
				bestMatchScore = temp;
			}
		}
		
		return bestMatchScore;
	}
	
	
	/**
	 * Similarity between two strings
	 * 
	 * @param s1
	 * @param s2
	 * @return long string length - distance(long string, short string) / long string length
	 */
	public double similarity(String s1, String s2) {
		String longer = s1, shorter = s2;
		if (s1.length() < s2.length()) { // longer should always have greater length
			longer = s2; shorter = s1;
		}
		  
		int longerLength = longer.length();
		
		if (longerLength == 0) { 
			return 1.0; /* both strings are zero length */
		}
		  
		return (longerLength - distance(longer, shorter)) / (double) longerLength;
	}
	
	/**
	 * Levenshtein distance
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int distance(String a, String b) {
		a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
	
	/*Getters and Setters*/

	public String[] getWordListArr() {
		return wordListArr;
	}

	public void setWordListArr(String[] wordListArr) {
		this.wordListArr = wordListArr;
	}

	public HashMap<String, String> getWordList() {
		return wordList;
	}

	public void setWordList(HashMap<String, String> wordList) {
		this.wordList = wordList;
	}
	
}
