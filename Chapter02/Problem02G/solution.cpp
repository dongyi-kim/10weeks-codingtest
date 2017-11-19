#include<cstdio>

const int MAX_N = 1000;


void bubble_sort(int arr[], int length)
{	//arr[0] ~ arr[length-1]의 원소를 오름차순으로 정렬해주는 함수 
	for(int i = 0 ; i < length; i++)
	{   //arr[0] ~ arr[back] 범위의 최대값을 뒤로 밀어 arr[back]으로 보내는 로직
		int back = length - i - 1 ; //최대값이 위치하게 될 칸의 번호 
		int changed = 0;            //이번 범위에서 교환이 일어난 적 있는지 
		for(int j = 0 ; j < back; j ++)
		{
			if( arr[j] > arr[j+1])
			{   //대소관계를 위반하는 인접한 두 원소가 있다면

                //두 원소의 자리를 바꾸고 
				int temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j+1]= temp;
				
                //그러한 쌍의 수를 증가시킨다 
				changed ++;
			}
		}
        
        //이 범위의 모든 원소가 정렬되어 있다면 더 이상 종료하지 않고 종료한다
		if(changed == 0)
		{
			break;	
		}
	}
}

int main()
{
	int arr[MAX_N];
	int n;
	
	scanf("%d", &n);
	for(int i = 0 ; i < n ; i ++)
	{
		scanf("%d", &arr[i]);
	}
	
	bubble_sort(arr, n);
	
	
	for(int i = 0 ; i < n ; i ++)
	{
		if( i > 0 )
			printf(" ");
		printf("%d", arr[i]);
	}

  return 0;
}
