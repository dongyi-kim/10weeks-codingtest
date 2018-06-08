import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		int R = scanner.nextInt();
		int L = scanner.nextInt();

		Robot[] robots = new Robot[N];
		for (int i = 0; i < N; i += 1) {
			int X = scanner.nextInt();
			robots[i] = new Robot(X, R);
		}

		int answer = Solution.getMinimumMoveDistance(N, L, robots);

		System.out.println(answer);
	}

	public static void main(String[] args) {
		int caseNum = scanner.nextInt();
		for (int caseIndex = 1; caseIndex <= caseNum; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
}

class Solution {

	public static boolean isPossible(int N, int L, int D, Robot[] robots) {
		int leftEnd = 0;
		int rightEnd = L;

		for (int i = 0; i < N; i += 1) {

			if (leftEnd <= robots[i].leftBound) {
				int distance = robots[i].leftBound - leftEnd;
				if (distance > D) {
					return false;
				}

				leftEnd = Math.max(robots[i].rightBound - distance, leftEnd);
			} else {
				int distance = Math.min(D, leftEnd - robots[i].leftBound);

				leftEnd = Math.max(robots[i].rightBound + distance, leftEnd);
			}

		}

		return leftEnd >= rightEnd;
	}

	public static int getMinimumMoveDistance(int N, int L, Robot[] robots) {
		Arrays.sort(robots);
		long lowerBound = 0;
		long upperBound = Integer.MAX_VALUE;

		while (lowerBound < upperBound) {
			int distanceLimit = (int) ((lowerBound + upperBound + 1) / 2);

			boolean possible = isPossible(N, L, distanceLimit, robots);

			if (possible == true) {
				upperBound = distanceLimit;
			} else {
				lowerBound = distanceLimit + 1;
			}

		}

		return (int)lowerBound;
	}
}

class Robot implements Comparable<Robot> {
	public final int x;
	public final int leftBound;
	public final int rightBound;

	public Robot(int x, int r) {
		this.x = x;
		this.leftBound = x - r;
		this.rightBound = x + r;
	}


	@Override
	public int compareTo(Robot other) {
		return this.x - other.x;
	}
}