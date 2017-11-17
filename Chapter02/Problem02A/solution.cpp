#include <cstdio>

const int MAX_N = 100000;

int main()
{
	int n, m;
	int height[MAX_N];
	int month[MAX_N];
	
	scanf("%d", &n);
	
	//차례로 키를 입력받는다
	for(int i = 0 ; i < n ; i ++)
	{
		scanf("%d", &height[i]);
	}
	
	//차례로 달을 입력받는다
	for(int i = 0 ; i < n ; i ++)
	{
		scanf("%d", &month[i]);
	}
	
	//찾을 달을 입력받는다
	scanf("%d", &m);
	
	int tallest = -1; //조건을 만족하는 도토리 중 가장 큰 키 

	for(int i = n-1 ; i >=0 ; i --)
	{	// 모든 i에 대하여 - (n-1)부터 0까지 차례로 1씩 줄어가며
		//height[i]:= i번 도토리의 키 
		//month[i] := i반 도토리가 속한 달 
		if(month[i] == m )
		{	//찾고자 하는 달을 최초로 발견하였다면 저장 후 종료
			tallest = height[i];
			break;
		}
	}
	
	printf("%d\n",tallest);
    return 0;
}
