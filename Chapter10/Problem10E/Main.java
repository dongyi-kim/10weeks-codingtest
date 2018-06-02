import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int[][] values = new int[n][n];
		for (int r = 0; r < n; r += 1) {
			for (int c = 0; c < n; c += 1) {
				values[r][c] = scanner.nextInt();
			}
		}

		RangeSum rangeSum = new RangeSum(values);

		for (int i = 0; i < m; i += 1) {
			int minRow = scanner.nextInt() - 1;
			int minCol = scanner.nextInt() - 1;
			int maxRow = scanner.nextInt() - 1;
			int maxCol = scanner.nextInt() - 1;
			int sum = 0;
			sum += rangeSum.f(maxRow, maxCol);
			sum -= rangeSum.f(maxRow, minCol - 1);
			sum -= rangeSum.f(minRow - 1, maxCol);
			sum += rangeSum.f(minRow - 1, maxCol - 1);

			System.out.println(sum);
		}
	}
}

class RangeSum {
	private static int EMPTY = Integer.MIN_VALUE;
	private int[][] memo;
	private int[][] values;
	private int n;

	public RangeSum(int[][] values) {
		this.n = values.length;
		this.values = new int[n][n];
		this.memo = new int[n][n];
		for (int i = 0; i < n; i += 1) {
			for (int j = 0; j < n; j += 1) {
				this.memo[i][j] = EMPTY;
				this.values[i][j] = values[i][j];
			}
		}
	}

	/**
	 * values[0][0] ~ values[lastRow][lastCol] 까지의 직사각형 누적합을 계산하는 함수
	 *
	 * @param lastRow 마지막 행 번호
	 * @param lastCol 마지막 열 번호
	 * @return 해당 범위의 누적합
	 */
	public int f(int lastRow, int lastCol) {
		if (lastCol < 0 || lastRow < 0) {
			return 0;
		} else if (memo[lastRow][lastCol] != EMPTY) {
			return memo[lastRow][lastCol];
		} else if (lastRow == 0 && lastCol == 0) {
			return values[0][0];
		}

		int totalSum
				= values[lastRow][lastCol]
				+ f(lastRow - 1, lastCol)
				+ f(lastRow, lastCol - 1)
				- f(lastRow - 1, lastCol - 1);

		return totalSum;
	}
}