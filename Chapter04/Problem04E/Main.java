
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
		{   // sqrt(N)이하의 자연수 div에 대해서
			while(N % div == 0)
			{   // div이 N의 약수라면?
				// 이 때, div보다 작은 약수는 모두 처리되었으므로 div은 소인수임이 보장된다
				
				// 소인수 목록에 div을 추가한다.
				factors.add(div);
				
				// N에서 div을 소거해준다. 
				// 그러므로 이후에는 div을 제외한 소인수를 찾게 된다.
				N /= div;
			}
		}
		if( N > 1 )
		{   // 소인수를 찾지 못하고 남아있는 N이 존재한다면, 소수일 수 밖에 없다. 
			// 이를 소인수에 추가한다.
			factors.add(N);
		}
		return factors;
	}
}