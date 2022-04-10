import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int n = scanner.nextInt();
		int[][] costMap = new int[n][n];
		for (int r = 0; r < n; r += 1) {
			for (int c = 0; c < n; c += 1) {
				costMap[r][c] = scanner.nextInt();
			}
		}

		Solution solution = new Solution(costMap);
		int answer = solution.f(n-1,n-1);

		System.out.println(answer);
	}
}


class Solution {
	private static final int EMPTY = -1;
	private static final int INFINITY = 1000000000;

	private int[][] cost;
	private int[][] memo;
	private int n;

	public Solution(int[][] costMap) {
		int n = costMap.length;
		this.cost = new int[n][n];
		this.memo = new int[n][n];
		for (int i = 0; i < n; i += 1) {
			for (int j = 0; j < n; j += 1) {
				this.cost[i][j] = costMap[i][j];
				this.memo[i][j] = EMPTY;
			}
		}
	}


	/**
	 * (0, 0) ~ (lastRow, lastCol)까지만 고려했을 때 문제의 정답
	 *
	 * @param lastRow
	 * @param lastCol
	 * @return	최단경로들 중 가장 작은 난이도 합
	 */
	public int f(int lastRow, int lastCol) {
		if (lastCol < 0 || lastRow < 0) {
			return INFINITY;
		} else if (memo[lastRow][lastCol] != EMPTY) {
			return memo[lastRow][lastCol];
		} else if (lastRow == 0 && lastCol == 0) {
			return cost[0][0];
		}

		int answer = Math.min(
				f(lastRow - 1, lastCol) + cost[lastRow][lastCol],
				f(lastRow, lastCol - 1) + cost[lastRow][lastCol]
		);

		memo[lastRow][lastCol] = answer;
		return answer;
	}

}