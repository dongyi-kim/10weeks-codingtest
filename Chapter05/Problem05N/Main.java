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

	public static ArrayList<Integer> getMatchedIndexes(String S, String P) {
		ArrayList<Integer> matchedIndexes = new ArrayList<>();

		int N = S.length();
		int M = P.length();

		for (int i = 0; i + M - 1 < N; i += 1) {
			boolean matched = true;
			for (int j = 0; j < M; j += 1) {
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
