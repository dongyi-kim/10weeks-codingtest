import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase(int caseIndex)
	{   //각 테스트케이스에 대하여
		int num1 = scanner.nextInt();
		int num2 = scanner.nextInt();

		// 두 숫자의 최대 공약수와 최소 공배수를 계산한다
		long gcd =  MathUtil.getGCD(num1, num2);
		long lcm =  MathUtil.getLCM(num1, num2);

		// 정답을 출력한다
		System.out.printf("Case #%d:\n", caseIndex);
		System.out.printf("%d %d\n", gcd,lcm);
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}

class MathUtil{

	/**
	 * a와 b의 최대 공약수를 계산하는 함수
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	static long getGCD(long a, long b)
	{
		while( a % b != 0)
		{
			long c = a % b;
			a = b;
			b = c;
		}
		return b;
	}

	/**
	 * a와 b의 최소 공배수를 계산하는 함수
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	static long getLCM(long a, long b)
	{
		return a * b / getGCD(a, b);
	}
}