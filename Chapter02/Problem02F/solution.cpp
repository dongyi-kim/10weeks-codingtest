#include<cstdio>
#include<math.h>
const int MAX_N = 10000;


int main()
{
	int n;
	int x[MAX_N];
	int y[MAX_N];
	
	scanf("%d", &n);
	for(int i = 0 ; i < n ; i++)
	{
		scanf("%d %d", &x[i], &y[i]);	
	}
	
	
	int min_dist = 123456789; //현재까지 발견된 가장 가까운 두 천체의 거리 
	int min_count = 0;	//해당 거리 만큼 떨어진 천체 쌍의 수
	
	for(int i = 0 ; i < n ; i++)
	{
		for(int j = i + 1 ; j < n ; j ++)
		{
			//모든 두 천채의 조합 <i , j>에 대하여 
			int dx = x[i] - x[j];   
			int dy = y[i] - y[j];
			int square_dist = dx * dx + dy * dy; //거리의제곱 
			
			if(square_dist  < min_dist)
			{   //더 짧은 거리를 발견했다면 이 거리로 카운트 시작 
				min_dist = square_dist;
				min_count = 1 ;
			}else if(square_dist == min_dist)
			{   //기존의 거리와 같은 쌍을 발견했다면 카운트 업 
				min_count ++ ;	
			}
		}
	}
	
	double dist = sqrt((double)min_dist);
	
	printf("%.1lf\n", dist);
	printf("%d\n", min_count);
	
	return 0;
}