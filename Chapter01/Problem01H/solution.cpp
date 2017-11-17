#include<cstdio>

const int MAX_N = 10000;

int get_abs(int n)
{	// => 정수 n의 절대값을 반환해주는 함수
	if( n < 0)
		return -n;
	return n;
}


int main()
{
	int n;
	scanf("%d", &n);
	
	//데이터를 차례로 입력받으며 그 합을 저장한다
	int data[MAX_N];
	int sum = 0;
	
	for(int i = 0 ; i < n ; i++)
	{
		scanf("%d", &data[i]);
		sum = sum + data[i];
	}
	
	
	int m = 0; //평균과 가장 가까운 데이터의 번호 
	for(int i = 0 ; i < n; i++)
	{	
		int dm = get_abs(sum - data[m] * n );
		int di = get_abs(sum - data[i] * n );
		
		if( dm > di)
		{	//i번 데이터가 m번째 데이터보다 더 평균에 가깝다면 
			//그 인덱스를 저장한다
			m = i;		
		}
	}
	
	printf("%d %d\n", m+1, data[m]);
	
  return 0;
}
