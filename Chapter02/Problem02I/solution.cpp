#include<cstdio>

const int MAX_N = 100000;

int main()
{
	int n;
	int data[MAX_N];

	scanf("%d", &n);

	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}

	int max_value = data[0];
	int min_value = data[0];
	for (int i = 0; i < n; i++)
	{
		if (max_value < data[i])
		{
			max_value = data[i];
		}
		if (min_value > data[i])
		{
			min_value = data[i];
		}
	}

	int length = max_value - min_value + 1;
	if (length == n)
	{
		printf("YES\n");
	}
	else {
		printf("NO\n");
	}

	return 0;
}