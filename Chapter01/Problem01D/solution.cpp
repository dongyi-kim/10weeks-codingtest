#include<cstdio>

const int MAX_N = 1000;

int get_sum(int data[], int length)
{
	int sum = 0;

	for (int i = 0; i < length; i++)
	{
		sum = sum + data[i];
	}

	return sum;
}


int main() {
	int n;
	int data[MAX_N];
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}
	int answer = get_sum(data, n);
	printf("%d\n", answer);
	return 0;
}
