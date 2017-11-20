#include<cstdio>
const int MAX_N = 1000;

int find_value(int value, int data[], int length)
{
	int index = -1;
	for (int i = 0; i < length; i++)
	{
		if (data[i] == value)
		{
			index = i;
		}
	}

	return index;
}

int main() {
	int n, m;
	int data[MAX_N];
	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}
	int answer = find_value(m, data, n);
	printf("%d\n", answer);
	return 0;
}