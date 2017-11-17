#include <cstdio>

int get_range_sum(int begin, int end)
{
	int answer = 0;
	
	for(int i = begin; i <= end; i++)
	{
		answer += i;	
	}
	
	return answer;
}

int main()
{
	int n;
	int answer = 0;
	
	scanf("%d", &n);
	
	for(int i = 1 ; i <= n ; i ++)
	{	//1 ~ N 사이의 모든 i 에 대하여 
		
		
		// 1 ~ i 사이의 부분합을 구하여 answer 누적한다
		answer = answer + get_range_sum(1, i);
	}
	
	printf("%d\n", answer);
  return 0;
}
