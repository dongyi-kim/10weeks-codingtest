#include<cstdio>

const int MAX_N = 10000;

int get_abs(int n)
{	
	if (n < 0)
		return -n;
	return n;
}


int main()
{
	int n;
	scanf("%d", &n);


	int data[MAX_N];
	int sum = 0;

	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
		sum = sum + data[i];
	}


	int m = 0; 
	for (int i = 0; i < n; i++)
	{
		int dm = get_abs(sum - data[m] * n);
		int di = get_abs(sum - data[i] * n);

		if (dm > di)
		{
			m = i;
		}
	}

	printf("%d %d\n", m + 1, data[m]);

	return 0;
}
