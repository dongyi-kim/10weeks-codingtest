#include <cstdio>
#include <vector>

using namespace std;

/**
 *
 * @param N
 * @return
 */
vector<long long> factorize(long long N) {
	vector<long long> factors;

	for (long div = 2; div * div <= N; div += 1) {
		while (N % div == 0) {
			factors.push_back(div);
			N /= div;
		}
	}

	if (N > 1) {
		factors.push_back(N);
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
int powInt(int a, int n) {
	int value = 1;
	int level = a;
	for (int scaleBit = 1; scaleBit <= n; scaleBit *= 2, level *= level) {
		if ((n & scaleBit) > 0) {
			value *= level;
		}
	}
	return value;
}

/**
 * 게임의 규칙을 만족하면서 만들 수 있는 가장 큰 최대공약수를 반환하는 함수
 *
 * @param n         주어진 카드의 수
 * @param cards     각 카드에 적힌 숫자 배열
 * @return
 */
int getMaximumGCD(int n, const vector<int> &cards) {
	// 각 소인수에 대한 빈도수를 계산하기 위해 빈도수 테이블을 정의하자
	vector<int> frequency(1000000 + 1);
	int maximumFactor = 1;  // 등장한 소인수들 중 최대값

	for (int i = 0; i < cards.size(); i += 1) {

		// 모든 카드 C에 대해 차례로 한 번씩
		// 모든 소인수를 찾아낸다
		const int &C = cards[i];
		vector<long long> factors = factorize(C);

		for (int j = 0; j < factors.size(); ++j) {

			// 각 소인수의 빈도수를 계산한다
			const long long &f = factors[j];
			frequency[f] += 1;

			// 소인수들 중 가장 큰 숫자를 찾는다
			if (maximumFactor < f) {
				maximumFactor = f;
			}
		}
	}


	int answer = 1; // 각 소인수별 공약수를 모두 곱해 최대 공약수를 계산하자

	// 각 소인수들은 모든 n개의 카드에 공평히 나누는 것이 유리하다
	for (int f = 2; f <= maximumFactor; f += 1) {   // 각 소인수 f에 대하여 전체 등장 횟수를 계산한다
		int count = frequency[f];

		// n개의 숫자에는 (count/n)개씩 나누어 줄 수 있다.
		// 그러므로 f로 만들 수 있는 공약수는 최대 f ^(count/n) 이다.
		answer *= powInt(f, count / n);
	}

	return answer;
}

int main() {
	int n;
	scanf("%d", &n);

	vector<int> cards(n);
	for (int i = 0; i < n; ++i) {
		scanf("%d", &cards[i]);
	}

	int answer = getMaximumGCD(n, cards);

	printf("%d\n", answer);
}