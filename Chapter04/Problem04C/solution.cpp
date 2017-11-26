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
	int n, m;
	scanf("%d %d", &n, &m);

	int g = gcd(n, m);
	int l = lcm(n, m);

	printf("Case #%d:\n", caseIndex + 1);
	printf("%d %d\n", g, l);
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