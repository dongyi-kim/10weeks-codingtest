#include<cstdio>

int gcd(int a, int b)
{
	int c = a % b;
	while (c != 0)
	{
		a = b;
		b = c;
		c = a % b;
	}
	return b;
}

int lcm(int a, int b)
{
	return a * b / gcd(a, b);
}

void testCase(int caseIndex)
{
	int m, n, x, y;
	scanf("%d %d %d %d", &m, &n, &x, &y);
	
	int k = lcm(m, n);

	int answer = -1;
	for (int i = x; i <= k; i += m)
	{
		int p = (i - 1) % m + 1;
		int q = (i - 1) % n + 1;
		if (p == x && q == y)
		{
			answer = i;
			break;
		}
	}

	printf("%d\n", answer);
}

int main()
{
	int caseNum;
	scanf("%d", &caseNum);

	for (int caseIndex = 0; caseIndex < caseNum; caseIndex += 1)
	{
		testCase(caseIndex);
	}
	return 0;
}