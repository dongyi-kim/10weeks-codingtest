#include<cstdio>
#include<iostream>

const int MAX_N = 100000;

using namespace std;


/*** arr[0] ~ arr[n-1]에서 p이하인 값들의 합을 반환하는 함수 ***/
int get_sum(int arr[], int n, int p)
{
	int sum = 0 ;
	for(int i = 0 ; i < n ; i ++)
	{
		if( arr[i] <= p )
		{
			sum += arr[i];	
		}
	}
	return sum;
}

/*** arr[0] ~ arr[n-1]에서 p이하인 값들의 갯수를 반환하는 함수 ***/
int get_cnt(int arr[], int n, int p)
{
	int cnt = 0;
	for(int i = 0 ; i < n ; i ++)	
	{
		if(arr[i] <= p)
		{
			cnt += 1;
		}
	}
	return cnt;
}


int main()
{   
	int n, p, q;
	int data[MAX_N];

	//데이터를 입력받는다 
	scanf("%d %d %d", &n, &p, &q);
	for(int i = 0 ; i < n ; i ++){
		scanf("%d", &data[i]);
	}


	int sum = get_sum(data, n, p); //arr에서 p이하인 값들의 합 
	int count = get_cnt(data, n, p); //arr에서 p이하인 값들의 수
	
	printf("%d %d\n", count, sum);
	if( sum <= q ){
		printf("YES\n");
	}else{
		printf("NO\n");
	}
	return 0;
}