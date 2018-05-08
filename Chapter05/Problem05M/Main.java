import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static ArrayList<String> getMatchedStrings(String pattern, int N, String[] strings){
		ArrayList<String> matched = new ArrayList<>();

		for(int i = 0 ; i < N; i+= 1){
			String s = strings[i];
			if(StringUtil.isMatched(pattern, s)){
				matched.add(s);
			}
		}

		return matched;
	}

	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();

		String pattern = scanner.next();
		String[] inputs = new String[N];
		for(int i = 0; i < N ; i+= 1){
			inputs[i] = scanner.next();
		}

		ArrayList<String> matchedStrings = getMatchedStrings(pattern, N, inputs);

		for(int i = 0; i < matchedStrings.size(); i+= 1){
			String s = matchedStrings.get(i);
			writer.write(s);
			writer.write("\n");
		}

		writer.flush();
		writer.close();
	}

}

class StringUtil{
	public static final char ANY_CHARACTER = '?';

	public static boolean isMatched(String pattern, String str) {
		if(pattern.length() != str.length()){
			return false;
		}

		int N = pattern.length();
		int matchedCount = 0;
		for(int i = 0 ; i < N ; i += 1){
			char cp = pattern.charAt(i);
			char cs = str.charAt(i);
			if(cp == cs || cp == StringUtil.ANY_CHARACTER){
				matchedCount += 1;
			}else{
				break;
			}
		}

		if(matchedCount == N){
			return true;
		}
		return false;
	}


}