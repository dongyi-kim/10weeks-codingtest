#include<cstdio>
#include<math.h>
const int MAX_N = 10000;


int main()
{
	int n;
	int x[MAX_N];
	int y[MAX_N];

	scanf("%d", &n);
	for (int i = 0; i < n; i++)
	{
		scanf("%d %d", &x[i], &y[i]);
	}


	int min_dist = 1234567890; 
	int min_count = 0;	

	for (int i = 0; i < n; i++)
	{
		for (int j = i + 1; j < n; j++)
		{
			int dx = x[i] - x[j];
			int dy = y[i] - y[j];
			int square_dist = dx * dx + dy * dy; 

			if (square_dist  < min_dist)
			{   
				min_dist = square_dist;
				min_count = 1;
			}
			else if (square_dist == min_dist)
			{   
				min_count++;
			}
		}
	}

	double dist = sqrt((double)min_dist);

	printf("%.1lf\n", dist);
	printf("%d\n", min_count);

	return 0;
}