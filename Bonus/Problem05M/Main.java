import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static ArrayList<String> getMatchedStrings(String pattern, int N, String[] strings) {
		ArrayList<String> matched = new ArrayList<>();

		for (int i = 0; i < N; i += 1) {
			// 모든 문자열 S[i]에 대하여 
			String s = strings[i];

			// 패턴과 일치하면 정답에 인덱스를 추가한다
			if (StringUtil.isMatched(pattern, s)) {
				matched.add(s);
			}
		}

		return matched;
	}

	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();

		String pattern = scanner.next();
		String[] inputs = new String[N];
		for (int i = 0; i < N; i += 1) {
			inputs[i] = scanner.next();
		}

		ArrayList<String> matchedStrings = getMatchedStrings(pattern, N, inputs);

		for (int i = 0; i < matchedStrings.size(); i += 1) {
			String s = matchedStrings.get(i);
			writer.write(s);
			writer.write("\n");
		}

		writer.flush();
		writer.close();
	}

}

class StringUtil {
	public static final char ANY_CHARACTER = '?';

	public static boolean isMatched(String pattern, String str) {
		// 글자수가 다르다면 매치될 수 가 없으므로 종료 
		if (pattern.length() != str.length()) {
			return false;
		}

		int N = pattern.length();
		int matchedCount = 0;
		for (int i = 0; i < N; i += 1) {
			// 각 i번째 글자 cp와 cs에 대하여 
			char cp = pattern.charAt(i);
			char cs = str.charAt(i);

			// cp와 cs가 일치하거나, cp가 '?' 와일드카드 인 경우 일치로 판단한다
			if (cp == cs || cp == StringUtil.ANY_CHARACTER) {
				matchedCount += 1;
			} else {
				// 하나라도 다른 문자가 등장한 경우 종료한다
				break;
			}
		}

		// 모든 글자가 완전히 일치했다면 true를 반화한다
		if (matchedCount == N) {
			return true;
		}
		return false;
	}


}