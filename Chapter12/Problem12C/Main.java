import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		String P = scanner.next();
		int M = P.length();

		int[] PI = getFailureFunctionTable(P);

		for (int i = 0; i < M; i += 1) {
			writer.write(String.format("%d\n", PI[i]));
		}

		writer.close();
	}

	/**
	 * 문자열 P에 대한 부분 매칭 테이블을 계산하여 반환해주는 함수
	 *
	 * @param P 테이블을 계산할 패턴 문자열
	 * @return 부분 매칭 테이블
	 */
	public static int[] getFailureFunctionTable(String P) {
		String S = P;
		int N = S.length(); // 탐색 대상 문자열 (자기자신)
		int M = P.length();    // 패턴 문자열
		int[] PI = new int[M];

		int begin = 1;        // 자기 자신을 찾는 경우를 무시하기 위해 1부터 시작
		int matched = 0;

		while (begin + matched < N) {
			if (S.charAt(begin + matched) == P.charAt(matched)) {
				matched += 1;
				// P[0..(matched-1)] == P[begin..(begin+matched-1)] 이다.
				// 즉, 부분 문자열 P[0..(begin-matched-1)]은 좌우 matched글자 만큼 공통 접두/접미사가 있다.
				PI[begin + matched - 1] = matched;
			} else {
				// KMP와 똑같이 수행한다.
				if (matched == 0) {
					begin += 1;
				} else {
					begin += matched - PI[matched - 1];
					matched = PI[matched - 1];
				}
			}
		}
		return PI;
	}
}
