#include <cstdio>
const int MAX_N = 10000;


int main() {
	int n, m, s;
	int data[MAX_N];
	
	//데이터를 입력 받는다 
	scanf("%d %d %d", &n, &m, &s);
	for(int i = 0 ; i < n ; i++)
	{
		scanf("%d", &data[i]);
	}
	
	int count = 0 ; //수정이가 체크해야 할 사람의 수를 저장
	//여기 알고리즘을 작성하자 
	
	for(int i = 0 ; i < n ; i ++)
	{	//data[0] ~ data[n-1]사이의 모든 값에 대하여 
		
		if( data[i] == s || data[i] == m )
		{	//data[i]가 s 혹은 m과 같은 모든 i들에 대하여 한 번씩 만 실행되는 구문
			count++;
		}
	}
	
	
	//답을 출력하자
	printf("%d\n", count);
	
  return 0;
}
