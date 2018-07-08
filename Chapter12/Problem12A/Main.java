import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		String S = scanner.next();
		String P = scanner.next();

		ArrayList<Integer> indexes
				= RabinKarp.getMatchedIndexes(S, P);

		if (indexes.size() == 0) {
			System.out.println("Not Matched");
		} else {
			StringBuilder output = new StringBuilder();
			for (int i = 0; i < indexes.size(); i += 1) {
				if (i > 0) {
					output.append(" ");
				}
				output.append(indexes.get(i));
			}
			System.out.println(output.toString());
		}
	}
}

class RabinKarp {
	public static final long MODULAR = 1000000007;
	public static final long BASE = 257;

	/**
	 * 라빈카프 알고리즘 기반으로
	 *
	 * @param S
	 * @param P
	 * @return
	 */
	public static ArrayList<Integer> getMatchedIndexes(String S, String P) {
		int N = S.length();    // 대상 문자열의 길이
		int M = P.length(); // 패턴 문자열의 길이

		if (N < M) {        // 패턴이 더 긴 경우는 존재하지 않는다.
			return new ArrayList<>();
		}

		long[] weights = getWeightTable(M);    // 각 자리별 가중치 테이블을 계산한다 - O(M)
		long hashP = getHash(P, weights);    // 패턴 문자열에 대한 해시 값을 계산한다 - O(M)
		long hashS = getHash(S.substring(0, M), weights); // 대상 문자열의 가장 앞 부분 문자열의 해시를 계산한다 - O(M)

		ArrayList<Integer> matchedIndexes = new ArrayList<>();

		if (hashP == hashS) {    // 두 해시값이 같다면, 가장 앞 부분 문자열은 패턴과 일치한다 (편의상 따로 처리)
			matchedIndexes.add(0);
		}

		for (int begin = 1; begin + M - 1 < N; begin += 1) {
			// 각 부분 문자열 [ begin, begin + M - 1 ] 에 대해

			// 이전 영역의 해시 값을 사용하여, 이번 범위의 해시 값을 계산한다 - O(f(M)) = O(1)
			hashS = (hashS - S.charAt(begin - 1) * weights[M - 1]) % MODULAR;
			hashS = (hashS * BASE) % MODULAR;
			hashS = (hashS + S.charAt(begin + M - 1) * weights[0]) % MODULAR;
			if (hashS < 0) {	// 모듈러 값이 음수가 되는 경우 예외처리
				hashS += MODULAR;
			}

			if (hashP == hashS) {	// 패턴 문자열과 해시 값이 일치한다면 직접 문자열을 비교한다.
				boolean equal = true;
				for (int matched = 0; matched < M; matched += 1) {
					if (S.charAt(begin + matched) != P.charAt(matched)) {
						equal = false;
						break;
					}
				}

				// 직접 비교 결과, 두 문자열이 같다면 패턴이 매칭된 것으로 처리한다.
				if (equal) {
					matchedIndexes.add(begin);
				}
			}
		}
		return matchedIndexes;
	}

	/**
	 * 각 자리 별 Weight를 계산해둔 배열을 반환하는 함수
	 *
	 * @param M 패턴 문자열의 길이
	 * @return weights[i] := 오른쪽에서 i번째 문자에 곱할 가중치
	 */
	public static long[] getWeightTable(int M) {
		long[] weights = new long[M + 1];

		weights[0] = 1;
		for (int i = 1; i < weights.length; i += 1) {
			weights[i] = (weights[i - 1] * BASE) % MODULAR;
		}

		return weights;
	}

	/**
	 * 주어진 Weight 테이블을 사용해 문자열 P의 해시 값을 계산하는 함수
	 *
	 * @param P       해시 값을 계산할 문자열
	 * @param weights 각 자리별 가중치 테이블
	 * @return
	 */
	public static long getHash(String P, long[] weights) {
		long hash = 0;

		final int M = P.length();

		for (int i = 0; i < M; i += 1) {
			int position = M - i - 1;

			hash += P.charAt(i) * weights[position];
			hash %= MODULAR;
		}

		return hash;
	}
}
