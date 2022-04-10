import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase(int caseIndex) {   //각 테스트케이스에 대하여
		int num1 = scanner.nextInt();
		int num2 = scanner.nextInt();

		// 두 숫자의 최대 공약수와 최소 공배수를 계산한다
		long gcd = MathUtil.getGCD(num1, num2);
		long lcm = MathUtil.getLCM(num1, num2);

		// 정답을 출력한다
		System.out.printf("Case #%d:\n", caseIndex);
		System.out.printf("%d %d\n", gcd, lcm);
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}

class MathUtil {

	/**
	 * a와 b의 최대 공약수를 계산하는 함수
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	static long getGCD(long a, long b) {
		while (a % b != 0) 
		{   // b가 a의 약수가 아니라면 반복한다
			long c = a % b; // 나머지를 계산한다
			a = b;          // 이후 b와 c에 대해 이 과정을 반복한다
			b = c;          // a <= b, b <= c
		}
		// b가 a의 약수라면, b가 a의 최대 공약수다
		return b;
	}

	/**
	 * a와 b의 최소 공배수를 계산하는 함수
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	static long getLCM(long a, long b) {   
		// 두 수의 곱에서 최대 공약수를 나누면 최소 공배수다
		return a * b / getGCD(a, b);
	}
}