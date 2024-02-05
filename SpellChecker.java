
public class SpellChecker {


	public static void main(String[] args) {
		String word = "coooool";//args[0];
		int threshold =2;// Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if(str.length()==1){
			return "";
		}
			return str.substring(1);	
		}
	

	public static int levenshtein(String word1, String word2) {
		if (word1.isEmpty()){
			return word2.length();
		}
		if (word2.isEmpty()){
			return word1.length();
	}
		if(word1.charAt(0)==word2.charAt(0)){
			return	levenshtein(tail(word1),tail(word2));
		}
		else{
			int a = levenshtein(tail(word1), word2);
		 	int b=levenshtein(word1, tail(word2));
			int c=levenshtein(tail(word1), tail(word2));
			int Currentminimum=Math.min(Math.min(b, a),c);
			return 1+Currentminimum;
		}
	}
	



	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i]=in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			if (levenshtein(word, dictionary[i]) <= threshold) {
				return dictionary[i];
			}
			
			if (levenshtein(word, dictionary[i]) == 0) {
				return word;
			}
		}
	
		// If no match is found within the threshold, return the original word.
		return word;
	}
}