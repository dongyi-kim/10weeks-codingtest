#include <cstdio>
#include <vector>

using namespace std;

//소인수 분해를 빠르게
class Sieve {
public:
	int maximumValue;     // 에라토스테네스의 체에서 다룰 가장 큰 범위의 값
	vector<bool> isPrime; // 각 숫자별 소수 여부
	Sieve(int maximumValue) {
		this->maximumValue = maximumValue;
		this->isPrime.assign(maximumValue + 1, false);
		this->fillSieve();
	}

	/**
	 *
	 * @param num
	 * @return 'num'이 소수라면 true, 그렇지 않으면 false
	 */
	bool isPrimeNumber(int num) const {
		return this->isPrime[num];
	}

	/**
	 * isPrime 배열의 값을 채우는 함수
	 */
	void fillSieve() {
		isPrime.assign(this->maximumValue, true); // 처음에는 모두 소수라고 저장한다.
		isPrime[0] = isPrime[1] = false;    // 0과 1은 소수가 아니므로 미리 처리한다.

		for(int num = 2; num <= maximumValue; num += 1) {  
			// [2, N] 사이의 모든 자연수 num에 대해
			if( isPrime[num] == false ) {   
				// 이미 소수가 아니라고 체크되었다면 건너 뛴다.
				continue;
			}

			// 이 때 num은 소수다.
			// 그러므로 num의 모든 배수를 소수가 아니라고 체크한다.
			// num보다 작은 k에 대해 mul=num*num으로 표현되는 모든 자연수는 이미 처리되었다.
			// 그러므로 mul = num * num 부터만 처리하면 된다.
			for(long long mul = (long long)num * num; mul <= maximumValue; mul += num ) {   
				// [!!] 이 때 mul에 대해서, mul을 나누는 '최초의 소인수 num'이 등장한다.
				// 이 정보를 저장해두면 소인수 분해를 할 때 유용하다.
				int index = (int)mul;
				isPrime[index] = false;
			}
		}
	}
};

vector<int> getAllPrimeNumbers(int from, int to, const Sieve& sieve) {
	vector<int> primes;

	for(int num = from; num <= to; num += 1) {
		if(sieve.isPrimeNumber(num)) {
			primes.push_back(num);
		}
	}

	return primes;
}

void process(int caseIndex, const Sieve& sieve) {
	int L, R;
	scanf("%d%d", &L, &R);

	vector<int> allPrimeNumbers = getAllPrimeNumbers(L, R, sieve);

	printf("Case #%d:\n", caseIndex);
	printf("%d\n", (int)allPrimeNumbers.size());
}

int main() {
	const int MAX_VALUE = 1000000;
	Sieve sieve = Sieve(MAX_VALUE);

	int caseSize;
	scanf("%d", &caseSize);

	for (int caseIndex = 1; caseIndex <= caseSize; ++caseIndex) {
		process(caseIndex, sieve);
	}
}