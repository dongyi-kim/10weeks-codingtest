
import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase(int caseIndex) {
		long N = scanner.nextLong();

		// N을 소인수 분해한다
		ArrayList<Long> factors = MathUtil.factorize(N);

		// 정답을 출력한다
		System.out.printf("#%d:\n", caseIndex);
		for(int i = 0 ; i <factors.size();i+=1)
		{
			if( i > 0 ){
				System.out.print(" ");
			}
			System.out.print(factors.get(i));
		}
		System.out.println();
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
	 * 자연수 N을 구성하는 모든 소인수를 반환하는 함수
	 *
	 * @param N
	 * @return
	 */
	public static ArrayList<Long> factorize(long N)
	{
		ArrayList<Long> factors = new ArrayList<>();
		for(long div = 2; div * div <= N; div += 1)
		{
			while(N % div == 0)
			{
				factors.add(div);
				N /= div;
			}
		}
		if( N > 1 ){
			factors.add(N);
		}
		return factors;
	}
}