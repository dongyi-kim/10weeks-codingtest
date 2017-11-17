#include<cstdio>
const int MAX_N = 1000;

int find_value(int value, int data[], int length)
{	// => data[0] ~ data[length-1]에서 value가 존재하는 인덱스를 반환하는 함수 
	// => 존재하지 않는다면 -1을 반환한다.
	
	int index = -1;
	for(int i = 0 ; i < length; i++)
	{	// 모든 data[i]에 대하여 
		if(data[i] == value)
		{	//data[i]가 value와 일치한다면 
			//i가 찾는 인덱스가 된다
			
			index = i;
			
			//한 번이라도 이 코드가 실행되었다면 index에는 value의 인덱스가 저장되어 있다.
			//한 번도 실행되지 않았다면 index는 변하지 않으므로 -1로 남을 것이다.
		}
	}

	return index;
}

int main(){
	int n, m;
	int data[MAX_N];
	scanf("%d %d", &n, &m);
	for(int i = 0 ; i < n ; i ++)
	{
		scanf("%d", &data[i]);
	}
	int answer = find_value(m, data, n);
	printf("%d\n",answer);
	return 0;
}