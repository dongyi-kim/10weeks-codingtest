#include<cstdio>

const int MAX_N = 1000;
const int MAX_COLOR = 100;
int main()
{
	int n, m;

	scanf("%d %d", &n, &m);

	int seat[MAX_N + 1];
	for (int i = 0; i <= n; i++)
	{
		seat[i] = 0;
	}

	int color[MAX_COLOR];
	for (int i = 0; i < 100; i++)
	{
		color[i] = 0;
	}

	for (int i = 0; i < m; i++)
	{
		int l, r, c;
		scanf("%d %d %d", &l, &r, &c);
		
		for (int index = l; index <= r; index++)
		{
			seat[index] = c;
		}
	}

	for (int index = 1; index <= n; index++)
	{
		int c = seat[index];
		color[c] += 1;
	}

	int min_color = -1;
	int max_color = -1;
	for (int c = 0; c < 100; c++)
	{
		if (color[c] == 0)
		{
			continue;
		}

		if (max_color == -1 || color[c] > color[max_color] )
		{
			max_color = c;
		}
		if (min_color == -1 ||color[c] < color[min_color])
		{
			min_color = c;
		}
	}

	printf("%d\n%d\n", max_color, min_color);

	return 0;
}