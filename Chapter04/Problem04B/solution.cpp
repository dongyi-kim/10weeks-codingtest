#include<cstdio>

const int MAX_N = 5000;
const int MAX_M = 1000;

int main()
{
	int n, m;
	int used[MAX_N];
	scanf("%d %d", &n, &m);

	for (int ticket = 0; ticket < n; ticket++)
	{
		used[ticket] = 0;
	}

	for (int i = 0; i < m; i++)
	{
		int id;
		scanf("%d", &id);
		int ticket = id % n;

		while (used[ticket] != 0)
		{
			ticket = (ticket + 1) % n;
		}
		
		used[ticket] = 1;

		printf("%d\n", ticket);
	}

	return 0;
}