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
		long lowerBound = 0;
		long upperBound = 200000000000L;

		while(lowerBound < upperBound){

			long minimumSpeed = (lowerBound + upperBound + 1) / 2;
			long totalBudget = M;

			for(int i = 0 ; i < N ; i += 1){
				if(minimumSpeed <= S[i]){
					continue;
				}

				long diff = minimumSpeed - S[i];
				long budgetCost = (diff-1) / V[i] + 1;

				totalBudget -= budgetCost;
			}

			if(totalBudget >= 0){
				lowerBound = minimumSpeed;
			}else{
				upperBound = minimumSpeed - 1;
			}

		}

		return lowerBound;
	}
}
