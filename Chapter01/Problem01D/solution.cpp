#include<cstdio>

const int MAX_N = 1000;

int get_sum(int data[], int length)
{	//data[0] ~ data[length-1] 사이의 모든 값에 대한 합을 반환하는 함수
	int sum = 0;
	
	for(int i = 0 ; i < length; i++)
	{	// 모든 data[i]에 대하여 한번 씩 실행되는 구문
		
		//sum에 data[i]가 누산된다.
		sum = sum + data[i];
	}
	
	//data[0] ~ data[length-1] 의 모든 값이 한 번씩 더해진 값
	return sum;
}


int main() {
	int n;
	int data[MAX_N];
	scanf("%d", &n);
	for(int i = 0 ; i < n ; i++)
	{
		scanf("%d", &data[i]);
	}
	int answer = get_sum(data, n);
	printf("%d\n",answer);
  return 0;
}
