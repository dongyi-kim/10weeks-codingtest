import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		String stringA = scanner.next();
		String stringB = scanner.next();
		int n = stringA.length();
		int m = stringB.length();

		LCS lcs = new LCS(stringA, stringB);
		int answer = lcs.f(n - 1, m - 1);

		System.out.println(answer);
	}
}


class LCS {
	private static final int EMPTY = -1;
	private int[][] memo;

	private char[] A;
	private char[] B;

	private int lenA;
	private int lenB;

	public LCS(String stringA, String stringB) {
		A = stringA.toCharArray();
		B = stringB.toCharArray();
		lenA = A.length;
		lenB = B.length;
		this.memo = new int[lenA][lenB];
		for (int i = 0; i < lenA; i += 1) {
			for (int j = 0; j < lenB; j += 1) {
				memo[i][j] = EMPTY;
			}
		}
	}

	/**
	 * 부분 문자열 A[0..lastIndexA]와 B[0..lastIndexB] 사이의 LCS길이
	 *
	 * @param lastIndexA 문자열 A의 마지막 문자 인덱스
	 * @param lastIndexB 문자열 B의 마지막 문자 인덱스
	 * @return 해당 부분 문자 열사이의 LCS길이
	 */
	public int f(int lastIndexA, int lastIndexB) {
		if (lastIndexA < 0 || lastIndexB < 0) {
			// 둘 중 하나가 빈 문자열인 경우는 0
			return 0;
		} else if (memo[lastIndexA][lastIndexB] != EMPTY) {
			// 이미 계산된 값인 경우
			return memo[lastIndexA][lastIndexB];
		}

		int caseA = f(lastIndexA - 1, lastIndexB);	// A[i]를 제외하고 계산한 LCS
		int caseB = f(lastIndexA, lastIndexB - 1);	// B[j]를 제외하고 계산한 LCS
		int caseC = f(lastIndexA - 1, lastIndexB - 1);	// A[i], B[j]를 모두 제외하고 계산한 LCS
		if (A[lastIndexA] == B[lastIndexB]) {
			caseC += 1;		// 만약 A[i], B[j]가 일치한다면 LCS길이 + 1
		}

		int answer = MAX(caseA, caseB, caseC);

		memo[lastIndexA][lastIndexB] = answer;
		return answer;
	}


	public static int MAX(int... arr) {
		int max = arr[0];
		for (int i = 0; i < arr.length; i += 1) {
			max = Math.max(max, arr[i]);
		}
		return max;
	}
}
