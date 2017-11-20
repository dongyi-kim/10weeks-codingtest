#include<cstdio>

bool is_prime(int n)
{
	if (n <= 1)
		return false;

	if (n == 2)
		return true;

	if (n % 2 == 0)
		return false;

	for (long long i = 3; i*i <= n; i += 2)
	{
		if (n % i == 0) {
			return false;
		}
	}

	return true;
}
void test_case()
{
	int n;
	scanf("%d", &n);
	if (is_prime(n))
	{
		printf("YES\n");
	}
	else
	{
		printf("NO\n");
	}
}

int main() {
	int tc;
	scanf("%d", &tc);
	for (int i = 1; i <= tc; i++)
	{
		printf("Case #%d\n", i);
		test_case();
	}
	return 0;
}
