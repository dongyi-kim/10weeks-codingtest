#include <cstdio>
#include <vector>

using namespace std;

class Sieve {
public:
	int maximumValue;
	vector<bool> isPrime;
	vector<int> primes;

	Sieve(int maximumValue) {
		this->maximumValue = maximumValue;
		this->isPrime.assign(maximumValue + 1, true);
		fillSieve();
	}


	void fillSieve() {
		isPrime[0] = isPrime[1] = false;
		for (int num = 2; num <= maximumValue; num += 1) {
			if (isPrime[num] == false) {
				continue;
			}

			primes.push_back(num);
			for (long long mul = (long long) num * num; mul <= maximumValue; mul += num) {
				int index = (int) mul;
				isPrime[index] = false;
			}
		}
	}

	bool isPrimeNumber(int num) const {
		return isPrime[num];
	}
};

void process(int caseIndex, const Sieve &sieve) {
	int x;
	scanf("%d", &x);

	int a = -1, b = -1;

	// 두 수 p, q에 대해 소수 판별을 빠르게 하기 위해서
	// 에라토스테네스의 체를 사용한다.
	for (int p = 3; p <= x / 2; p += 2) {
		// p 보다 작은 모든 홀수 p에 대하여
		int q = x - p; // x = p + q가 되는 q가 결정적으로 정해진다

		// p와 q가 모두 소수라면?
		if (sieve.isPrimeNumber(p) && sieve.isPrimeNumber(q)) {   // x = p + q가 되는 두 홀수 소수 <p, q>가 존재한다
			a = p;
			b = q;
			break;
		}
	}

	printf("Case #%d:\n", caseIndex);
	if (a > 0 && b > 0) {
		printf("%d = %d + %d\n", x, a, b);
	} else {
		printf("-1\n");
	}
}

int main() {
	const int MAX_VALUE = 1000000;
	Sieve sieve(MAX_VALUE);

	int caseSize;
	scanf("%d", &caseSize);

	for (int caseIndex = 1; caseIndex <= caseSize; ++caseIndex) {
		process(caseIndex, sieve);
	}
}