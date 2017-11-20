#include<cstdio>

const int MAX_N = 100000;


int get_sum(int arr[], int n, int p)
{
	int sum = 0;
	for (int i = 0; i < n; i++)
	{
		if (arr[i] <= p)
		{
			sum += arr[i];
		}
	}
	return sum;
}


int get_cnt(int arr[], int n, int p)
{
	int cnt = 0;
	for (int i = 0; i < n; i++)
	{
		if (arr[i] <= p)
		{
			cnt += 1;
		}
	}
	return cnt;
}


int main()
{
	int n, p, q;
	int data[MAX_N];

	scanf("%d %d %d", &n, &p, &q);
	for (int i = 0; i < n; i++) {
		scanf("%d", &data[i]);
	}


	int sum = get_sum(data, n, p);
	int count = get_cnt(data, n, p);

	printf("%d %d\n", count, sum);
	if (sum <= q) {
		printf("YES\n");
	}
	else {
		printf("NO\n");
	}
	return 0;
}