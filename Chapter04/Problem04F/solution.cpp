#include<cstdio>

const int MAX_N = 1000000;
bool isPrime[MAX_N + 1];

void initSieve()
{
	for (int i = 1; i <= MAX_N; i++)
	{
		isPrime[i] = true;
	}

	isPrime[1] = false;

	for (int p = 2; p <= MAX_N; p++)
	{
		if (isPrime[p] == false)
		{
			continue;
		}

		for (long long m = (long long) p * p; m <= MAX_N; m += p)
		{
			isPrime[m] = false;
		}
	}

}

void testCase(int caseIndex)
{
	int l, r;
	scanf("%d %d", &l, &r);

	int cnt = 0;
	for (int p = l; p <= r; p+= 1)
	{
		if (isPrime[p] == true)
		{
			cnt += 1;
		}
	}
	
	printf("Case #%d:\n", caseIndex + 1);
	printf("%d\n", cnt);
}

int main()
{
	initSieve();

	int caseNum;
	scanf("%d", &caseNum);
	for (int caseIndex = 0; caseIndex < caseNum; caseIndex += 1)
	{
		testCase(caseIndex);
	}
	return 0;
}
 