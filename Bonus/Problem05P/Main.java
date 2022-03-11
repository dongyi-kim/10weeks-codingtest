import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 *
	 * @param fullName  만들고자 하는 이름 문자열
	 * @param N         사용할 수 있는 단어의 수
	 * @param words     이름 제작에 사용할 수 있는 단어들 words[0] ~ words[N-1]
	 * @return   서로 다른 세 개의 단어로 fullName을 만들 수 있다면 true, else false
	 */
	public static boolean isPossibleName(String fullName, int N, String[] words) {

		// 서로 다른 i, j, k에 대해 (words[i] + words[j] + words[k])로 이름을 만들어보자 .
		for (int i = 0; i < N; i += 1) {
			// 모든 접두사 words[i]에 대해
			String prefix = words[i];

			// 만들고자 하는 이름이 prefix로 시작하지 않는다면 건너뛴다
			if (fullName.startsWith(prefix) == false) {
				continue;
			}

			for (int k = 0; k < N; k += 1) {
				// 모든 접미사 words[k]에 대해
				String suffix = words[k];

				// words[i]와 중복되거나 만들고자 하는 이름의 접미사가 아니라면 건너뛴다.
				if (i == k || fullName.endsWith(suffix) == false) {
					continue;
				}

				for (int j = 0; j < N; j += 1) {
					// 중간 부분 word[sj]에 대해
					String middle = words[j];

					// 이미 등장한 단어라면 건너뛴다
					if (i == j || j == k) {
						continue;
					}

					// 결정된 words[i] + words[j] + words[k]에 대하여
					String concatenated = prefix + middle + suffix;

					// 만들고자 하는 이름과 일치한다면 true를 반환한다
					if (fullName.equals(concatenated)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		String fullName = scanner.next();
		String[] words = new String[N];
		for (int i = 0; i < N; i += 1) {
			words[i] = scanner.next();
		}

		boolean possible = isPossibleName(fullName, N, words);

		if (possible) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}
