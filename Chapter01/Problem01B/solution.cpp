#include <cstdio>
using namespace std;

const int MAX_N = 10000;


int get_max(int data[], int length) 
{   // => data[0] ~ data[length-1] 사이의 최대값을 반환하는 함수
	int max_value = data[0];
	for(int i = 0 ; i < length; i ++)
	{	//모든 i에 대하여 모든 배열의 원소 data[i]에 대하여
		if( max_value > data[i] )
		{	//max_value := data[0]~data[i-1]중 가장 컸던 값
			//data[i]가 이 보다 크다면 max_value는 data[i]로 대체한다
			//그 이후 max_value := data[0] ~ data[i]중 가장 큰 값이 된다.
			max_value = data[i];	
		}
	}
	
	//data[0] ~ data[length-1] 중 가장 큰 값이 저장되어 있음이 확실하다
	return max_value;
}


int main() {
	int n;
	int data[MAX_N];
	
	//데이터의 수 N을 입력받는다
	scanf("%d", &n);
	
	//N개의 데이터를 입력받는다 
	for(int i = 0 ;  i < n ; i++){
		scanf("%d", &data[i]);
	}
	
	//최대값을 구한다 
	int answer = get_max(data, n);
	
	//출력한다
	printf("%d\n", answer);
  return 0;
}
