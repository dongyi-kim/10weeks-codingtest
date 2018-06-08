import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int n = scanner.nextInt();
		int[] signA = new int[n];
		int[] signB = new int[n];

		for (int i = 0; i < n; i += 1) {
			signA[i] = scanner.nextInt();
		}

		for (int i = 0; i < n; i += 1) {
			signB[i] = scanner.nextInt();
		}

		DTW dtw = new DTW(signA, signB);

		int answer = dtw.f(n - 1, n - 1);
		System.out.println(answer);
	}
}


class DTW {
	public static final int EMPTY = -1;
	public static final int INFINITY = 1_000_000_000;

	private int[] signA;
	private int[] signB;
	private int[][] memo;
	private int n;
	private int m;

	public DTW(int[] signA, int[] signB) {
		this.signA = signA.clone();
		this.signB = signB.clone();
		this.n = signA.length;
		this.m = signB.length;
		this.memo = new int[n][m];
		for (int i = 0; i < n; i += 1) {
			Arrays.fill(memo[i], EMPTY);
		}
	}

	/**
	 * 두 부분 파형 signA[0..lastIndexA], signB[0..lastIndexB]에 대한 최소의 거리
	 * signA[lastIndexA]와 signB[lastIndexB]는 반드시 대응되어야 한다.
	 *
	 * @param lastIndexA
	 * @param lastIndexB
	 * @return 두 부분 파형에대한 최소 거리
	 */
	public int f(int lastIndexA, int lastIndexB) {
		if (lastIndexA < 0 || lastIndexB < 0) {
			return INFINITY;
		} else if (memo[lastIndexA][lastIndexB] != EMPTY) {
			return memo[lastIndexA][lastIndexB];
		} else if (lastIndexA == 0 && lastIndexB == 0) {
			// 시점이 하나씩 밖에 없는 경우 서로 대응된다 
			int diff = signA[0] - signB[0];
			return diff * diff;
		}

		// 해당 두 시점의 차이 값 
		int diff = signA[lastIndexA] - signB[lastIndexB];
		int error = diff * diff;

		// 해당 두 시점 이전 시점들의 대응들에 대한 최적해와 더한다
		int answer = error + MIN(
				f(lastIndexA - 1, lastIndexB),
				f(lastIndexA, lastIndexB - 1),
				f(lastIndexA - 1, lastIndexB - 1)
		);

		return answer;
	}

	public static int MIN(int... arr) {
		int min = arr[0];
		for (int i = 0; i < arr.length; i += 1) {
			min = Math.min(min, arr[i]);
		}
		return min;
	}
}
