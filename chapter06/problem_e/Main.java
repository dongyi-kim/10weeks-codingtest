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
		
		long ret = pow((a * a) % MOD, n / 2);
		if (n % 2 == 1) {
			ret *= a;
		}
		return ret % MOD;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int n = scanner.nextInt();
		long answer = pow(a, n);
		System.out.println(answer);
	}
}