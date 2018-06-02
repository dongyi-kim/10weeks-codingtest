import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int caseNum = scanner.nextInt();
		for (int caseIndex = 1; caseIndex <= caseNum; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

	public static void testCase(int caseIndex) {
		String input = scanner.next().trim();
		int n = input.length();

		Solution solution = new Solution(input);
		int answer = solution.f(0, n-1);

		System.out.println(answer);
	}
}

class Solution {
	private static final int EMPTY = -1;
	private char[] brackets;
	private int[][] memo;
	private int n;

	public Solution(String input) {
		this.n = input.length();
		this.brackets = input.toCharArray();
		this.memo = new int[n][n];
		for (int i = 0; i < n; i += 1) {
			Arrays.fill(memo[i], EMPTY);
		}
	}

	/**
	 * 부분 문자열 brackets[leftMost...rightMost]에 대한 가장 긴 올바른 괄호 문자열의 길이
	 *
	 * @param leftMost		가장 왼쪽 인덱스
	 * @param rightMost		가장 오른쪽 인덱스
	 * @return				주어진 범위에 대한 가장 긴 올바른 괄호 문자열의 길이
	 */
	public int f(int leftMost, int rightMost) {
		if (leftMost < 0 || rightMost >= n || leftMost > rightMost) {
			return 0;
		} else if (memo[leftMost][rightMost] != EMPTY) {
			return memo[leftMost][rightMost];
		} else if (leftMost == rightMost) {
			// 한 글자는 괄호 문자열이 될 수 없다
			return 0;
		}

		int answer = 0;

		// 양쪽 끝이 짝이 맞는 경우, 해당 괄호짝이 감싼 경우를 고려한다
		if (true == isPaired(leftMost, rightMost)) {
			answer = 2 + f(leftMost + 1, rightMost - 1);
		}

		// 어떤 한 지점으로부터 양쪽으로 분할된 경우를 고려한다.
		for (int mid = leftMost; mid < rightMost; mid += 1) {
			int leftLength = f(leftMost, mid);
			int rightLength = f(mid + 1, rightMost);
			int totalLength = leftLength + rightLength;
			answer = Math.max(answer, totalLength);
		}

		memo[leftMost][rightMost] = answer;
		return answer;
	}

	private boolean isPaired(int leftIndex, int rightIndex) {
		if (brackets[leftIndex] == '(') {
			return brackets[rightIndex] == ')';
		} else if (brackets[leftIndex] == '[') {
			return brackets[rightIndex] == ']';
		}
		return false;
	}
}
