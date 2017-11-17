#include<cstdio>
#include<iostream>
using namespace std;

const int MAX_N = 100000;

int main()
{
	int n;
	int data[MAX_N];

	scanf("%d", &n);
	for(int i = 0 ; i< n ; i ++)
	{
		scanf("%d", &data[i]);
	}
	
	//count := data[0] ~ data[n-1]에 등장하는 숫자의 개수
	int count = 1; //data[0]가 한 종류다 
	
	for(int i = 1; i < n ; i++)
	{	//data[1] ~ data[n-1]의 각 원소에 대하여 
		
		//자기 왼쪽 원소와 다른 값을 가진다면
		if(data[i-1] != data[i])
		{	//오름 차순이므로 data[i]와 같은 값은 처음 등장한 것이므로 카운트한다 
			count ++;
		}
	}
	
	//정답을 출력한다
	printf("%d\n", count);
	
	return 0;
}