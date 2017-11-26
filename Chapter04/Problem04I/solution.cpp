#include<cstdio>

const int MAX_N = 1000000;


bool isPrime[MAX_N + 1];
void initSieve()
{
	for (int i = 1; i <= MAX_N; i += 1)
	{
		isPrime[i] = true;
	}

	isPrime[1] = false;
	
	for (int p = 2; p <= MAX_N; p += 1)
	{
		if (isPrime[p] == false)
		{
			continue;
		}

		for (long long m = (long long)p * p; m <= MAX_N; m += p)
		{
			isPrime[m] = false;
		}
	}
}

void testCase(int caseIndex)
{
	int n;
	scanf("%d", &n);

	int answerA = -1;
	int answerB = -1;

	for (int a = 3; a < n; a += 2)
	{
		if (isPrime[a] == false)
		{
			continue;
		}

		int b = n - a;
		if (isPrime[b] == true)
		{
			answerA = a;
			answerB = b;
			break;
		}
	}

	printf("Case #%d:\n", caseIndex + 1);
    if(answerA == -1)
    {
        printf("-1\n");
    }else{
	    printf("%d = %d + %d\n", n, answerA, answerB);
    }
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