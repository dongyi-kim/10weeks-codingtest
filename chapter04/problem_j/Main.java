import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int MAX_VALUE = 1000000;


	/**
	 * 게임의 규칙을 만족하면서 만들 수 있는 가장 큰 최대공약수를 반환하는 함수
	 *
	 * @param n         주어진 카드의 수
	 * @param cards     각 카드에 적힌 숫자 배열
	 * @return
	 */
	public static int getMaximumGCD(int n, int[] cards)
	{
		// 각 소인수에 대한 빈도수를 계산하기 위해 빈도수 테이블을 정의하자
		int[] frequency = new int[MAX_VALUE + 1];
		int maximumFactor = 1;  // 등장한 소인수들 중 최대값

		for(int C : cards)
		{   // 모든 카드 C에 대해 차례로 한 번씩
			// 모든 소인수를 찾아낸다
			ArrayList<Long> factors = MathUtil.factorize(C);
			for(long f : factors)
			{
				// 각 소인수의 빈도수를 계산한다
				int index = (int)f;
				frequency[index]+=1;

				// 소인수들 중 가장 큰 숫자를 찾는다
				if(maximumFactor < f)
				{
					maximumFactor = (int)f;
				}
			}
		}


		int answer = 1; // 각 소인수별 공약수를 모두 곱해 최대 공약수를 계산하자

		// 각 소인수들은 모든 n개의 카드에 공평히 나누는 것이 유리하다
		for(int f = 2; f <= maximumFactor; f += 1)
		{   // 각 소인수 f에 대하여 전체 등장 횟수를 계산한다
			int count = frequency[f];

			// n개의 숫자에는 (count/n)개씩 나누어 줄 수 있다.
			// 그러므로 f로 만들 수 있는 공약수는 최대 f ^(count/n) 이다.
			answer *= MathUtil.powInt( f, count / n );
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int n = scanner.nextInt();
		int[] cards = new int[n];
		for(int i = 0 ; i < n ; i++)
		{
			cards[i] = scanner.nextInt();
		}

		int answer = getMaximumGCD(n, cards);

		System.out.println(answer);
	}

}

class MathUtil{
	/**
	 *
	 * @param N
	 * @return
	 */
	public static ArrayList<Long> factorize(long N) // 최몇
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


	/**
	 * 두 정수 a, n에 대해 a ^ n을 계산해주는 함수
	 *
	 * @param a
	 * @param n
	 * @return
	 */
	public static int powInt(int a, int n)
	{
		int value = 1;
		int level = a;
		for(int scaleBit = 1; scaleBit <= n; scaleBit *= 2, level *= level)
		{
			if( (n & scaleBit) > 0)
			{
				value *= level;
			}
		}
		return value;
	}
}


