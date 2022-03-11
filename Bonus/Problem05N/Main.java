import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		String S = scanner.next();
		String P = scanner.next();

		ArrayList<Integer> matchedIndexes = StringUtil.getMatchedIndexes(S, P);

		for (int i = 0; i < matchedIndexes.size(); i += 1) {
			int index = matchedIndexes.get(i);
			writer.write(String.valueOf(index));
			writer.write("\n");
		}

		writer.flush();
		writer.close();
	}

}

class StringUtil {

	/**
	 * 문자열 S의 내부에서 패턴 P가 등장하는 모든 시작 인덱스를 반환하는 함수
	 *
	 * @param S
	 * @param P
	 * @return
	 */
	public static ArrayList<Integer> getMatchedIndexes(String S, String P) {
		ArrayList<Integer> matchedIndexes = new ArrayList<>();

		int N = S.length(); // 문자열의 길이 
		int M = P.length(); // 패턴의 길이 

		for (int i = 0; i + M - 1 < N; i += 1) {
			// 문자열 S의 모든 글자이자 패턴의 검사를 시작할 시작 인덱스 i에 대하여 

			// 모든 글자가 일치했는지 여부 
			boolean matched = true;

			for (int j = 0; j < M; j += 1) {
				// S[i]에서부터 등장하는 패턴 P의 각 글자 P[j]에 대하여 

				// 일치하지 않는 글자가 등장했다면, 일치 여부를 갱신하고 종료한다.
				if (S.charAt(i + j) != P.charAt(j)) {
					matched = false;
					break;
				}
			}

			if (matched) {
				matchedIndexes.add(i);
			}
		}

		return matchedIndexes;
	}
}
