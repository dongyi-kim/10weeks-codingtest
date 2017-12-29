#include<cstdio>

const int MOD = 100000000;
const int MAX_N = 1000000;

int main()
{
	int fib[MAX_N + 1];
	int caseNum;

	fib[1] = 0;
	fib[2] = 0;
	for (int i = 3; i <= MAX_N; i++)
	{
		fib[i] = (fib[i - 1] + fib[i - 2]) % MOD;
	}

	scanf("%d", &caseNum);
	for (int caseIndex = 0; caseIndex < caseNum; caseIndex++)
	{
		int n;
		scanf("%d", &n);
		printf("%d\n", fib[n]);
	}

	return 0;
}
