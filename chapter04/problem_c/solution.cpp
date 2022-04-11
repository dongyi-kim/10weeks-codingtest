#include <cstdio>

using namespace std;


/**
 * a와 b의 최대 공약수를 계산하는 함수
 *
 * @param a
 * @param b
 * @return
 */
long long getGCD(long long a, long long b) {
	while (a % b != 0) {   // b가 a의 약수가 아니라면 반복한다
		long long c = a % b; // 나머지를 계산한다
		a = b;          // 이후 b와 c에 대해 이 과정을 반복한다
		b = c;          // a <= b, b <= c
	}
	// b가 a의 약수라면, b가 a의 최대 공약수다
	return b;
}

/**
 * a와 b의 최소 공배수를 계산하는 함수
 *
 * @param a
 * @param b
 * @return
 */
long long getLCM(long long a, long long b) {
	// 두 수의 곱에서 최대 공약수를 나누면 최소 공배수다
	return a * b / getGCD(a, b);
}

void process(int caseIndex) {
	int num1, num2;
	scanf("%d%d", &num1, &num2);

	long long gcd = getGCD(num1, num2);
	long long lcm = getLCM(num1, num2);

	printf("Case #%d:\n", caseIndex);
	printf("%lld %lld\n", gcd, lcm);
}

int main() {
	int caseSize;
	scanf("%d", &caseSize);

	for (int caseIndex = 1; caseIndex <= caseSize; ++caseIndex) {
		process(caseIndex);
	}
}