#include <cstdio>

int get_range_sum(int begin, int end)
{
	int answer = 0;

	for (int i = begin; i <= end; i++)
	{
		answer += i;
	}

	return answer;
}

int main()
{
	int n;
	int answer = 0;

	scanf("%d", &n);

	for (int i = 1; i <= n; i++)
	{
		answer = answer + get_range_sum(1, i);
	}

	printf("%d\n", answer);
	return 0;
}
