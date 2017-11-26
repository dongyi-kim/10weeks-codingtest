#include<cstdio>

const int MAX_N = 10;
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


int main()
{
	int n;
	int period[MAX_N];
	scanf("%d", &n);

	for (int i = 0; i < n; i++)
	{
		scanf("%d", &period[i]);
	}

	int l = period[0];
	for (int i = 1; i < n; i++)
	{
		l = lcm(l, period[i]);
	}

	printf("%d\n", l + 1);
	return 0;
}