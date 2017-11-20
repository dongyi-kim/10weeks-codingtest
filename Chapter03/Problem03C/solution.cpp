#include<cstdio>

const int MAX_N = 100000;
const int MAX_NUM = 100000;

int main()
{
	int n;
	int data[MAX_N];
	int count[MAX_NUM + 1 ];

	scanf("%d", &n);

	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}

	for (int value = 0; value <= MAX_NUM; value++)
	{
		count[value] = 0;
	}

	for (int i = 0; i < n; i++)
	{
		int value = data[i];
		count[value] += 1;
	}

	bool first = true;
	for (int value = 0; value <= MAX_NUM; value++)
	{
		if (count[value] != 1)
		{
			continue;
		}
		if (first)
		{
			first = false;
		}
		else 
		{
			printf(" ");
		}
		printf("%d", value);
	}


	return 0;
}