#include <cstdio>
const int MAX_N = 10000;


int main() {
	int n, m, s;
	int data[MAX_N];

	scanf("%d %d %d", &n, &m, &s);
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &data[i]);
	}

	int count = 0;

	for (int i = 0; i < n; i++)
	{
		if (data[i] == s || data[i] == m)
		{
			count++;
		}
	}

	printf("%d\n", count);

	return 0;
}
