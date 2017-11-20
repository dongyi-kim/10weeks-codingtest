#include <cstdio>

const int MAX_N = 100000;

int main()
{
	int n, k;
	int data[MAX_N];

	scanf("%d %d", &n, &k);
	for (int i = 0; i < n; i++) {
		scanf("%d", &data[i]);
	}

	bool flag = false;
	int sum = 0;
	for (int i = 0; i < n; i++)
	{
		int right = i;
		int left = i - k + 1;
		int removed = right - k;
		int added = right;

		if (removed >= 0)
		{
			sum -= data[removed];
		}

		sum += data[added];

		if (left >= 0 && sum % 2 == 0)
		{
			flag = true;
			break;
		}
	}

	if (flag == true)
	{
		printf("YES\n");
	}
	else {
		printf("NO\n");
	}

	return 0;
}
