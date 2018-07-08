import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		String S = scanner.next();
		String P = scanner.next();

		int N = S.length();
		int M = P.length();

		int[] PI = new int[M];

		for (int i = 0; i < M; i += 1) {
			PI[i] = scanner.nextInt();
		}

		ArrayList<Integer> indexes
				= KMP.getMatchedIndexes(S, P, PI);

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

class KMP {
	/**
	 * KMP 알고리즘을 수행하여 패턴이 매칭된 시작 인덱스들을 반환하는 함수
	 *
	 * @param S     탐색 문자열
	 * @param P     패턴 문자열
	 * @param PI    패턴 P에 대한 Prefix-Function(Failure Function)
	 * @return      모든 시작 인덱스 리스트 (오름차순)
	 */
	public static ArrayList<Integer> getMatchedIndexes(String S, String P, int[] PI) {
		final int N = S.length();    //대상 문자열의 길이
		final int M = P.length();    //패턴 문자열의 길이

		int begin = 0;                // 비교를 시작할 왼쪽 인덱스
		int matched = 0;            // 일치한 문자 수

		ArrayList<Integer> matchedIndexes = new ArrayList<>();

    while (begin + matched < N) {	// 대상 문자열을 초과하지 않는 동안
			// 현재 위치 문자가 패턴과 일치하는 경우
			if (matched < M && S.charAt(begin + matched) == P.charAt(matched)) {
				matched += 1;
				if (matched == M) { // M개의 글자가 일치한다 <=> 이 위치에서 패턴이 등장한다
					matchedIndexes.add(begin);
				}
			} else {
				if (matched == 0) {
					// 한 글자도 일치하지 않았다면 그냥 다음 칸으로 이동한다
					begin += 1;
				} else {
					// 일부분이 일치한 후 글자가 틀린 경우
					begin += matched - PI[matched - 1];	// 다음 후보 위치로 이동한다
					matched = PI[matched - 1];			// 해당 부분에서 접두사의 길이만큼은 비교를 생략한다.
				}
			}
		}

		return matchedIndexes;
	}
}
