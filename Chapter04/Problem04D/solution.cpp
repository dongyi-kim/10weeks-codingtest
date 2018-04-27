#include <cstdio>
#include <vector>

using namespace std;

long long getGCD(long long a, long long b) {
	if (a % b == 0) {
		return b;
	}
	return getGCD(b, a % b);
}

long long getLCM(long a, long b) {
	return a * b / getGCD(a, b);
}

/**
 * 여러 숫자에 대한 공통 최소 공배수를 계산하는 함수
 *
 * @param numbers
 * @return
 */
long long getLCM(vector<long long>& numbers) {
	if (numbers.size() == 0) {
		return 0;
	}

	long long lcm = numbers[0];

	for (int i = 1; i < numbers.size(); i += 1)  {   
		// 모든 숫자 numbers[i]에 대하여 차례로
		// lcm := numbers[0] ~ numbers[i-1]에 대한 최대 공약수
		lcm = getLCM(lcm, numbers[i]);
		// lcm := numbers[0] ~ numbers[i]에 대한 최대 공약수
	}

	return lcm;
}

/**
 * 모든 수열이 동시에 최초의 원소를 만나는 최소 주기를 계산하는 함수
 *
 * @param n     수열의 수
 * @param sizes 각 순환 수열의 길이(주기)
 * @return
 */
long long getGlobalPeriod(vector<long long>& sizes) {
	long globalPeriod = getLCM(sizes);
	return globalPeriod;
}

int main() {
	int n;
	scanf("%d", &n);

	vector<long long> sizes(n);

	for (int i = 0; i < n ; i += 1) {
		scanf("%lld", &sizes[i]);
	}

	long long answer = 1 + getGlobalPeriod(sizes);
	printf("%lld\n", answer);
}
