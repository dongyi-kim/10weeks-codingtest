import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int MAX_VALUE = 1000000;
	public static final Sieve sieve = new Sieve(MAX_VALUE);

	public static ArrayList<Integer> getAllPrimeNumbers(int from, int to)
	{
		ArrayList<Integer> primes = new ArrayList<>();

		for(int num = from; num <= to; num += 1)
		{   // 주어진 범위 [from, to] 사이의 모든 자연수 num에 대하여
			if(sieve.isPrimeNumber(num) == true)
			{   // 소수라면 primes 리스트에 추가한다.
				primes.add(num);
			}
		}

		return primes;
	}

	public static void testCase(int caseIndex) {
		int L = scanner.nextInt();
		int R = scanner.nextInt();

		ArrayList<Integer> allPrimeNumbers = getAllPrimeNumbers(L, R);

		System.out.printf("Case #%d:\n", caseIndex);
		System.out.println(allPrimeNumbers.size());
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}


class Sieve //소인수 분해를 빠르게
{
	final int maximumValue;     // 에라토스테네스의 체에서 다룰 가장 큰 범위의 값
	final boolean[] isPrime;    // 각 숫자별 소수 여부
	Sieve(int maximumValue)
	{
		this.maximumValue = maximumValue;
		this.isPrime = new boolean[maximumValue+1];
		this.fillSieve();
	}

	/**
	 * 배열에 저장된 결과를 사용해 num의 소수 여부를 반환한다.
	 * 배열만을 사용하므로 시간 복잡도는 O(1)이 된다.
	 *
	 * @param num
	 * @return 'num'이 소수라면 true, 그렇지 않으면 false
	 */
	public boolean isPrimeNumber(int num)
	{
		return this.isPrime[num];
	}

	/**
	 * isPrime 배열의 값을 채우는 함수
	 */
	private void fillSieve()
	{
		Arrays.fill(isPrime, true);     // 처음에는 모두 소수라고 저장한다.
		isPrime[0] = isPrime[1] = false;    // 0과 1은 소수가 아니므로 미리 처리한다.

		for(int num = 2; num <= maximumValue; num += 1)
		{   // [2, N] 사이의 모든 자연수 num에 대해
			if( isPrime[num] == false )
			{   // 이미 소수가 아니라고 체크되었다면 건너 뛴다.
				continue;
			}

			// 이 때 num은 소수다.
			// 그러므로 num의 모든 배수를 소수가 아니라고 체크한다.
			// num보다 작은 k에 대해 mul=num*num으로 표현되는 모든 자연수는 이미 처리되었다.
			// 그러므로 mul = num * num 부터만 처리하면 된다.
			for(long mul = (long)num * num; mul <= maximumValue; mul += num )
			{   // [!!] 이 때 mul에 대해서, mul을 나누는 '최초의 소인수 num'이 등장한다.
				// 이 정보를 저장해두면 소인수 분해를 할 때 유용하다.
				int index = (int)mul;
				isPrime[index] = false;
			}
		}
	}
}