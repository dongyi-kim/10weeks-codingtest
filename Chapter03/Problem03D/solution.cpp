#include <cstdio>
#include <vector>

using namespace std;


const int MAX_N = 1000000;
vector<int> FIBONACCI_TABLE;

/**
* 피보나치 수열의 1~n번째 항을 배열에 저장하여 반환해주는 함수
* * 단, 항의 가장 뒤 8자리만을 저장한다.
* @param n
* @return fibo[i] := 피보나치 수열의 i번째 항
*/
vector<int> makeFibonacciTable(int n) {
	const int MOD = 100000000;

	// 피보나치 배열을 미리 선언해준다.
	vector<int> ret(n + 1);
	ret[1] = 0; // 첫 번재 항은 0이다.
	ret[2] = 1; // 두 번째 항은 1이다.

	// 세 번째 항부터는 피보다치의 정의를 이용해 계산을 한다.
	// f(n) = f(n - 1) + f(n - 2)
	for (int i = 3; i <= n; ++i) {
		ret[i] = ret[i - 1] + ret[i - 2];

		// 피보나치를 구할 때 모듈러 값을 이용해 뒤에 8자리만 남기도록 해 준다.
		// ((A % MOD) + (B % MOD)) % MOD == (A + B) % MOD
		// 위 식이 성립하기 때문에 피보나치의 값이 잘못될 일은 없다.
		ret[i] %= MOD;
	}

	return ret;
}

/**
* 피보나치 수열의 n번째 항을 반환하는 함수
* 단, 항의 가장 뒤 8자리만을 반환한다.
* @param n
* @return
*/
int getFibo(int n) {
	// 피보나치의 값을 미리 계산해 뒀기 때문에 그 값을 반환한다.
	// 첫 번째 항의 인덱스가 0이므로 -1을 해 준다.
	int answer = FIBONACCI_TABLE[n];
	return answer;
}

int main() {
	FIBONACCI_TABLE = makeFibonacciTable(MAX_N);

	int caseSize;
	scanf("%d", &caseSize);

	for (int caseIndex = 1; caseIndex <= caseSize; ++caseIndex) {
		int n;
		scanf("%d", &n);

		// 피보나치 수열의 n번째 항을 계산하여 출력한다.
		int answer = getFibo(n);
		printf("%d\n", answer);
	}

	// 불필요한 배열은 가능하면 할당 해제해주는 버릇을 들이자.
	FIBONACCI_TABLE.clear();

	return 0;
}
