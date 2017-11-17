#include<cstdio>
#include<iostream>

const int MAX_N = 100000;

using namespace std;
/*** arr[0] ~ arr[n-1]에서 자기 오른쪽 원소보다 큰 값을 가지는 원소의 수 ***/
int get_rev(int arr[], int n)
{
	int cnt = 0;
	//begin of algorithm
	
	for(int i = 0 ; i + 1 ; i < n ; i++)
	{	//모든 인접한 두 숫자 arr[i], arr[i+1]에 대하여
		if( arr[i] > arr[i+1] )
		{	//오른쪽 원소가 더 쌍의 수를 센다
			cnt ++;	
		}
	}

	//end of algorithm
	return cnt;
}

int main()
{   
	int n;
	int data[MAX_N];

	//데이터를 입력받는다 
	scanf("%d %d %d", &n);
	for(int i = 0 ; i < n ; i ++){
		scanf("%d", &data[i]);
	}
	
 	//자기 오른쪽 원소보다 큰 값을 가지는 원소의 수
	int revcnt = get_rev(arr, n); 
	
	if( revcnt == 0 )
	{	//그런 원소가 하나도 없다면 오름차순이라고 할 수 있다
		printf("YES\n");
	}else
	{	//그런 원소가 하나라도 존재한다면 오름차순이라고 할 수 없다
		printf("NO\n");
	}
	return 0;
}