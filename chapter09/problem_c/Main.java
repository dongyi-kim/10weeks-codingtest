import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static int MAX(int... arr) {
		int max = arr[0];
		for (int i = 0; i < arr.length; i += 1) {
			max = Math.max(max, arr[i]);
		}
		return max;
	}

	public static void main(String[] args) {
		int n = scanner.nextInt();
		int[] profits = new int[n];
		for (int i = 0; i < n; i += 1) {
			profits[i] = scanner.nextInt();
		}

		int answer;

		// 두 가지 방법을 완성해보세요

		//[Solution A]
		SolutionA solutionA = new SolutionA(profits);
		answer = MAX(
				solutionA.f(n - 1, 0),
				solutionA.f(n - 1, 1),
				solutionA.f(n - 1, 2)
		);

		//[Solution B]
		SolutionB solutionB = new SolutionB(profits);
		answer = 0;
		for (int i = 0; i < n; i += 1) {
			answer = Math.max(answer, solutionB.f(i));
		}

		System.out.println(answer);
	}
}

class SolutionA {
	private static int EMPTY = -1;
	private int[][] memo;
	private int[] profits;
	private int n;

	public SolutionA(int[] profits) {
		this.n = profits.length;
		this.profits = profits.clone();
		this.memo = new int[n][3];
		for (int i = 0; i < n; i += 1) {
			Arrays.fill(memo[i], EMPTY);
		}
	}

	/**
	 * lastIndex번째 날에 연속으로 count번째 근무한다면 얻을 수 있는 최대 인센티브
	 *
	 * @param lastIndex 고려할 마지막 날짜의 인덱스
	 * @param count     연속으로 근무한 횟수
	 * @return 해당 가정에서의 정답
	 */
	public int f(int lastIndex, int count) {
		if (lastIndex < 0 || count < 0 || count >= 3) {
			return 0;
		} else if (memo[lastIndex][count] != EMPTY) {
			return memo[lastIndex][count];
		}

		int answer = 0;
		if (count == 0) {
			int caseA = f(lastIndex - 1, 0);
			int caseB = f(lastIndex - 1, 1);
			int caseC = f(lastIndex - 1, 2);
			answer = Main.MAX(caseA, caseB, caseC);
		} else {
			answer = f(lastIndex - 1, count - 1) + profits[lastIndex];
		}

		memo[lastIndex][count] = answer;
		return memo[lastIndex][count];
	}

}

class SolutionB {
	private static int EMPTY = -1;
	private int[] memo;
	private int[] profits;
	private int n;

	public SolutionB(int[] profits) {
		this.n = profits.length;
		this.profits = profits.clone();
		this.memo = new int[n];
		Arrays.fill(memo, EMPTY);
	}

	/**
	 * profits[0] ~ profits[lastIndex]까지만 고려했을 때의 정답
	 * 단, profits[lastIndex]는 무조건 마지막으로 사용한다.
	 *
	 * @param lastIndex 마지막으로 근무할 날의 인덱스
	 * @return 'lastIndex'번째 날에 마지막으로 근무 했을 때 얻을 수 있는 최대 인센티브
	 */
	public int f(int lastIndex) {
		if (lastIndex < 0) {
			return 0;
		} else if (memo[lastIndex] != EMPTY) {
			return memo[lastIndex];
		} else if (lastIndex == 0) {
			return profits[0];
		}

		int answer = Math.min(
			f(lastIndex - 2) + profits[lastIndex],
			f(lastIndex - 3) + profits[lastIndex - 1] + profits[lastIndex]
		);

		memo[lastIndex] = answer;
		return memo[lastIndex];
	}
}