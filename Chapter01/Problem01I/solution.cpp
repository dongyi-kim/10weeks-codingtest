#include <cstdio>
const int MAX_N = 1000;

int get_minimum_index(int data[], int begin, int end)
{	//data[begin] ~ data[end]사이의 가장 작은 값을 가진 인덱스를 반환하는 함수 
	int answer = begin;	//가장 작은 값을 가지고 있는 인덱스
	
	for(int i = begin; i <= end; i++)
	{
		if( data[i] < data[answer] )
		{
			answer = i;	
		}
	}
	
	return answer;
}

void selection_sort(int data[], int length)
{  //이 곳에 정렬 함수를 작성한다
	int n = length;
	for(int i = 0; i < n ; i++)
	{ // i := 0 , 1, ...,  n -1
		
		//data[i] ~ data[length-1]에서 가장 작은 값을 찾는다 
		int min_index = get_minimum_index(data, i, length-1);
		
		//data[i]에 가장 작은 숫자가 오도록 자리를 바꾼다
		int temp = data[i];
		data[i] = data[min_index];
		data[min_index] = temp;
	}
}

int main() {
	int n;
	int data[MAX_N];
	scanf("%d", &n);
	for(int i = 0 ; i < n ; i++)
	{
		scanf("%d", &data[i]);
	}
	selection_sort(data, n);
	for(int i = 0 ; i < n ; i++)
	{
		if( i > 0)
		{	//첫 번째 원소가 아니라면 앞에 공백을 하나 출력한다 
			printf(" ");
		}
		printf("%d",data[i]);
	}
  return 0;
}
