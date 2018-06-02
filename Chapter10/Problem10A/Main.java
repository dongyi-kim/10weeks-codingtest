import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int n = scanner.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i += 1) {
			arr[i] = scanner.nextInt();
		}

		LIS lis = new LIS(arr);
		int answer = 0;
		for (int i = 0; i < n; i += 1) {
			answer = Math.min(answer, lis.f(i));
		}

		System.out.println(answer);
	}
}

class LIS {
	private static final int EMPTY = -1;

	private int[] memo;         // DP 상태공간
	private int[] array;        // 수열의 원소
	private int n;              // 수열의 길이

	public LIS(int[] array) {
		this.array = array.clone();
		this.n = array.length;
		this.memo = new int[n];
		Arrays.fill(memo, EMPTY);
	}

	/**
	 * array[lastIndex]가 마지막 원소인 모든 LIS 길이를 계산하는 함수
	 *
	 * @param lastIndex 부분 수열의 마지막 원소의 인덱스
	 * @return array[lastIndex]가 마지막 원소인 LIS의 길이
	 */
	public int f(int lastIndex) {
		if (lastIndex < 0) {
			// 예외인 경우는 길이 0으로 취급한다.
			return 0;
		} else if (memo[lastIndex] != EMPTY) {
			// 이미 계산된 적 있는 결과라면 반환한다.
			return memo[lastIndex];
		} else if (lastIndex == 0) {
			return 1;
		}

		int answer = 1;
		for (int previousIndex = 0; previousIndex < lastIndex; previousIndex += 1) {
			int newLength = f(previousIndex) + 1;
			if (array[previousIndex] < array[lastIndex] && newLength > answer) {
				answer = newLength;
			}
		}
		memo[lastIndex] = answer;
		return memo[lastIndex];
	}
}