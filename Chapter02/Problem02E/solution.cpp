#include<cstdio>
#include<iostream>

using namespace std;

/**
 * 주어진 숫자가 소수인지 판별하는 함수 
 *
 * @param N 
 * @return true   소수라면 
 * @return false  소수가 아니라면
 */
bool isPrime(int N) {
	if (N == 1) return false;   //1은 소수가 아니다
	else if (N == 2) return true; //2는 소수다
	else if (N % 2 == 0) return false; //나머지 짝수는 소수가 아니다

	for (int i = 3; i * i <= N; i += 2) {   //모든 N미만의 홀수 자연수 i에 대해
		if (N % i == 0) {   //이 범위에 약수가 존재한다면 소수일 수 없다.
			return false;
		}
	}
	//약수가 하나도 존재하지 않았다면.
	return true;
}

void testcase(int caseIndex) {
	int n;
	scanf("%d", &n);

	bool result = isPrime(n);

	printf("Case #%d\n", caseIndex);
	if (result) {
		printf("YES\n");
	} else {
		printf("NO\n");
	}
}

int main() {
	int caseSize;
	scanf("%d", &caseSize);
	for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
		testcase(caseIndex);
	}
	return 0;
}