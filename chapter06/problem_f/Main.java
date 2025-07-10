import java.io.*;
import java.lang.*;
import java.util.*;

class Main {
	public static final int MOD = 1000000007;

	/**
	 * a^n을 계산하는 함수
	 *
	 * @param a
	 * @param n
	 * @return a^n을 1,000,000,007로 나눈 나머지를 출력한다
	 */
	public static long pow(long a, long n) {
		if (n == 0) return 1;
		if (n == 1) return a;

		// 절반에 대한 결과값을 계산
		long half = pow(a, n / 2);

		// 이를 제곱하여 전체 결과 계산 (나머지 계산 포함)
		long ret = (half * half) % MOD;

		// 홀수인 경우 예외처리 (나머지 계산 포함)
		if (n % 2 == 1) {
			ret = (ret * a) % MOD;
		}

		return ret;
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int n = scanner.nextInt();
		long answer = pow(a, n);
		System.out.println(answer);
	}
}