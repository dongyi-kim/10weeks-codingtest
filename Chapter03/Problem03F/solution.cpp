#include<cstdio>

const int MAX_N = 100000;

int data[MAX_N + 1];
long long rangeSum[MAX_N + 1];

int main()
{
	int n, m;

	scanf("%d %d", &n, &m);

	for (int i = 1; i <= n; i++)
	{
		scanf("%d", &data[i]);
	}

	rangeSum[0] = 0;
	for (int i = 1; i <= n; i++)
	{
		rangeSum[i] = rangeSum[i - 1] + data[i];
	}

	int maxIndex = -1;
	long long maxValue = -1;

	for (int i = 0; i < m; i++)
	{
		int leftIndex;
		int rightIndex;

		scanf("%d %d", &leftIndex, &rightIndex);

		long long sum = rangeSum[rightIndex] - rangeSum[leftIndex - 1];
		
		if (sum > maxValue)
		{
			maxValue = sum;
			maxIndex = i + 1;
		}
	}

	printf("%d %d\n", maxIndex, maxValue);
	return 0;
}