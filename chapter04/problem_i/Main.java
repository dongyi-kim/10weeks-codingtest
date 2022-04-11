import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int MAX_VALUE = 1000000;
	public static final Sieve sieve = new Sieve(MAX_VALUE);

	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void testCase(int caseIndex) {
		int x = scanner.nextInt();

		int a = -1;
		int b = -1;

		// 두 수 p, q에 대해 소수 판별을 빠르게 하기 위해서
		// 에라토스테네스의 체를 사용한다.
		for(int p = 3; p <= x / 2; p += 2)
		{   // p 보다 작은 모든 홀수 p에 대하여
			int q = x - p; // x = p + q가 되는 q가 결정적으로 정해진다

			// p와 q가 모두 소수라면?
			if(sieve.isPrimeNumber(p) && sieve.isPrimeNumber(q))
			{   // x = p + q가 되는 두 홀수 소수 <p, q>가 존재한다
				a = p;
				b = q;
				break;
			}
		}

		// 정답을 출력한다
		System.out.printf("Case #%d:\n", caseIndex);
		if(a > 0 && b > 0)
		{
			System.out.printf("%d = %d + %d\n", x, a, b);
		}else{
			System.out.println(-1);
		}
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
}

class Sieve
{
	final int maximumValue;
	final boolean[] isPrime;
	final ArrayList<Integer> primes;
	Sieve(int maximumValue)
	{
		this.maximumValue = maximumValue;
		this.isPrime = new boolean[maximumValue+1];
		this.primes = new ArrayList<Integer>();
		this.fillSieve();
	}


	private void fillSieve()
	{
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for(int num = 2; num <= maximumValue; num += 1)
		{
			if( isPrime[num] == false ){
				continue;
			}

			primes.add(num);
			for(long mul = (long)num * num; mul <= maximumValue; mul += num )
			{
				int index = (int)mul;
				isPrime[index] = false;
			}
		}
	}

	public boolean isPrimeNumber(int num)
	{
		return this.isPrime[num];
	}
}