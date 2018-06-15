import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);


	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		long M = scanner.nextLong();
		long[] S = new long[N];
		long[] V = new long[N];
		for (int i = 0; i < N; i += 1) {
			S[i] = scanner.nextLong();
		}
		for (int i = 0; i < N; i += 1) {
			V[i] = scanner.nextLong();
		}

		long maximumSpeed = Solution.getMaximumSpeed(N, M, S, V);

		System.out.println(maximumSpeed);
	}

	public static void main(String[] args) {
		int caseNum = scanner.nextInt();
		for (int caseIndex = 1; caseIndex <= caseNum; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
}

class Solution{

	/**
	 * @brief 총 M의 예산을 투자하여 가장 느린 기차의 속도 최대값
	 *
	 * @param N  기차의 수
	 * @param M  총 예산
	 * @param S  S[i] := 각 열차의 초기 속력
	 * @param V  V[i] := 각 열차의 단위 예산당 속도 증가량
	 * @return
	 */
	public static long getMaximumSpeed(int N, long M, long[] S, long[] V){
		long lowerBound = 0;				// 속도는 최소 0이므로
		long upperBound = 200000000000L;	// 이론상 최대 속력

		while(lowerBound < upperBound){ // 범위가 하나로 정해질 때 까지
			long minimumSpeed = (lowerBound + upperBound + 1) / 2; // 중간값 계산 후 테스트
			boolean possible = isPossible(N, M, minimumSpeed, S, V);

			if(possible){	 // 가능하다면, 그 이상의 속도로 범위를 좁힌다
				lowerBound = minimumSpeed;
			}else{			// 불가능하다면, 그 이하의 속도로 범위를 좁힌다.
				upperBound = minimumSpeed - 1;
			}
		}

		// 결과를 반환한다
		return lowerBound;
	}

	/**
	 * 총 M의 예산으로 최저 속도 하한선 달성 여부를 검사하는 함수
	 * @param N				열차의 수
	 * @param M				총 예산
	 * @param minimumSpeed	속도 하한선
	 * @param S				각 열차의 초기 속도
	 * @param V				각 열차의 예산당 속도 증가량
	 * @return				실현 가능 여부
	 */
	public static boolean isPossible(int N, long M, long minimumSpeed, long[] S, long[] V){
		long totalBudget = M;	// 총 예산

		for(int i = 0 ; i < N ; i += 1){ // 모든 열차에 대해
			if(minimumSpeed <= S[i]){ // 이미 하한 속도를 넘겼다면 건너뛴다.
				return false;
			}

			long diff = minimumSpeed - S[i]; 		// 늘려야 할 속도 량
			long budgetCost = (diff-1) / V[i] + 1;	// 그리고 그 때 필요한 비용

			totalBudget -= budgetCost;		// 해당 비용을 전체 예산에서 감산한다
		}

		return (totalBudget >= 0);	// 예산 M이내로 모두 충당이 가능하면 true
	}
}
