#include <cstdio>

const int MAX_N = 100000;

int main()
{
	int n, m;
	int height[MAX_N];
	int month[MAX_N];

	scanf("%d", &n);

	for (int i = 0; i < n; i++)
	{
		scanf("%d", &height[i]);
	}

	for (int i = 0; i < n; i++)
	{
		scanf("%d", &month[i]);
	}

	scanf("%d", &m);

	int tallest = -1; 

	for (int i = n - 1; i >= 0; i--)
	{	
		if (month[i] == m)
		{	
			tallest = height[i];
			break;
		}
	}

	printf("%d\n", tallest);
	return 0;
}
